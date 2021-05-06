package az.azerconnect.checker.msisdn.controller;

import az.azerconnect.checker.msisdn.dto.MsisdnDto;
import az.azerconnect.checker.msisdn.service.MsisdnService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @ Created by Polad Gulmammadli on  05 May 2021
 */
@SpringBootTest
class MsisdnControllerTest{
    @InjectMocks
    private MsisdnController msisdnController;
    @Mock
    private MsisdnService msisdnService;
    private final MsisdnDto msisdnDto = new MsisdnDto();

    @BeforeEach
    public void setUp(){
        msisdnDto.setMsisdnList(Arrays.asList("994702131234","994702000123"));
        msisdnDto.setBlacklistString("702000%,70201%,702996%,702997%,7733_3333");
        msisdnDto.setWhitelistString("7020%,70213%,70214%,70215%");
    }

    @Test
    void checkIsEligibleToSellByMaskList(){
        Map<String,String> response = new HashMap<>();
        response.put("994702131234","OK");
        response.put("994702000123","msisdn = 994702000123 is in blacklist");
        response.put("994701131234","msisdn = 994701131234 is not in whitelist");
        when(msisdnService.checkMsisdnListIsEligibleToSell(msisdnDto)).thenReturn(response);
        Map<String,String> result = msisdnController.checkIsEligibleToSellByMaskList(msisdnDto).getBody();
        assertThat(result).isNotNull().hasSameSizeAs(response)
                .containsEntry("994702131234",response.get("994702131234"))
                .containsEntry("994702000123",response.get("994702000123"));
        verify(msisdnService,times(1)).checkMsisdnListIsEligibleToSell(msisdnDto);
    }
}