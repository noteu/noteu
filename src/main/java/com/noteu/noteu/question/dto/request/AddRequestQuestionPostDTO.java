package com.noteu.noteu.question.dto.request;

import com.noteu.noteu.member.entity.Member;
import com.noteu.noteu.subject.entity.Subject;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddRequestQuestionPostDTO {

    private String questionPostTitle;

    private String questionPostContent;
}
