package com.anytime.studymaker.controller.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Data
public class ReplyResponse {
    private Long replyId;
    private String replyContent;
    private Long userId;
    private String userName;
    private String userNickname;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
