package com.anytime.studymaker.domain.study;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Region {
    @Id
    @GeneratedValue
    private Long regionId;
    private String regionName;

    @OneToMany(mappedBy = "region")
    List<Study> studyList = new ArrayList<>();
}
