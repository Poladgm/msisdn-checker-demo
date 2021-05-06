package az.azerconnect.checker.msisdn.exception;

import org.springframework.http.HttpStatus;

public class FieldIsNullOrEmptyException extends RestException{
    private static final long serialVersionUID = 6942704813233493346L;

    public FieldIsNullOrEmptyException(String errorCode,String errorDescription){
        super(HttpStatus.BAD_REQUEST,errorCode,errorDescription);
    }
}
