package com.kwu.swe.domain.material.service;


import com.kwu.swe.domain.assignment.entity.Assignment;
import com.kwu.swe.domain.lecture.entity.Lecture;
import com.kwu.swe.domain.lecture.repository.LectureRepository;
import com.kwu.swe.domain.material.dto.MaterialRequestDto;
import com.kwu.swe.domain.material.entity.Material;
import com.kwu.swe.domain.material.entity.MaterialFile;
import com.kwu.swe.domain.material.repository.MaterialFileRepository;
import com.kwu.swe.domain.material.repository.MaterialRepository;
import com.kwu.swe.domain.user.entity.Role;
import com.kwu.swe.domain.user.entity.User;
import com.kwu.swe.domain.user.repository.UserRepository;
import com.kwu.swe.presentation.payload.code.ErrorStatus;
import com.kwu.swe.presentation.payload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MaterialCommandServiceImpl implements MaterialCommandService{
    
    private final MaterialRepository materialRepository;
    private final MaterialFileRepository materialFileRepository;
    private final UserRepository userRepository;
    private final LectureRepository lectureRepository;
    @Override
    public Long registerMaterial(Long lectureId, String code, MaterialRequestDto materialRequestDto) {
        User user = userRepository.findUserByCode(code)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));
        if(!user.getRole().equals(Role.ROLE_PROFESSOR))
            throw new GeneralException(ErrorStatus.ONLY_TOUCHED_BY_PROFESSOR);
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.LECTURE_NOT_FOUND));
        //SAVE
        Material build = Material.builder()
                .title(materialRequestDto.getTitle())
                .content(materialRequestDto.getContent())
                .lecture(lecture)
                .build();
        materialRepository.save(build);
        // SAVE FILES
        List<MaterialFile> files = new ArrayList<>();
        materialRequestDto.getEncodedFiles().forEach(
                fileURL -> {
                    files.add(MaterialFile.builder()
                            .material(build)
                            .encodedURL(fileURL)
                            .build());
                }
        );
        materialFileRepository.saveAll(files);
        return build.getId();
    }

    public void deleteMaterial(Long materialId) {
        Material material = materialRepository.findById(materialId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MATERIAL_NOT_FOUND));

        materialRepository.delete(material);
    }
}
