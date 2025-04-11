package com.kwu.swe.domain.submission.repository;

import com.kwu.swe.domain.submission.entity.SubmissionFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmissionFileRepository extends JpaRepository<SubmissionFile, Long> {
    List<SubmissionFile> findBySubmissionId(Long submissionId); // 특정 Submission에 대한 파일 조회
}
