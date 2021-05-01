package com.example.restfulwebservice.exception;


import com.example.restfulwebservice.user.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class) //에러 발생 시
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request){ // 해당 핸들러 실행
        // exceptionResponse 객체 생성
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
                // 해당 리스폰스 전송
        return  new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class) //에러 발생 시
    public final ResponseEntity<Object> handleUserNotFoundExceptions(Exception ex, WebRequest request){ // 해당 핸들러 실행
        // exceptionResponse 객체 생성
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        // 해당 리스폰스 전송
        return  new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @Override
    // ResponseEntityExceptionHandler 의  handleMethodArgumentNotValid 를 오버라이드 하여  NotValid 한 상황 발생시
    // ExceptionResponse 생성
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
                "Validation Failed", ex.getBindingResult().toString());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}
