package com.anytime.studymaker.domain.study.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@Data
public class StudyApiResponse {
    private Long studyId;
    private String studyName;
    private Integer studyMaximum;
    private String studySummary;
    private String studyDescription;
    private String studyImage;
    private Boolean studyStatus;
    private Boolean studyType;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
