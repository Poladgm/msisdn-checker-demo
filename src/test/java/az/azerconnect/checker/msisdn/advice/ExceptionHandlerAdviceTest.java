package az.azerconnect.checker.msisdn.advice;

import az.azerconnect.checker.msisdn.exception.ApiError;
import az.azerconnect.checker.msisdn.exception.NotFoundException;
import az.azerconnect.checker.msisdn.exception.RestException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @ Created by Polad Gulmammadli on  06 May 2021
 */
@SpringBootTest
class ExceptionHandlerAdviceTest{
    @InjectMocks
    private ExceptionHandlerAdvice exceptionHandlerAdvice;

    @Test
    void handleNotFoundException(){
        NotFoundException notFoundException = mock(NotFoundException.class);
        when(notFoundException.getHttpStatus()).thenReturn(HttpStatus.NOT_FOUND);
        assertEquals(HttpStatus.NOT_FOUND, exceptionHandlerAdvice.handleNotFoundException(notFoundException).getStatusCode());
    }
    @Test
    void handleRestException(){
        RestException restException = mock(RestException.class);
        when(restException.getHttpStatus()).thenReturn(HttpStatus.BAD_REQUEST);
        assertEquals(HttpStatus.BAD_REQUEST, exceptionHandlerAdvice.handleRestException(restException).getStatusCode());
    }

    @Test
     void handleMethodArgumentNotValid() {
        MethodArgumentNotValidException methodArgumentNotValidException = mock(MethodArgumentNotValidException.class);
        FieldError fieldError =  mock(FieldError.class);
        BindingResult bindingResult = mock(BindingResult.class);
        List<ObjectError> objectErrorList = Collections.singletonList(fieldError);
        when(methodArgumentNotValidException.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getAllErrors()).thenReturn(objectErrorList);
        when(fieldError.getField()).thenReturn("test");
        when(fieldError.getDefaultMessage()).thenReturn("test");
        assertEquals(HttpStatus.BAD_REQUEST, exceptionHandlerAdvice.handleMethodArgumentNotValid(methodArgumentNotValidException).getStatusCode());

    }

    @Test
    void handleGlobalExceptionHandler(){
        ApiError apiError = mock(ApiError.class);
        Exception exception = mock(Exception.class);
        when(apiError.getCode()).thenReturn(HttpStatus.INTERNAL_SERVER_ERROR.name());
        assertEquals(apiError.getCode(), Objects.requireNonNull(exceptionHandlerAdvice.globalExceptionHandler(exception).getBody()).getCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exceptionHandlerAdvice.globalExceptionHandler(exception).getStatusCode());
    }


}