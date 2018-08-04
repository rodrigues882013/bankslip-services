package br.com.blueaccount.bankslip;

import br.com.blueaccount.bankslip.configuration.SwaggerConfiguration;
import br.com.blueaccount.bankslip.configuration.WebConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@Import({WebConfiguration.class, SwaggerConfiguration.class})
public class BankSlipServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankSlipServiceApplication.class, args);
    }

}
