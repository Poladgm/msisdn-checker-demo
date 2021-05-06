package az.azerconnect.checker.msisdn.utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Pattern;

public class RegexUtils{
    private static final Logger logger = LogManager.getLogger(RegexUtils.class);

    private RegexUtils(){
    }

    public static boolean isMatchingMask(String msisdn,String mask){
        logger.info("matching msisdn: {}, mask: {}",msisdn,mask);
        if (mask.contains("%")){
            return isMatchingRangeMask(msisdn,mask);
        } else if (mask.contains("_")){
            return isMatchingWildcardMask(msisdn,mask);
        }
        return Pattern.matches(mask,msisdn);
    }

    private static boolean isMatchingRangeMask(String msisdn,String mask){
        return Pattern.matches(createRangeRegex(mask),msisdn);
    }

    private static boolean isMatchingWildcardMask(String msisdn,String mask){
        return Pattern.matches(createWildcardRegex(mask),msisdn);
    }

    private static String createRangeRegex(String mask){
        return mask.replace('%','.') + "*";
    }

    private static String createWildcardRegex(String mask){
        return mask.replace('_','.');
    }
}
