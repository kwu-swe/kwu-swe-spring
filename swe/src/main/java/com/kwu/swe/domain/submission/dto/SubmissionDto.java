package com.kwu.swe.domain.submission.dto;

import com.kwu.swe.domain.submission.entity.Submission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionDto {

    private Long id;
    private String title;
    private String content;
    private String encodingResult;
    private String status;

    // 엔티티와 DTO를 변환하는 메서드 (optional)
    public static SubmissionDto fromEntity(Submission submission) {
        return SubmissionDto.builder()
                .id(submission.getId())
                .title(submission.getTitle())
                .content(submission.getContent())
                .encodingResult(submission.getEncodingResult())
                .status(submission.getStatus().name()) // SubmissionStatus를 String으로 변환
                .build();
    }
}
