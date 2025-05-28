package com.kwu.swe.domain.material.controller;

import com.kwu.swe.domain.assignment.dto.AssignmentRequestDto;
import com.kwu.swe.domain.material.dto.MaterialRequestDto;
import com.kwu.swe.domain.material.dto.MaterialResponseDto;
import com.kwu.swe.domain.material.dto.MaterialSummaryDto;
import com.kwu.swe.domain.material.entity.Material;

import com.kwu.swe.domain.material.service.MaterialCommandService;
import com.kwu.swe.domain.material.service.MaterialQueryService;
import com.kwu.swe.presentation.payload.dto.ApiResponseDto;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materials")
@RequiredArgsConstructor
public class MaterialApiController {

    private final MaterialCommandService materialCommandService;
    private final MaterialQueryService materialQueryService;

    @PostMapping("/lectures/{lectureId}")
    public ApiResponseDto<Long> registerMaterial(@PathVariable Long lectureId,
                                                 @Parameter(hidden = true) @AuthenticationPrincipal UserDetails userDetails,
                                                 @RequestBody MaterialRequestDto materialRequestDto) {
        return ApiResponseDto.onSuccess(materialCommandService.registerMaterial(lectureId, userDetails.getUsername(), materialRequestDto));
    }

    @GetMapping("/lectures/{lectureId}")
    public ApiResponseDto<List<MaterialSummaryDto>> getMaterialsByLecture(@PathVariable Long lectureId) {
        List<Material> materials = materialQueryService.getMaterialByLectureId(lectureId);
        List<MaterialSummaryDto> result = materials.stream().map(material ->
                MaterialSummaryDto.builder()
                        .materialId(material.getId())
                        .title(material.getTitle())
                        .createdAt(material.getCreatedAt())
                        .build()
        ).toList();
        return ApiResponseDto.onSuccess(result);
    }

    @GetMapping("/{materialId}")
    public ApiResponseDto<MaterialResponseDto> getSpecificMaterial(@PathVariable Long materialId) {
        Material specificmaterial = materialQueryService.getSpecificMaterial(materialId);
        MaterialResponseDto build = MaterialResponseDto.builder()
                .materialId(materialId)
                .encodedFiles(
                        specificmaterial.getMaterialFileList().stream()
                                .map(
                                        materialFile -> materialFile.getEncodedURL()
                                ).toList()
                )
                .content(specificmaterial.getContent())
                .writer(specificmaterial.getLecture().getProfessor().getName())
                .title(specificmaterial.getTitle())
                .build();
        return ApiResponseDto.onSuccess(build);
    }

    @PutMapping("/{materialId}")
    public ApiResponseDto<Long> updateMaterial(
            @PathVariable Long materialId,
            @RequestBody MaterialRequestDto materialRequestDto) {

        return ApiResponseDto.onSuccess(materialCommandService.updateMaterial(materialId, materialRequestDto));
    }

    @DeleteMapping("/{materialId}")
    public ApiResponseDto<Void> deleteMaterial(@PathVariable Long materialId) {
        materialCommandService.deleteMaterial(materialId);
        return ApiResponseDto.onSuccess(null);
    }
}
