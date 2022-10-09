package az.company.bookstore.exception;

import az.company.bookstore.enums.ErrorCodeEnum;

public class PublisherNotFoundException extends RuntimeException{

    public PublisherNotFoundException(String message){
        super(message);
    }

    public PublisherNotFoundException(ErrorCodeEnum errorCodeEnum){
        super(errorCodeEnum.getMessage());
    }

}
