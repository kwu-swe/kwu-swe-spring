package com.kwu.swe.domain.announcement.entity;

import com.kwu.swe.domain.auditing.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnnouncementFile extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "announcement_file_id")
    private Long id;

    private String encodedURL; // 파일의 인코딩 결과

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "announcement_id")
    private Announcement announcement;
}
