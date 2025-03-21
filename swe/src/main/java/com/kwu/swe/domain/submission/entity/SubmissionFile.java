package com.kwu.swe.domain.submission.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "submission_file")
public class SubmissionFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // 제출과 N:1 관계
    @JoinColumn(name = "submission_id", nullable = false)
    private Submission submission;

    @Column(name = "file_name", nullable = false, length = 255)
    private String fileName;
}
