package com.anytime.studymaker.controller.dto;

import com.anytime.studymaker.domain.category.Category;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.anytime.studymaker.domain.study.Study;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Data
public class StudyRequest {
    private Long studyId;
    private String studyName;
    private Integer studyMaximum;
    private String studySummary;
    private String studyDescription;
    private String studyImage;
    private Boolean studyStatus;
    private Boolean studyType;

    private Long userId;
    private Long regionId;
    private Long categoryId;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public Study toEntity() {
        return Study.builder().studyName(studyName).studyMaximum(studyMaximum).studySummary(studySummary)
                .studyDescription(studyDescription).studyImage(studyImage).studyStatus(studyStatus).studyType(studyType)
                .userStudyList(new ArrayList<>()).createAt(LocalDateTime.now()).build();
    }
}
