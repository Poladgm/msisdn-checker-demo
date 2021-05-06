package az.azerconnect.checker.msisdn.utils;

import az.azerconnect.checker.msisdn.enums.ErrorCode;
import az.azerconnect.checker.msisdn.exception.FieldIsNullOrEmptyException;
import az.azerconnect.checker.msisdn.exception.FormatAndSizeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FieldValidatorUtils{
    private static final Logger logger = LogManager.getLogger(FieldValidatorUtils.class);

    private FieldValidatorUtils(){
    }

    public static void checkIfFieldIsNumericWithSpecificSize(String fieldValue,int requiredSize){
        logger.info("checking if {} is numeric with specific size {}",fieldValue,requiredSize);
        checkIfFieldIsAvailable(fieldValue);
        if (!NumberUtils.isNumericWithSpecificSize(fieldValue.trim(),requiredSize)){
            throw new FormatAndSizeException(ErrorCode.ERRC103.name(),ErrorCode.ERRC103.getValue());
        }
    }

    public static void checkIfFieldIsAvailable(String fieldValue){
        if (isNullOrEmpty(fieldValue)){
            throw new FieldIsNullOrEmptyException(ErrorCode.ERRC102.name(),ErrorCode.ERRC102.getValue());
        }
    }

    public static boolean isNullOrEmpty(String str){
        return str == null || str.trim().isEmpty();
    }
}
