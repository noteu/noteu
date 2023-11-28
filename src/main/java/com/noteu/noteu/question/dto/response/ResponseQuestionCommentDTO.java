package com.noteu.noteu.question.dto.response;

import com.noteu.noteu.member.entity.Member;
import com.noteu.noteu.question.entity.QuestionPost;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ResponseQuestionCommentDTO {

    private Long questionCommentId;

    private QuestionPost questionPost;

    private Long memberId;
    
    private String memberName;

    private String profile;

    private String questionCommentContent;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;
}
