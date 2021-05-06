package az.azerconnect.checker.msisdn.service;

import az.azerconnect.checker.msisdn.dto.MsisdnDto;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface MsisdnService{
    Map<String,String> checkMsisdnListIsEligibleToSell(MsisdnDto msisdnDto);
}
