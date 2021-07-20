package com.anytime.studymaker.domain.studyBoard;

import com.anytime.studymaker.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Accessors(chain = true)
@Entity
public class StudyBoard {
    @Id
    @GeneratedValue
    private Long studyBoardId;
    private String studyBoardTitle;
    private String StudyBoardContent;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "studyBoard")
    List<Reply> replyList = new ArrayList<>();
}
