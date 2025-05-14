package com.kwu.swe.domain.announcement.service;

import com.kwu.swe.domain.announcement.dto.AnnouncementRequestDto;
import com.kwu.swe.domain.announcement.entity.Announcement;

public interface AnnouncementCommandService {

    Long registerAnnouncement(Long lectureId, String code, AnnouncementRequestDto announcementRequestDto);
}
