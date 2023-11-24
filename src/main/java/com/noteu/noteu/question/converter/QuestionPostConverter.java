package com.noteu.noteu.question.converter;

import com.noteu.noteu.question.dto.QuestionCommentDTO;
import com.noteu.noteu.question.dto.QuestionPostDTO;
import com.noteu.noteu.question.dto.request.AddRequestQuestionPostDTO;
import com.noteu.noteu.question.dto.response.DetailResponseQuestionPostDTO;
import com.noteu.noteu.question.dto.response.GetAllResponseQuestionPostDTO;
import com.noteu.noteu.question.entity.QuestionComment;
import com.noteu.noteu.question.entity.QuestionPost;
import com.noteu.noteu.reference.dto.ReferenceDTO;
import com.noteu.noteu.reference.dto.response.GetAllResponseReferenceRoomDTO;
import com.noteu.noteu.reference.entity.Reference;

import java.util.List;
import java.util.stream.Collectors;

public interface QuestionPostConverter {

    /**
     *
     * @param addRequestQuestionPostDTO
     * @return QuestionPostDTO
     */
    default QuestionPostDTO addRequestQuestionPostDtoToQuestionPostDto(AddRequestQuestionPostDTO addRequestQuestionPostDTO) {
        return QuestionPostDTO.builder()
                .questionPostTitle(addRequestQuestionPostDTO.getQuestionPostTitle())
                .questionPostContent(addRequestQuestionPostDTO.getQuestionPostContent())
                .build();
    }

    /**
     *
     * @param questionPostDTO
     * @return QuestionPost (Entity)
     */
    default QuestionPost questionPostDtoToQuestionPostEntity(QuestionPostDTO questionPostDTO) {
        return QuestionPost.builder()
                .subject(questionPostDTO.getSubject())
                .member(questionPostDTO.getMember())
                .questionPostTitle(questionPostDTO.getQuestionPostTitle())
                .questionPostContent(questionPostDTO.getQuestionPostContent())
                .build();
    }

    /**
     *
     * @param questionComment
     * @return QuestionCommentDTO
     */
    default QuestionCommentDTO questionCommentEntityToQuestionCommentDTO(QuestionComment questionComment) {
        return QuestionCommentDTO.builder()
                .id(questionComment.getId())
                .questionPost(questionComment.getQuestionPost())
                .member(questionComment.getMember())
                .questionCommentContent(questionComment.getQuestionCommentContent())
                .createdAt(questionComment.getCreatedAt())
                .modifiedAt(questionComment.getModifiedAt())
                .build();
    }

    /**
     *
     * @param questionPost
     * @param questionComments
     * @return DetailResponseQuestionPostDTO
     */
    default DetailResponseQuestionPostDTO toDetailResponseQuestionPostDto(QuestionPost questionPost, List<QuestionComment> questionComments) {

        List<QuestionComment> matchingReferences = questionComments.stream()
                .filter(questionComment -> questionComment.getQuestionPost().getId() == questionPost.getId())
                .toList();

        List<QuestionCommentDTO> questionCommentDTOList = matchingReferences.stream()
                .map(this::questionCommentEntityToQuestionCommentDTO)
                .collect(Collectors.toList());

        return DetailResponseQuestionPostDTO.builder()
                .questionPostId(questionPost.getId())
                .subject(questionPost.getSubject())
                .memberId(questionPost.getMember().getId())
                .memberName(questionPost.getMember().getMemberName())
                .questionPostTitle(questionPost.getQuestionPostTitle())
                .questionPostContent(questionPost.getQuestionPostContent())
                .comment(questionCommentDTOList)
                .createdAt(questionPost.getCreatedAt())
                .modifiedAt(questionPost.getModifiedAt())
                .build();
    }

    default GetAllResponseQuestionPostDTO toGetAllResponseReferenceRoomDTO(QuestionPost questionPost) {
        return GetAllResponseQuestionPostDTO.builder()
                .questionPostId(questionPost.getId())
                .subject(questionPost.getSubject())
                .memberId(questionPost.getMember().getId())
                .memberName(questionPost.getMember().getMemberName())
                .questionPostTitle(questionPost.getQuestionPostTitle())
                .createdAt(questionPost.getCreatedAt())
                .build();
    }
}
