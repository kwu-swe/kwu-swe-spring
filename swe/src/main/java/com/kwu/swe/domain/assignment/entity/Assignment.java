package com.kwu.swe.domain.assignment.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "assignment")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;  // 과제 제목

    @Column(nullable = false)
    private String content;  // 과제 내용

    @Column(nullable = false)
    private LocalDateTime dueDate;  // 마감일
}
