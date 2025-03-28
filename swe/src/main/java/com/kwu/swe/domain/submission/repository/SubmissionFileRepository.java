package com.kwu.swe.domain.submission.repository;

import com.kwu.swe.domain.submission.entity.SubmissionFile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SubmissionFileRepository extends JpaRepository<SubmissionFile, Long> {

    // submissionId로 파일을 찾는 쿼리 메서드 추가
    Optional<SubmissionFile> findBySubmissionId(Long submissionId);
}
