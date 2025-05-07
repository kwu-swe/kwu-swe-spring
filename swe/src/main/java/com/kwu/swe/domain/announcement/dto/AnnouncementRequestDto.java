package com.kwu.swe.domain.announcement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementRequestDto {

    private String userId;
    private Long lectureId; // 공지사항이 속한 강의 ID
    private String title;
    private String content;
    private List<String> encodedFiles;
    private List<String> fileNames;
}
