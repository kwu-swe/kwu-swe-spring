package com.kwu.swe.domain.announcement.repository;

import com.kwu.swe.domain.announcement.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    List<Announcement> findAllByLectureId(Long lectureId);
}
