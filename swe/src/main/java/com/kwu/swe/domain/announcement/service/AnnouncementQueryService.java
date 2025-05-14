package com.kwu.swe.domain.announcement.service;

import com.kwu.swe.domain.announcement.entity.Announcement;

import java.util.List;

public interface AnnouncementQueryService {

    List<Announcement> getAnnouncementByLectureId(Long lectureId);

    Announcement getSpecificAnnouncement(Long announcementId);
}
