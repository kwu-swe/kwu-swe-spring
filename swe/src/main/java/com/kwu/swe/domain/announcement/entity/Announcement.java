package com.kwu.swe.domain.announcement.entity;

import com.kwu.swe.domain.auditing.entity.BaseTimeEntity;
import com.kwu.swe.domain.lecture.entity.Lecture;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
@Table(name = "announcement")
public class Announcement extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "announcement_id")
    private Long id; // 공지사항 고유 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    private Lecture lecture; // 강의 정보

    private String title; // 공지사항 제목
    private String content; // 공지사항 내용

    @OneToMany(mappedBy = "announcement", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AnnouncementFile> announcementFileList = new ArrayList<>();

}
