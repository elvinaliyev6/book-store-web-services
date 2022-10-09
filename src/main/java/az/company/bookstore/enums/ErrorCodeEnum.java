package az.company.bookstore.enums;

import az.company.bookstore.exception.ExceptionConstants;

public enum ErrorCodeEnum {

    BOOK_NOT_FOUND(ExceptionConstants.NOT_FOUND, "Can not find book given id"),
    AUTHOR_NOT_FOUND(ExceptionConstants.NOT_FOUND,"Can not find author given id"),
    PUBLISHER_NOT_FOUND(ExceptionConstants.NOT_FOUND,"Can not find publisher given id"),
    VALIDATION_ERROR(ExceptionConstants.VALIDATION_ERROR, " is not valid"),
    ACCESS_DENIED(ExceptionConstants.ACCESS_DENIED, "Access is denied"),
    UNKNOWN_ERROR(ExceptionConstants.UNKNOWN_ERROR, "Unknown error");


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
