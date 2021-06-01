package com.anytime.studymaker.domain.study.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

import com.anytime.studymaker.domain.study.Study;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@Data
public class StudyApiRequest {
    private Long userId;
    private String studyName;
    private Integer studyMaximum;
    private String studySummary;
    private String studyDescription;
    private String studyImage;
    private Boolean studyStatus;
    private Boolean studyType;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public Study toEntity() {
        return Study.builder().studyName(studyName).studyMaximum(studyMaximum).studySummary(studySummary)
                .studySummary(studySummary).studyDescription(studyDescription).studyImage(studyImage)
                .studyStatus(studyStatus).studyType(studyType).createAt(LocalDateTime.now()).build();
    }
}
