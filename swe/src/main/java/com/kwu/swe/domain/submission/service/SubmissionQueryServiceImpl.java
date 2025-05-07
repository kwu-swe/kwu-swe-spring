package com.kwu.swe.domain.submission.service;

import com.kwu.swe.domain.submission.entity.Submission;
import com.kwu.swe.domain.submission.entity.SubmissionFile;
import com.kwu.swe.domain.submission.repository.SubmissionFileRepository;
import com.kwu.swe.domain.submission.repository.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubmissionQueryServiceImpl implements SubmissionQueryService {

    private final SubmissionRepository submissionRepository;
    private final SubmissionFileRepository submissionFileRepository;

    @Override
    public Submission getSubmissionByUserAndAssignment(String studentNumber, Long assignmentId) {
        return submissionRepository.findByUserStudentNumberAndAssignmentId(studentNumber, assignmentId);
    }

    @Override
    public List<SubmissionFile> getSubmissionByUserAndAssignment(Long submissionId) {
        return submissionFileRepository.findBySubmissionId(submissionId);
    }

/*    @Override
    public List<MultipartFile> decodeFileToMultipartFile(Long submissionId) throws IOException {
        // DB에서 SubmissionFile을 조회
        List<SubmissionFile> submissionFiles = submissionFileRepository.findBySubmissionId(submissionId);

        // MultipartFile 리스트 생성
        List<MultipartFile> multipartFiles = new ArrayList<>();

        for (SubmissionFile submissionFile : submissionFiles) {
            // Base64로 인코딩된 문자열을 byte[]로 디코딩
            byte[] decodedBytes = Base64.getDecoder().decode(submissionFile.getEncodedResult());

            // byte[]를 MultipartFile로 변환하여 리스트에 추가
            MultipartFile multipartFile = new MockMultipartFile(
                    "file",  // 파라미터 이름
                    submissionFile.getFileName(),  // 파일 이름
                    "application/octet-stream",  // MIME 타입
                    new ByteArrayInputStream(decodedBytes)  // 디코딩된 byte[]를 InputStream으로
            );

            multipartFiles.add(multipartFile);
        }

        return multipartFiles;
    }*/
}
