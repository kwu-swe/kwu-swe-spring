package com.kwu.swe.domain.submission.entity;

import com.kwu.swe.domain.assignment.entity.Assignment;
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
    @Column(name = "status")
    private SubmissionStatus status;  // 제출 상태 (미제출, 제출 완료, 지각 제출 등)

    @Column(name = "submitted_at", nullable = false)
    private LocalDateTime submittedAt;  // 제출한 시간

    // 제출 상태 업데이트 메서드
    public void updateStatus(SubmissionStatus newStatus) {
        this.status = newStatus;
    }

    // 제출 메서드: 제출 시 상태를 SUBMITTED로 변경
    public void submit() {
        this.status = SubmissionStatus.SUBMITTED;  // 제출 완료 상태로 변경
        this.submittedAt = LocalDateTime.now();    // 제출 시간 기록
    }

    // 지각 제출 메서드: 제출 상태를 LATE로 변경
    public void submitLate() {
        this.status = SubmissionStatus.LATE;      // 지각 제출 상태로 변경
        this.submittedAt = LocalDateTime.now();   // 제출 시간 기록
    }
}
