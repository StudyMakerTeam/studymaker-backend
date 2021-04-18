package com.anytime.studymaker.domain.study;

import com.anytime.studymaker.domain.user.UserStudy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Accessors(chain = true)
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

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    private Region region;

    @OneToMany(mappedBy = "study")
    List<UserStudy> userStudyList = new ArrayList<>();

}
