package com.noteu.noteu.question.service;


import com.noteu.noteu.question.dto.request.RequestQuestionPostDTO;
import com.noteu.noteu.question.dto.response.DetailResponseQuestionPostDTO;
import com.noteu.noteu.question.dto.response.GetAllResponseQuestionPostDTO;

import java.util.List;

public interface QuestionPostService {

    void save(RequestQuestionPostDTO RequestQuestionPostDTO, Long subjectId, Long memberId);

    DetailResponseQuestionPostDTO getById(Long questionPostId);

    List<GetAllResponseQuestionPostDTO> getAll();

    void updateById(RequestQuestionPostDTO requestQuestionPostDTO, Long questionPostId);

    void deleteById(Long questionPostId);


}
