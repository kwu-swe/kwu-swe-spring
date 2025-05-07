package com.kwu.swe.domain.announcement.entity;

import com.kwu.swe.domain.lecture.entity.Lecture;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "announcement")
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "announcement_id")
    private Long id; // 공지사항 고유 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    private Lecture lecture; // 강의 정보

    private String title; // 공지사항 제목
    private String content; // 공지사항 내용

}
