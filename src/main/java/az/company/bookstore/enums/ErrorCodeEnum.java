package az.company.bookstore.enums;

public enum ErrorCodeEnum {

    NOT_FOUND(1001, "Can not find "),
    VALIDATION_ERROR(1002, " is not valid"),
    ACCESS_DENIED(1003, "Access is denied"),
    INVALID_REQUEST_DATA(1005,"Invalid request data"),
    UNKNOWN_ERROR(1005, "Unknown error");


    private final String message;

    private final int code;

    ErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
