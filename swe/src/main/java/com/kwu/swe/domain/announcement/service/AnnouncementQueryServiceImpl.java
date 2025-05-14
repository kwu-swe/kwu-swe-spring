package com.kwu.swe.domain.announcement.service;

import com.kwu.swe.domain.announcement.entity.Announcement;
import com.kwu.swe.domain.announcement.repository.AnnouncementRepository;
import com.kwu.swe.presentation.payload.code.ErrorStatus;
import com.kwu.swe.presentation.payload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AnnouncementQueryServiceImpl implements AnnouncementQueryService{

    private final AnnouncementRepository announcementRepository;

    @Override
    public List<Announcement> getAnnouncementByLectureId(Long lectureId) {
        return announcementRepository.findAllByLectureId(lectureId);
    }

    @Override
    public Announcement getSpecificAnnouncement(Long announcementId) {
        return announcementRepository.findById(announcementId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.ANNOUNCEMENT_NOT_FOUND));
    }
}
