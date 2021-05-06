package az.azerconnect.checker.msisdn.exception;

import org.springframework.http.HttpStatus;

public class FormatAndSizeException extends RestException{
    public FormatAndSizeException(String errorCode,String errorDescription){
        super(HttpStatus.BAD_REQUEST,errorCode,errorDescription);
    }
}
