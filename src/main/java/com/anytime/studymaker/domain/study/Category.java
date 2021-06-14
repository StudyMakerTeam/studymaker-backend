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
@Builder
@Data
@Accessors(chain = true)
@Entity
public class Category {
    @Id
    @GeneratedValue
    private Long categoryId;
    private String categoryName;

    @OneToMany(mappedBy = "category")
    List<Study> studyList = new ArrayList<>();
}
