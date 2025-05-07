package com.kwu.swe.domain.announcement.entity;

import com.kwu.swe.domain.lecture.entity.Lecture;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "announcement")
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 공지사항 고유 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    private Lecture lecture; // 강의 정보

    private String title; // 공지사항 제목
    private String content; // 공지사항 내용

    /*@Column(name = "created_at")
    private LocalDateTime createdAt; // 공지사항 작성 시간

    // 공지사항 생성 시 현재 시간이 자동으로 입력되도록 설정
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }*/
}
