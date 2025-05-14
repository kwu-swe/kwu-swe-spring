package com.kwu.swe.domain.assignment.entity;

import com.kwu.swe.domain.lecture.entity.Lecture;
import com.kwu.swe.domain.submission.entity.SubmissionFile;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "assignment")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;  // 과제 제목

    @Column(nullable = false)
    private String content;  // 과제 내용

    @Column(nullable = false)
    @Builder.Default
    private LocalDateTime dueDate;  // 마감일

    @OneToMany(mappedBy = "assignment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AssignmentFile> files = new ArrayList<>(); // 파일 목록

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id", nullable = false)   // lecture 테이블과 연결
    private Lecture lecture;           // 해당 과제가 속한 강의

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
