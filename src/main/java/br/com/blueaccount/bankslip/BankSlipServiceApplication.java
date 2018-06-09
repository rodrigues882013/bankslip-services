package br.com.blueaccount.bankslip;

import br.com.blueaccount.bankslip.configuration.WebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(WebConfiguration.class)
public class BankSlipServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankSlipServiceApplication.class, args);
    }
}
