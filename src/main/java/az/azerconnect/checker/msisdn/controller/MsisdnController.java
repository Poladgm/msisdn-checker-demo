package az.azerconnect.checker.msisdn.controller;

import az.azerconnect.checker.msisdn.dto.MsisdnDto;
import az.azerconnect.checker.msisdn.service.MsisdnService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MsisdnController{
    private final MsisdnService msisdnService;

    @PostMapping("/masklist/iseligible")
    public ResponseEntity<Map<String,String>> checkIsEligibleToSellByMaskList(@RequestBody MsisdnDto msisdnDto){
        return ResponseEntity.ok(msisdnService.checkMsisdnListIsEligibleToSell(msisdnDto));
    }
}
