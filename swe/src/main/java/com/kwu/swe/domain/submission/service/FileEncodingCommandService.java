package com.kwu.swe.domain.submission.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileEncodingCommandService {

    String encodeFile(MultipartFile file) throws IOException;

    //TODO C, U 기능은 Long type으로 반환
    Long saveFileToDB(Long submissionId, String fileName, String encodedResult) throws IOException;
}
