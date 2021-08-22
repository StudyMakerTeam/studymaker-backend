package com.anytime.studymaker.controller.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Data
public class ReplyRequest {
    private Long replyId;
    private String replyContent;
    private Long userId;
    private Long studyBoardId;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
