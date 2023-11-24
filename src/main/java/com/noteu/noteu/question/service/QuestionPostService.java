package com.noteu.noteu.question.service;

import com.noteu.noteu.question.dto.QuestionPostDTO;
import com.noteu.noteu.question.dto.request.AddRequestQuestionPostDTO;
import com.noteu.noteu.question.dto.response.DetailResponseQuestionPostDTO;
import com.noteu.noteu.question.dto.response.GetAllResponseQuestionPostDTO;

import java.util.List;

public interface QuestionPostService {

    void save(AddRequestQuestionPostDTO addRequestQuestionPostDTO, Long subjectId, Long memberId);

    DetailResponseQuestionPostDTO getById(Long questionPostId);

    List<GetAllResponseQuestionPostDTO> getAll();

    void deleteById(Long id);


}
