package com.kwu.swe.domain.lecture_plan.service;

import com.kwu.swe.domain.announcement.dto.AnnouncementRequestDto;
import com.kwu.swe.domain.announcement.entity.Announcement;
import com.kwu.swe.domain.lecture.entity.Lecture;
import com.kwu.swe.domain.lecture.repository.LectureRepository;
import com.kwu.swe.domain.lecture_plan.dto.RegisterPlanRequestDto;
import com.kwu.swe.domain.lecture_plan.entity.LecturePlan;
import com.kwu.swe.domain.lecture_plan.repository.LecturePlanRepository;
import com.kwu.swe.domain.user.entity.Role;
import com.kwu.swe.domain.user.entity.User;
import com.kwu.swe.domain.user.repository.UserRepository;
import com.kwu.swe.presentation.payload.code.ErrorStatus;
import com.kwu.swe.presentation.payload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class LecturePlanCommandServiceImpl implements LecturePlanCommandService {

    private final UserRepository userRepository;
    private final LectureRepository lectureRepository;
    private final LecturePlanRepository lecturePlanRepository;

    @Override
    public Long registerPlan(RegisterPlanRequestDto registerPlanRequestDto, String code, Long lectureId) {

        User user = userRepository.findUserByCode(code)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

        if (!user.getRole().equals(Role.ROLE_PROFESSOR))
            throw new GeneralException(ErrorStatus.ONLY_TOUCHED_BY_PROFESSOR);

        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.LECTURE_NOT_FOUND));

        //SAVE
        LecturePlan build = LecturePlan.builder()
                .lecture(lecture)
                .description(registerPlanRequestDto.getDescription())
                .goal(registerPlanRequestDto.getGoal())
                .build();

        lecturePlanRepository.save(build);

        return build.getId();
    }
}
