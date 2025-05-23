package com.kwu.swe.domain.material.service;


import com.kwu.swe.domain.material.entity.Material;

import java.util.List;

public interface MaterialQueryService {

    List<Material> getMaterialByLectureId(Long lectureId);

    Material getSpecificMaterial(Long materialId);
}
