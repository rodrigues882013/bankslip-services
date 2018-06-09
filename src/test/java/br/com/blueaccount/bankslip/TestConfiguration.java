package br.com.blueaccount.bankslip;

import br.com.blueaccount.bankslip.services.BankSlipService;
import br.com.blueaccount.bankslip.web.facade.BankSlipFacade;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ActiveProfiles;


@SpringBootApplication
@ActiveProfiles("test")
public class TestConfiguration {

    @Bean
    @Primary
    public BankSlipService service() {
        return Mockito.mock(BankSlipService.class);
    }

    @Bean
    @Primary
    public BankSlipFacade facade() {
        return Mockito.mock(BankSlipFacade.class);
    }
}