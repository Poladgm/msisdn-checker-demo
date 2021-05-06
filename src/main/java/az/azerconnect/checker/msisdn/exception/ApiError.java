package az.azerconnect.checker.msisdn.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Getter
@AllArgsConstructor
public class ApiError implements Serializable{
    private static final long serialVersionUID = 5750018574242613291L;
    private final String code;
    private final String message;

}
