package az.company.bookstore.controller;

import az.company.bookstore.enums.ErrorCodeEnum;
import az.company.bookstore.exception.AuthorNotFoundException;
import az.company.bookstore.exception.BookNotFoundException;
import az.company.bookstore.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleBookNotFoundException() {
        return ErrorResponse
                .builder()
                .code(ErrorCodeEnum.BOOK_NOT_FOUND.getCode())
                .message(ErrorCodeEnum.BOOK_NOT_FOUND.getMessage())
                .build();
    }

    @ExceptionHandler(AuthorNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleAuthorNotFoundException(){
        return ErrorResponse.builder()
                .code(ErrorCodeEnum.AUTHOR_NOT_FOUND.getCode())
                .message(ErrorCodeEnum.AUTHOR_NOT_FOUND.getMessage())
                .build();
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleUnknownException(Exception ex) {
        ex.printStackTrace();
        return ErrorResponse
                .builder()
                .code(ErrorCodeEnum.UNKNOWN_ERROR.getCode())
                .message(ErrorCodeEnum.UNKNOWN_ERROR.getMessage())
                .build();
    }

}
