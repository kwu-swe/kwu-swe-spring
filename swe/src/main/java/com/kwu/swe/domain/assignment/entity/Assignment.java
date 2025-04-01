package com.kwu.swe.domain.assignment.entity;

import com.kwu.swe.domain.assignment.dto.AssignmentDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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

//    @ManyToOne(fetch = FetchType.LAZY)  // lecture와 N:1 관계
//    @JoinColumn(name = "lecture_id", nullable = false)
//    private Lecture lecture;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "due_date", nullable = false)
    private LocalDateTime dueDate;

    @Column(name = "extended_due_date")
    private LocalDateTime extendedDueDate;  // 연장된 제출 기한

    @Column(name = "allow_submission", nullable = false)
    private boolean allowSubmission;  // 제출 여부

    @Column(name = "is_public", nullable = false)
    private boolean isPublic;  // 과제 공개 여부

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // 과제 생성 시 필요한 메서드
    public void updateAssignment(String title, String content, LocalDateTime dueDate,
                                 LocalDateTime extendedDueDate, boolean allowSubmission, boolean isPublic) {
        this.title = title;
        this.content = content;
        this.dueDate = dueDate;
        this.extendedDueDate = extendedDueDate;
        this.allowSubmission = allowSubmission;
        this.isPublic = isPublic;
    }

    public void updateAssignment(AssignmentDto assignmentDto) {
    }
}
