package com.example.toyproject1_wst.Model;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userCode;

    @Column(unique = true, nullable = false, updatable = false)
    private String userId;

    @Column(nullable = false)
    private String userName;


    @Column(unique = true, nullable = false)
    private String email;


    private LocalDateTime joinDate;
    private LocalDateTime updateDate;
    private String userStatus;


}
