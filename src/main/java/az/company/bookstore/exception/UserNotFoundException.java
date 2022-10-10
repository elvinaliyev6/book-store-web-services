package az.company.bookstore.exception;

import az.company.bookstore.enums.ErrorCodeEnum;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getMessage());
    }



}
