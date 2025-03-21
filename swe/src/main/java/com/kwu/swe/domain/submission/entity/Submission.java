package com.kwu.swe.domain.submission.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "submission")
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // 과제와 N:1 관계
    @JoinColumn(name = "assignment_id", nullable = false)
    private Assignment assignment;

    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private SubmissionStatus status;

    @Column(name = "submitted_at", nullable = false)
    private LocalDateTime submittedAt;  // 제출한 시간

    // 제출 상태 업데이트 메서드
    public void updateStatus(SubmissionStatus newStatus) {
        this.status = newStatus;
    }
}
