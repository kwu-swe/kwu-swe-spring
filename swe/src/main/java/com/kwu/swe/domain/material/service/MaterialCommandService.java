package com.kwu.swe.domain.material.service;

import com.kwu.swe.domain.announcement.dto.AnnouncementRequestDto;
import com.kwu.swe.domain.assignment.dto.AssignmentRequestDto;
import com.kwu.swe.domain.material.dto.MaterialRequestDto;

public interface MaterialCommandService {

    Long registerMaterial(Long lectureId, String code, MaterialRequestDto materialRequestDto);

    Long updateMaterial(Long materialId, MaterialRequestDto materialRequestDto);

    void deleteMaterial(Long materialId);
}
