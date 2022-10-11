package az.company.bookstore.controller;

import az.company.bookstore.exception.BookStoreException;
import az.company.bookstore.enums.ErrorCodeEnum;
import az.company.bookstore.exception.CustomNotFoundException;
import az.company.bookstore.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(CustomNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleCustomNotException(Exception ex) {
        return ErrorResponse.builder()
                .code(ErrorCodeEnum.NOT_FOUND.getCode())
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        String paramName=ex.getParameter().getParameterName();
        return ErrorResponse
                .builder()
                .code(ErrorCodeEnum.VALIDATION_ERROR.getCode())
                .message(paramName+ErrorCodeEnum.VALIDATION_ERROR.getMessage())
                .build();
    }

    @ExceptionHandler(BookStoreException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBookStoreException(Exception ex){
        return ErrorResponse.builder()
                .code(ErrorCodeEnum.INVALID_REQUEST.getCode())
                .message(ex.getMessage())
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidation(MethodArgumentNotValidException e){
        List<String> fieldErrorNamesList=e.getBindingResult().getFieldErrors().stream().map(fieldError ->fieldError.getField())
                .collect(Collectors.toList());
        return ErrorResponse
                .builder()
                .code(ErrorCodeEnum.VALIDATION_ERROR.getCode())
                .message(fieldErrorNamesList+ErrorCodeEnum.VALIDATION_ERROR.getMessage())
                .build();
    }



}
