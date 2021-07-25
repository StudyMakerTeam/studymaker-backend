package com.anytime.studymaker.controller.dto;

import com.anytime.studymaker.domain.category.Category;
import com.anytime.studymaker.domain.study.Region;
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
        Category category = Category.builder().categoryId(categoryId).build();
        Region region = Region.builder().regionId(regionId).build();

        return Study.builder().studyName(studyName).studyMaximum(studyMaximum).studySummary(studySummary)
                .studyDescription(studyDescription).studyImage(studyImage).studyStatus(studyStatus).studyType(studyType)
                .category(category).region(region).createAt(LocalDateTime.now()).build();
    }
}
