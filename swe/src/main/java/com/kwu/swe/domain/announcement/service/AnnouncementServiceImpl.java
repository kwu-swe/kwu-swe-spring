package com.kwu.swe.domain.announcement.service;

import com.kwu.swe.domain.announcement.dto.AnnouncementRequestDto;
import com.kwu.swe.domain.announcement.entity.Announcement;
import com.kwu.swe.domain.announcement.repository.AnnouncementRepository;
import com.kwu.swe.domain.lecture.entity.Lecture;
import com.kwu.swe.domain.lecture.repository.LectureRepository;
import com.kwu.swe.domain.submission.entity.Submission;
import com.kwu.swe.domain.submission.entity.SubmissionStatus;
import com.kwu.swe.domain.user.entity.User;
import com.kwu.swe.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;
    private final LectureRepository lectureRepository;
    private final UserRepository userRepository;

    /*@Override
    public List<AnnouncementResponseDto> getAnnouncementsByLectureId(Long lectureId) {
        // 해당 강의의 공지사항들을 가져옴
        List<Announcement> announcements = announcementRepository.findByLectureId(lectureId);

        // 엔티티를 DTO로 변환하여 반환
        return announcements.stream()
                .map(announcement -> new AnnouncementResponseDto(announcement.getId(),
                        announcement.getTitle(),
                        announcement.getContent(),
                        announcement.getCreatedAt()))
                .collect(Collectors.toList());
    }*/

    @Override
    @Transactional
    public Long createAnnouncement(Long lectureId, String title, String content) {

        // lectureId를 통해 강의를 조회
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new IllegalArgumentException("강의를 찾을 수 없습니다."));

        // 공지사항 생성
        Announcement announcement = Announcement.builder()

                .title(title)
                .content(content)
                .build();

        // 저장 후 생성된 공지사항의 ID 반환
        Announcement savedAnnouncement = announcementRepository.save(announcement);
        return savedAnnouncement.getId();
    }

    /*@Override
    @Transactional
    public void updateAnnouncement(Long id, AnnouncementRequestDto requestDto) {
        // 수정할 공지사항 조회
        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 공지사항을 찾을 수 없습니다."));

        // 공지사항 수정
        announcement.setTitle(requestDto.getTitle());
        announcement.setContent(requestDto.getContent());

        // 수정된 공지사항 저장
        announcementRepository.save(announcement);
    }*/

    /*@Override
    @Transactional
    public void deleteAnnouncement(Long id) {
        // 삭제할 공지사항 조회
        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 공지사항을 찾을 수 없습니다."));

        // 공지사항 삭제
        announcementRepository.delete(announcement);
    }*/
}
