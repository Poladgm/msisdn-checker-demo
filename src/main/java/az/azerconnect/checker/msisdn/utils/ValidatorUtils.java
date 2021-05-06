package az.azerconnect.checker.msisdn.utils;

import az.azerconnect.checker.msisdn.dto.MsisdnDto;
import az.azerconnect.checker.msisdn.enums.ErrorCode;
import az.azerconnect.checker.msisdn.exception.NotFoundException;

import java.util.List;

public class ValidatorUtils{
    private static final byte MSISDN_SIZE = 12;

    private ValidatorUtils(){
    }

    public static void validateMsisdnRequest(MsisdnDto msisdnDto){
        checkIfMsisdnListIsAvailable(msisdnDto.getMsisdnList());
        msisdnDto.getMsisdnList().forEach(msisdn -> FieldValidatorUtils.checkIfFieldIsNumericWithSpecificSize(msisdn,MSISDN_SIZE));
    }

    private static void checkIfMsisdnListIsAvailable(List<String> msisdnList){
        if (msisdnList == null || msisdnList.isEmpty()){
            throw new NotFoundException(ErrorCode.ERRC101.name(),ErrorCode.ERRC101.getValue());
        }
    }
}
