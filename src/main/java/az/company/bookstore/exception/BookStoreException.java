package az.company.bookstore.exception;

import az.company.bookstore.enums.ErrorCodeEnum;

public class BookStoreException extends RuntimeException{

    public BookStoreException(String message){
        super(message);
    }

    public BookStoreException(ErrorCodeEnum errorCodeEnum){
        super(errorCodeEnum.getMessage());
    }

}
