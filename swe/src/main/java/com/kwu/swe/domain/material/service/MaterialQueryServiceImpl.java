package com.kwu.swe.domain.material.service;

import com.kwu.swe.domain.material.entity.Material;
import com.kwu.swe.domain.material.repository.MaterialRepository;
import com.kwu.swe.presentation.payload.code.ErrorStatus;
import com.kwu.swe.presentation.payload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MaterialQueryServiceImpl implements MaterialQueryService{

    private final MaterialRepository materialRepository;

    @Override
    public List<Material> getMaterialByLectureId(Long lectureId) {
        return materialRepository.findByLectureId(lectureId);
    }

    @Override
    public Material getSpecificMaterial(Long materialId) {
        return materialRepository.findById(materialId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MATERIAL_NOT_FOUND));
    }
}
