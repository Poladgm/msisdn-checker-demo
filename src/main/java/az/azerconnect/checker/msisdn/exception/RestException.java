package az.azerconnect.checker.msisdn.exception;

import org.springframework.http.HttpStatus;


public class RestException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final ApiError apiError;

    public RestException(HttpStatus httpStatus, String errorCode, String errorDescription) {
        this.httpStatus = httpStatus;
        this.apiError = new ApiError(errorCode, errorDescription);
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }
    public ApiError getApiError() {
        return this.apiError;
    }
}
