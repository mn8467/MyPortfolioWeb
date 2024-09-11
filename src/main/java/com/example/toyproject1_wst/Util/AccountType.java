package com.example.toyproject1_wst.Util;

import lombok.Getter;

@Getter
public enum AccountType {
    ROLE_ADMIN("관리자"),
    ROLE_GUEST("손님"); // 로그인 없이 들어오는 사람

    String value;

    AccountType(String value){
        this.value = value;
    }
}
