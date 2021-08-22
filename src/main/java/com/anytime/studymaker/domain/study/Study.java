package com.anytime.studymaker.domain.study;

import com.anytime.studymaker.controller.dto.StudyRequest;
import com.anytime.studymaker.controller.dto.StudyResponse;
import com.anytime.studymaker.domain.category.Category;
import com.anytime.studymaker.domain.userStudy.UserStudy;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Accessors(chain = true)
@ToString(exclude = "userStudyList")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Study {
    @Id
    @GeneratedValue
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

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    private Region region;

    @OneToMany(mappedBy = "study", cascade = CascadeType.ALL)
    List<UserStudy> userStudyList = new ArrayList<>();

    public StudyResponse toApiResponse() {
        UserStudy managerInfo = userStudyList.get(0);
        int numberOfMember = 0;

        return StudyResponse.builder()
                .studyId(studyId).studyName(studyName).studyMaximum(studyMaximum)
                .studySummary(studySummary).studyDescription(studyDescription)
                .studyImage(studyImage).studyStatus(studyStatus).studyType(studyType)

                .managerId(managerInfo.getUser().getUserId())
                .manager(managerInfo.getUser().getNickname())
                .numberOfMember(numberOfMember)

                .categoryId(category.getCategoryId())
                .category(category.getCategoryName())

                .regionId(region.getRegionId())
                .region(region.getRegionName())

                .createAt(createAt)
                .updateAt(updateAt).build();
    }

    public void update(StudyRequest request) {
        this.category = Category.builder().categoryId(request.getCategoryId()).build();
        this.studyName = request.getStudyName();
        this.studyMaximum = request.getStudyMaximum();
        this.studySummary = request.getStudySummary();
        this.studyDescription =request.getStudyDescription();
        this.studyImage = request.getStudyImage();
        this.studyStatus = request.getStudyStatus();
        this.studyType = request.getStudyType();
    }
}
