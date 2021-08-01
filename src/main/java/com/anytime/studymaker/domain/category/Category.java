package com.anytime.studymaker.domain.category;

import com.anytime.studymaker.controller.dto.CategoryResponse;
import com.anytime.studymaker.domain.study.Study;
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
@ToString(exclude = "studyList")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Category {
    @Id
    @GeneratedValue
    private Long categoryId;
    private String categoryName;

    @OneToMany(mappedBy = "category")
    List<Study> studyList = new ArrayList<>();

    public CategoryResponse toDto() {
        return CategoryResponse.builder().categoryId(this.categoryId).categoryName(this.categoryName).build();
    }
}
