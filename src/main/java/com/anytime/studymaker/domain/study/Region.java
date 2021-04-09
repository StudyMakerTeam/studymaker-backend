package com.anytime.studymaker.domain.study;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Accessors(chain = true)
@Entity
public class Region {
    @Id
    @GeneratedValue
    private Long regionId;
    private String regionName;

    @OneToMany(mappedBy = "region")
    List<Study> studyList = new ArrayList<>();
}
