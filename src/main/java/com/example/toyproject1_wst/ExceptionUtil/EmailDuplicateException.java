package com.example.toyproject1_wst.ExceptionUtil;

public class EmailDuplicateException extends DuplicateException {

    public EmailDuplicateException(final String email) {
        super(email, ErrorCode.EMAIL_DUPLICATE);
    }
}