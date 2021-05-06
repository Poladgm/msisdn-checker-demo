package az.azerconnect.checker.msisdn.advice;


import az.azerconnect.checker.msisdn.exception.ApiError;
import az.azerconnect.checker.msisdn.exception.NotFoundException;
import az.azerconnect.checker.msisdn.exception.RestException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class ExceptionHandlerAdvice{
    private static final Logger logger = LogManager.getLogger(ExceptionHandlerAdvice.class);

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFoundException(NotFoundException notFoundException){
        logger.error(notFoundException.getApiError());
        return ResponseEntity.status(notFoundException.getHttpStatus()).body(notFoundException.getApiError());
    }

    @ExceptionHandler(RestException.class)
    public ResponseEntity<ApiError> handleRestException(RestException restException){
        logger.error(restException.getApiError());
        return ResponseEntity.status(restException.getHttpStatus()).body(restException.getApiError());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex){
        ObjectError error = ex.getBindingResult().getAllErrors().stream().findAny().orElse(null);
        FieldError fieldError = (FieldError) error;
        String errorMessage = String.format("%s",Objects.requireNonNull(fieldError).getDefaultMessage());
        ApiError errorResponse = new ApiError(HttpStatus.BAD_REQUEST.name(),errorMessage);
        logger.error(errorResponse);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> globalExceptionHandler(Exception ex){
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.name(),ex.getMessage());
        logger.error(apiError);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }

}
