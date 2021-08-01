package com.anytime.studymaker.domain.studyBoard;

import com.anytime.studymaker.domain.user.User;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
