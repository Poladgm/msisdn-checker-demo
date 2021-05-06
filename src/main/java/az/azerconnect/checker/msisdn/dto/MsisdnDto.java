package az.azerconnect.checker.msisdn.dto;


import lombok.Data;

import java.util.List;

@Data
public class MsisdnDto{
    private List<String> msisdnList;
    private String whitelistString;
    private String blacklistString;

}
