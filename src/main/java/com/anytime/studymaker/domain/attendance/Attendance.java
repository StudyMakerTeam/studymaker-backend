package com.anytime.studymaker.domain.attendance;

import com.anytime.studymaker.domain.userStudy.UserStudy;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Attendance {
    @Id
    @GeneratedValue
    private Long attendanceId;
    private String attendanceStatus;
    private LocalDate attendaneDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserStudy userStudy;
}
