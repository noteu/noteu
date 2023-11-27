package com.noteu.noteu.question.service.impl;

import com.noteu.noteu.member.entity.Member;
import com.noteu.noteu.member.repository.MemberRepository;
import com.noteu.noteu.question.converter.impl.QuestionPostConverterImpl;
import com.noteu.noteu.question.dto.QuestionPostDTO;
import com.noteu.noteu.question.dto.RecentQuestionDto;
import com.noteu.noteu.question.dto.request.RequestQuestionPostDTO;
import com.noteu.noteu.question.dto.response.DetailResponseQuestionPostDTO;
import com.noteu.noteu.question.dto.response.GetAllResponseQuestionPostDTO;
import com.noteu.noteu.question.entity.QuestionComment;
import com.noteu.noteu.question.entity.QuestionPost;
import com.noteu.noteu.question.repository.QuestionCommentRepository;
import com.noteu.noteu.question.repository.QuestionPostRepository;
import com.noteu.noteu.question.service.QuestionPostService;
import com.noteu.noteu.subject.entity.Subject;
import com.noteu.noteu.subject.repository.SubjectRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class QuestionPostServiceImpl implements QuestionPostService {

    private final QuestionPostRepository questionPostRepository;
    private final QuestionCommentRepository questionCommentRepository;
    private final QuestionPostConverterImpl questionPostConverter;
    private final MemberRepository memberRepository;
    private final SubjectRepository subjectRepository;

    @Override
    public void save(RequestQuestionPostDTO requestQuestionPostDTO, Long subjectId, Long memberId) {

        QuestionPostDTO questionPostDTO = questionPostConverter.addRequestQuestionPostDtoToQuestionPostDto(requestQuestionPostDTO);
        questionPostDTO.setSubject(subjectRepository.getReferenceById(subjectId));
        questionPostDTO.setMember(memberRepository.getReferenceById(memberId));

        QuestionPost questionPost = questionPostConverter.questionPostDtoToQuestionPostEntity(questionPostDTO);
        questionPostRepository.save(questionPost);
    }

    @Transactional(readOnly = true)
    @Override
    public DetailResponseQuestionPostDTO getById(Long questionPostId) {

        QuestionPost questionPost = questionPostRepository.findById(questionPostId)
                .orElseThrow(() -> new EntityNotFoundException("Entity가 존재하지 않습니다." + questionPostId));

        List<QuestionComment> commentList = questionCommentRepository.findByQuestionPost(questionPost);
        DetailResponseQuestionPostDTO detailResponseQuestionPostDTO =
                questionPostConverter.toDetailResponseQuestionPostDto(questionPost, commentList);

        return detailResponseQuestionPostDTO;
    }

    @Transactional(readOnly = true)
    @Override
    public List<GetAllResponseQuestionPostDTO> getAll() {

        List<QuestionPost> entityList = questionPostRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        List<GetAllResponseQuestionPostDTO> dtoList = new ArrayList<>();
        for (QuestionPost questionPost : entityList) {
            GetAllResponseQuestionPostDTO getAllResponseQuestionPostDTO = questionPostConverter.toGetAllResponseReferenceRoomDTO(questionPost);
            dtoList.add(getAllResponseQuestionPostDTO);
        }

        return dtoList;
    }

    @Override
    public void updateById(RequestQuestionPostDTO requestQuestionPostDTO, Long questionPostId) {
        QuestionPost questionPost = questionPostRepository.findById(questionPostId)
                .orElseThrow(() -> new EntityNotFoundException("Entity가 존재하지 않습니다" + questionPostId));

        String newTitle = requestQuestionPostDTO.getQuestionPostTitle();
        String newContent = requestQuestionPostDTO.getQuestionPostContent();

        questionPost.update(newTitle, newContent);
    }

    @Override
    public void deleteById(Long questionPostId) {
        questionPostRepository.deleteById(questionPostId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<RecentQuestionDto> getRecentQuestionList(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        // 최근 작성된 글 5개
        Pageable pageable = PageRequest.of(0, 5);
        List<QuestionPost> questionPostList = questionPostRepository.findByMemberOrderByCreatedAtDesc(member, pageable);

        List<RecentQuestionDto> recentQuestionList = new ArrayList<>();
        for (QuestionPost questionPost : questionPostList) {
            Subject subject = questionPost.getSubject();
            List<QuestionComment> questionCommentList = questionCommentRepository.findByQuestionPost(questionPost);

            RecentQuestionDto recentQuestionDto = RecentQuestionDto.builder()
                    .subjectId(subject.getId())
                    .questionId(questionPost.getId())
                    .subjectName(subject.getSubjectName())
                    .questionPostTitle(questionPost.getQuestionPostTitle())
                    .questionPostContent(questionPost.getQuestionPostContent())
                    .commentCount(questionCommentList.size())
                    .createdAt(questionPost.getCreatedAt())
                    .modifiedAt(questionPost.getModifiedAt())
                    .build();
            recentQuestionList.add(recentQuestionDto);
        }

        return recentQuestionList;
    }
}
