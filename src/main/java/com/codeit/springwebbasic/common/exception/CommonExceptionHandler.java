package com.codeit.springwebbasic.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionHandler {

    // Controller 단에서 발생하는 모든 예외를 일괄 처리하는 클래스
    // 실제 예외는 Service 계층에서 대부분 발생하지만, 따로 예외 처리가 없는 경우
    // 메서드를 호출한 상위 계층으로 전파됨

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> illegalArgsHandler(IllegalArgumentException e) {
        e.printStackTrace();
        // 예외의 원인을 http 상태 코드와 메시지를 통해서 알려주고 싶다 -> ResponseEntity
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<?> illegalStateHandler(IllegalArgumentException e) {
        e.printStackTrace();
        // 예외의 원인을 http 상태 코드와 메시지를 통해서 알려주고 싶다 -> ResponseEntity
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandler(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
