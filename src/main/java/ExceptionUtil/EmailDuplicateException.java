package ExceptionUtil;

public class EmailDuplicateException extends DuplicateException {

    public EmailDuplicateException(final String email) {
        super(email, ErrorCode.EMAIL_DUPLICATE);
    }
}