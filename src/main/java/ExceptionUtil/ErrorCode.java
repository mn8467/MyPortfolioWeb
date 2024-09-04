package ExceptionUtil;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE("C-001", "Invalid Input Value"),
    METHOD_NOT_ALLOWED("C-002", "Method Not Allowed"),
    INTERNAL_SERVER_ERROR("C-004", "Server Error"),
    INVALID_TYPE_VALUE("C-005", "Invalid Type Value"),
    HANDLE_ACCESS_DENIED("C-006", "Access is Denied"),

    // Business
    DUPLICATE("B-001", "Duplicate Value"),
    NOT_FOUND("B-002", "Entity Not Found"),

    // Member
    EMAIL_DUPLICATE("M-001", "Duplicate Email Address"),
    USERNAME_DUPLICATE("M-002", "Duplicate Nickname"),
    USERID_DUPLICATE("M-003", "Duplicate UserId");

    // ...

    private final String code;
    private final String message;

    ErrorCode(final String code, final String message) {
        this.code = code;
        this.message = message;
    }
}