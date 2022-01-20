package app.workout.ErrorHandler.Advice;

import app.workout.ErrorHandler.ErrorResult;
import app.workout.ErrorHandler.TypeMismatchResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice("app.workout.Controller")
public class ApiControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResult> IllegalStateExceptionHandler(IllegalStateException e, HttpServletRequest request){
        ErrorResult errorResult = new ErrorResult(
                LocalDateTime.now(), HttpStatus.BAD_REQUEST.value() , HttpStatus.BAD_REQUEST,
                request.getRequestURI() , e.getMessage(), e.getClass().getName() );
        log.error("[exceptionHandler] e", e);
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ResponseEntity<TypeMismatchResult> TypeMismatchExceptionHandler(TypeMismatchException e, HttpServletRequest request){

        TypeMismatchResult errorResult = new TypeMismatchResult(
                LocalDateTime.now(), HttpStatus.BAD_REQUEST.value() , HttpStatus.BAD_REQUEST ,
                request.getRequestURI() , "타입이 올바르지 않습니다.", e.getClass().getName(),
                e.getRequiredType().getName(), e.getValue().getClass().getName(), e.getValue().toString());
        log.error("[exceptionHandler] e", e);
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResult> ExceptionHandler(Exception e, HttpServletRequest request){
        ErrorResult errorResult = new ErrorResult(
                LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value() , HttpStatus.INTERNAL_SERVER_ERROR ,
                request.getRequestURI() , e.getMessage(), e.getClass().getName() );
        log.error("[exceptionHandler] e", e);
        return new ResponseEntity<>(errorResult, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
