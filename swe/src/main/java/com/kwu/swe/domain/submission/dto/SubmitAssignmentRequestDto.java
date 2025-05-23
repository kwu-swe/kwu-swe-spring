package com.kwu.swe.domain.submission.dto;

import com.kwu.swe.domain.assignment.entity.Assignment;
import com.kwu.swe.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubmitAssignmentRequestDto {
    private String title;
    private String content;
    private List<String> encodedFiles;
}
