package com.anytime.studymaker.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Accessors(chain = true)
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
