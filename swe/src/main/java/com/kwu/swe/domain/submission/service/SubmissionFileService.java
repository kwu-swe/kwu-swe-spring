package com.kwu.swe.domain.submission.service;

import com.kwu.swe.domain.submission.entity.SubmissionFile;
import com.kwu.swe.domain.submission.repository.SubmissionFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubmissionFileService {

    private final SubmissionFileRepository submissionFileRepository;

    // 파일 다운로드 기능
    public byte[] downloadFile(Long submissionId) {
        Optional<SubmissionFile> submissionFileOptional = submissionFileRepository.findBySubmissionId(submissionId);

        // 파일이 없으면 예외 처리
        return submissionFileOptional
                .map(SubmissionFile::getFileData)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 파일이 존재하지 않습니다."));
    }
}
