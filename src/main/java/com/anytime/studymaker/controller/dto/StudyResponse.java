package com.anytime.studymaker.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class StudyResponse {
    private Long studyId;
    private String studyName;
    private Integer studyMaximum;
    private String studySummary;
    private String studyDescription;
    private String studyImage;
    private Boolean studyStatus;
    private Boolean studyType;

    private Long managerId;
    private String manager;
    private Integer numberOfMember;

    private Long regionId;
    private String region;

    private Long categoryId;
    private String category;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
