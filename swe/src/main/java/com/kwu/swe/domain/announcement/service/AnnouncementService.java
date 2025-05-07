package com.kwu.swe.domain.announcement.service;

import com.kwu.swe.domain.announcement.dto.AnnouncementRequestDto;
import com.kwu.swe.domain.announcement.dto.AnnouncementResponseDto;

import java.util.List;

public interface AnnouncementService {

    // 공지사항 목록 조회
    //List<AnnouncementResponseDto> getAnnouncementsByLectureId(Long lectureId);

    // 공지사항 생성
    Long createAnnouncement(Long lectureId, String title, String content);

    // 공지사항 수정
    //void updateAnnouncement(Long id, AnnouncementRequestDto requestDto);

    // 공지사항 삭제
    //void deleteAnnouncement(Long id);
}
