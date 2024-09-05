package com.example.toyproject1_wst.Model;

import com.example.toyproject1_wst.Util.Password;
import com.example.toyproject1_wst.Util.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userCode;

    @Column(unique = true, nullable = false, updatable = false)
    private String userId;

    @Column(nullable = false)
    private String userName;

    @AttributeOverride(name = "rawPassword", column = @Column(name = "password", nullable = false))
    @Embedded
    private Password password;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDateTime joinDate;
    private LocalDateTime updateDate;
    private String userStatus;

    @Builder
    public Account(int userCode,String userId,String userName,Password password,String email,
                  Role role) {
        this.userCode = userCode;
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}
