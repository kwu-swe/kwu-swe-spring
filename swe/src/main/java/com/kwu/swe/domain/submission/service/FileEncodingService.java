package com.kwu.swe.domain.submission.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
public class FileEncodingService {

    public String encodeFile(MultipartFile file) throws IOException {
        // 파일을 byte[]로 변환
        byte[] fileBytes = file.getBytes();

        // Base64로 파일 인코딩
        return Base64.getEncoder().encodeToString(fileBytes);
    }
}
