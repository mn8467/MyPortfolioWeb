package com.example.toyproject1_wst.ExceptionUtil;

public class UserIdDuplicateException extends DuplicateException {

    public UserIdDuplicateException(final String userId) {
        super(userId, ErrorCode.USERNAME_DUPLICATE);
    }
}
