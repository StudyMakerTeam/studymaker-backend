package com.anytime.studymaker.domain.status;

import com.anytime.studymaker.domain.userStudy.UserStudy;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
public class Status {
    @Id
    @GeneratedValue
    private Long statusId;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @OneToMany(mappedBy = "status")
    List<UserStudy> userStudyList = new ArrayList<>();

    public static Status builder() {
        return new Status();
    }

    public Status statusId(Long statusId) {
        this.statusId = statusId;
        return this;
    }

    public Status userStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
        return this;
    }

    public Status build() {
        return this;
    }
}
