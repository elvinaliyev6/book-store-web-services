package az.company.bookstore.exception;

import az.company.bookstore.enums.ErrorCodeEnum;

public class CustomNotFoundException extends RuntimeException{

    public CustomNotFoundException(ErrorCodeEnum errorCodeEnum){
        super(errorCodeEnum.getMessage());
    }

    public CustomNotFoundException(String message){
        super(message);
    }

}
