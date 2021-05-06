package az.azerconnect.checker.msisdn.service.impl;

import az.azerconnect.checker.msisdn.dto.MsisdnDto;
import az.azerconnect.checker.msisdn.exception.FieldIsNullOrEmptyException;
import az.azerconnect.checker.msisdn.exception.FormatAndSizeException;
import az.azerconnect.checker.msisdn.exception.NotFoundException;
import az.azerconnect.checker.msisdn.utils.ValidatorUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;

/**
 * @ Created by Polad Gulmammadli on  05 May 2021
 */
@SpringBootTest
class MsisdnServiceImplTest{

    @InjectMocks
    private MsisdnServiceImpl msisdnService;

    private final MsisdnDto msisdnDto = new MsisdnDto();

    @BeforeEach
    public void setUp(){
        msisdnDto.setMsisdnList(Arrays.asList("994702000123","994702131234"));
        msisdnDto.setBlacklistString("702000%,70201%,702996%,702997%,7733_3333,773323339");
        msisdnDto.setWhitelistString("7020%,70213%,70214%,70215%");
    }

    @Test
    void checkMsisdnListIsEligibleToSell(){
        try (MockedStatic<ValidatorUtils> mocked = mockStatic(ValidatorUtils.class)) {
            msisdnService.checkMsisdnListIsEligibleToSell(msisdnDto);
            mocked.verify(times(1),() -> ValidatorUtils.validateMsisdnRequest(msisdnDto));
        }

    }

    @Test
    void checkMsisdnListIsEligibleToSellFormatSizeException(){
        msisdnDto.setMsisdnList(Arrays.asList("9947020","9947021312","994705434553543"));
        assertThrows(FormatAndSizeException.class,() -> msisdnService.checkMsisdnListIsEligibleToSell(msisdnDto));
    }

    @Test
    void checkMsisdnListIsEligibleToSellFormatSizeExceptionNonNumeric(){
        msisdnDto.setMsisdnList(Collections.singletonList("994A7020ABC4"));
        assertThrows(FormatAndSizeException.class,() -> msisdnService.checkMsisdnListIsEligibleToSell(msisdnDto));
    }

    @Test
    void checkMsisdnListIsEligibleToSellFieldIsNullOrEmptyException(){
        msisdnDto.setMsisdnList(Arrays.asList("",null));
        assertThrows(FieldIsNullOrEmptyException.class,() -> msisdnService.checkMsisdnListIsEligibleToSell(msisdnDto));
    }

    @Test
    void checkMsisdnListIsEligibleToSellNotFoundException(){
        msisdnDto.setMsisdnList(Collections.emptyList());
        assertThrows(NotFoundException.class,() -> msisdnService.checkMsisdnListIsEligibleToSell(msisdnDto));
    }
}