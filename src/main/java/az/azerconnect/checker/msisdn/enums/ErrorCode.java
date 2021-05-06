package az.azerconnect.checker.msisdn.enums;

public enum ErrorCode{
    ERRC101("MSISDN List is empty"),
    ERRC102("MSISDN must be provided"),
    ERRC103("Wrong MSISDN format");

    ErrorCode(String value){
        this.value = value;
    }

    private final String value;

    public String getValue(){
        return value;
    }
}
