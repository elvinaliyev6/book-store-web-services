package az.company.bookstore.exception;

import az.company.bookstore.enums.ErrorCodeEnum;

public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException(String message) {
        super(message);
    }

    public BookNotFoundException(ErrorCodeEnum errorCodeEnum){
        super(errorCodeEnum.getMessage());
    }


}
