package com.kwu.swe.domain.submission.entity;

import com.kwu.swe.domain.assignment.entity.Assignment;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 기본 생성자 추가
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    @Lob
    private String encodingResult;
    private SubmissionStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignment_id")
    private Assignment assignment;

    public Submission(String content, String title, String encodingResult, SubmissionStatus status) {
        this.content = content;
        this.title = title;
        this.encodingResult = encodingResult;
        this.status = status;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }
}

