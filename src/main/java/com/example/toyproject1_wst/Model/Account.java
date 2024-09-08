package com.example.toyproject1_wst.Model;


import com.example.toyproject1_wst.Util.BaseTimeEntity;
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

    @Column(name = "user_id",unique = true, nullable = false, updatable = false)
    private String userId;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "user_name" ,nullable = false)
    private String userName;

    @Column(name = "Password" ,nullable = false)
    private String Password;




    @Column(name = "user_Status", nullable = true)
    private String userStatus;



    @Column(name = "join_date", nullable = true)
    private LocalDateTime joinDate;

    @Column(name = "update_date", nullable = true)
    private LocalDateTime updateDate;

    //엔티티가 처음 만들어질 때 joinDate 시간 대입
    @PrePersist
    public void onPrePersist() {
        this.joinDate = LocalDateTime.now();
    }

    // 엔티티가 수정될 때 updateDate 업데이트
    @PreUpdate
    public void onPreUpdate() {
        this.updateDate = LocalDateTime.now();
    }



}
