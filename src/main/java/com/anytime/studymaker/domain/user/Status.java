package com.anytime.studymaker.domain.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
public class Status {
    @Id
    @GeneratedValue
    private Long statusId;
    private String statusName;

    @OneToMany(mappedBy = "status")
    List<UserStudy> userStudyList = new ArrayList<>();
}
