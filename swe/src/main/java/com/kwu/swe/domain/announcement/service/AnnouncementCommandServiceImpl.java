package com.kwu.swe.domain.announcement.service;

import com.kwu.swe.domain.announcement.dto.AnnouncementRequestDto;
import com.kwu.swe.domain.announcement.entity.Announcement;
import com.kwu.swe.domain.announcement.entity.AnnouncementFile;
import com.kwu.swe.domain.announcement.repository.AnnouncementFileRepository;
import com.kwu.swe.domain.announcement.repository.AnnouncementRepository;
import com.kwu.swe.domain.lecture.entity.Lecture;
import com.kwu.swe.domain.lecture.repository.LectureRepository;
import com.kwu.swe.domain.user.entity.Role;
import com.kwu.swe.domain.user.entity.User;
import com.kwu.swe.domain.user.repository.UserRepository;
import com.kwu.swe.presentation.payload.code.ErrorStatus;
import com.kwu.swe.presentation.payload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AnnouncementCommandServiceImpl implements AnnouncementCommandService{

    private final AnnouncementRepository announcementRepository;
    private final AnnouncementFileRepository announcementFileRepository;
    private final UserRepository userRepository;
    private final LectureRepository lectureRepository;
    @Override
    public Long registerAnnouncement(Long lectureId, String code, AnnouncementRequestDto announcementRequestDto) {
        User user = userRepository.findUserByCode(code)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));
        if(!user.getRole().equals(Role.ROLE_PROFESSOR))
            throw new GeneralException(ErrorStatus.ONLY_TOUCHED_BY_PROFESSOR);
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.LECTURE_NOT_FOUND));
        //SAVE
        Announcement build = Announcement.builder()
                .title(announcementRequestDto.getTitle())
                .content(announcementRequestDto.getContent())
                .lecture(lecture)
                .build();
        announcementRepository.save(build);
        // SAVE FILES
        List<AnnouncementFile> files = new ArrayList<>();
        announcementRequestDto.getEncodedFiles().forEach(
                fileURL -> {
                    files.add(AnnouncementFile.builder()
                            .announcement(build)
                            .encodedResult(fileURL)
                            .build());
                }
        );
        announcementFileRepository.saveAll(files);
        return build.getId();
    }
}
