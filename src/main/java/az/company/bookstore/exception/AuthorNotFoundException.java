package az.company.bookstore.exception;

import az.company.bookstore.enums.ErrorCodeEnum;

public class AuthorNotFoundException extends RuntimeException{

    public AuthorNotFoundException(String message) {
        super(message);
    }

    public AuthorNotFoundException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getMessage());
    }
}
