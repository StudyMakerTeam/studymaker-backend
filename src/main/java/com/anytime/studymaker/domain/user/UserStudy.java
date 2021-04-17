package com.anytime.studymaker.domain.user;

import com.anytime.studymaker.domain.study.Study;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Builder
@Accessors(chain = true)
@Entity
public class UserStudy {
    @Id
    @GeneratedValue
    private Long userStudyId;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Study study;

    @ManyToOne(fetch = FetchType.LAZY)
    private Status status;

    @OneToMany(mappedBy = "userStudy")
    List<Attendance> attendanceList = new ArrayList<>();
}
