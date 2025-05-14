package com.kwu.swe.domain.material.entity;

import com.kwu.swe.domain.announcement.entity.Announcement;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MaterialFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_file_id")
    private Long id;

    private String encodedURL; // 파일의 인코딩 결과

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "material")
    private Material material;
}
