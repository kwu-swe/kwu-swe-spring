package com.kwu.swe.domain.submission.service;

import com.kwu.swe.domain.submission.entity.Submission;
import com.kwu.swe.domain.submission.entity.SubmissionFile;
import com.kwu.swe.domain.submission.repository.SubmissionFileRepository;
import com.kwu.swe.domain.submission.repository.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
@RequiredArgsConstructor
@Transactional
public class FileEncodingCommandServiceImpl implements FileEncodingCommandService {

    private final SubmissionFileRepository submissionFileRepository;
    private final SubmissionRepository submissionRepository;

    @Override
    public String encodeFile(MultipartFile file) throws IOException {
        // 파일을 byte[]로 변환
        byte[] fileBytes = file.getBytes();

        // Base64로 파일 인코딩
        return Base64.getEncoder().encodeToString(fileBytes);
    }

    //TODO C, U 기능은 Long type으로 반환
    @Override
    public Long saveFileToDB(Long submissionId, String fileName, String encodedResult) throws IOException {

        Submission submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 제출이 존재하지 않습니다."));

        // 제출할 과제에 대한 SubmissionFile 객체 생성
        SubmissionFile submissionfile = SubmissionFile.builder()
                .submission(submission)
                .fileName(fileName) // 제목 업데이트
                .encodedResult(encodedResult) // 내용 업데이트
                .build();

        // 제출 상태 저장
        submissionFileRepository.save(submissionfile);

        return submissionfile.getId();
    }
}

