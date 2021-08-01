package com.anytime.studymaker.domain.userStudy;

import com.anytime.studymaker.domain.attendance.Attendance;
import com.anytime.studymaker.domain.study.Study;
import com.anytime.studymaker.domain.user.User;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class UserStudy {
    @Id
    @GeneratedValue
    private Long userStudyId;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Study study;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @OneToMany(mappedBy = "userStudy", cascade = CascadeType.REMOVE)
    List<Attendance> attendanceList = new ArrayList<>();

    
}
