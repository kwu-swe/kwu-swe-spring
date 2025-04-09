package com.kwu.swe.domain.user.entity;

import com.kwu.swe.domain.user.dto.EditUserInfoRequestDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String name;

    private String password;

    @Column(nullable = false, unique = true)
    private String studentNumber;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;


    public void updateInfo(EditUserInfoRequestDto dto) {
        this.password = dto.getPassword();
        this.phoneNumber = dto.getPhoneNumber();
    }
}
