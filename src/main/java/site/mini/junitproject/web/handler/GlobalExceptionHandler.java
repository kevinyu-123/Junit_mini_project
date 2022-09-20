package site.mini.junitproject.web.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import site.mini.junitproject.dto.response.CmResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)

    public ResponseEntity<?> apiException(RuntimeException e){
        return new ResponseEntity<>(CmResponseDto.builder().code(2).msg(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }

}
