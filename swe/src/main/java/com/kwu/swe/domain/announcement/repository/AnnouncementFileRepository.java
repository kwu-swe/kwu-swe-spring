package com.kwu.swe.domain.announcement.repository;

import com.kwu.swe.domain.announcement.entity.AnnouncementFile;
import com.kwu.swe.domain.submission.entity.SubmissionFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AnnouncementFileRepository extends JpaRepository<AnnouncementFile, Long> {
    List<AnnouncementFile> findByAnnouncementId(Long AnnouncementId); // 특정 Announcement에 대한 파일 조회
}
