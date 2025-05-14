package com.kwu.swe.domain.announcement.repository;

import com.kwu.swe.domain.announcement.entity.AnnouncementFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementFileRepository extends JpaRepository<AnnouncementFile, Long> {
}
