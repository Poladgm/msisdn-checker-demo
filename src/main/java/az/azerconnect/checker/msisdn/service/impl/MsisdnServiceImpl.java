package az.azerconnect.checker.msisdn.service.impl;

import az.azerconnect.checker.msisdn.dto.MsisdnDto;
import az.azerconnect.checker.msisdn.service.MsisdnService;
import az.azerconnect.checker.msisdn.utils.CommonUtils;
import az.azerconnect.checker.msisdn.utils.RegexUtils;
import az.azerconnect.checker.msisdn.utils.ValidatorUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MsisdnServiceImpl implements MsisdnService{
    private static final Logger logger = LogManager.getLogger(MsisdnServiceImpl.class);

    @Override
    public Map<String,String> checkMsisdnListIsEligibleToSell(MsisdnDto msisdnDto){
        logger.info("msisdnDto {}",msisdnDto);
        ValidatorUtils.validateMsisdnRequest(msisdnDto);
        return msisdnDto.getMsisdnList().stream().collect(Collectors.toMap(msisdn -> msisdn,msisdn -> checkMsisdnIsEligibleToSell(
                msisdn,
                CommonUtils.convertStringToMaskList(msisdnDto.getWhitelistString()),
                CommonUtils.convertStringToMaskList(msisdnDto.getBlacklistString())),(a,b) -> b));
    }

    private String checkMsisdnIsEligibleToSell(String msisdn,List<String> whitelist,List<String> blacklist){
        if (isMsisdnInBlackList(blacklist,msisdn.substring(3)))
            return String.format("msisdn = %s is in blacklist",msisdn);
        return !isWhiteListIsEmpty(whitelist) && !isMsisdnInWhiteList(whitelist,msisdn.substring(3)) ? String.format("msisdn = %s is not in whitelist",msisdn) : "OK";
    }


    private boolean isMsisdnInWhiteList(List<String> whitelist,String msisdn){
        return whitelist.stream().anyMatch(mask -> RegexUtils.isMatchingMask(msisdn,mask));
    }

    private boolean isMsisdnInBlackList(List<String> blackList,String msisdn){
        return blackList.stream().anyMatch(mask -> RegexUtils.isMatchingMask(msisdn,mask));
    }

    private boolean isWhiteListIsEmpty(List<String> whitelist){
        return whitelist == null || whitelist.isEmpty();
    }


}

