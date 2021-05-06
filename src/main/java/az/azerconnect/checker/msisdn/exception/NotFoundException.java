package az.azerconnect.checker.msisdn.exception;

import org.springframework.http.HttpStatus;


public class NotFoundException extends RestException{
    public NotFoundException(String errorCode,String errorDescription){
        super(HttpStatus.NOT_FOUND,errorCode,errorDescription);
    }
}
