package br.com.blueaccount.bankslip;

import br.com.blueaccount.bankslip.configuration.WebConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@Import(WebConfiguration.class)
@EnableSwagger2
public class BankSlipServiceApplication {

    @Value("${build.version}")
    private String version;


    public static void main(String[] args) {
        SpringApplication.run(BankSlipServiceApplication.class, args);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("br.com.blueaccount.bankslip")).paths(PathSelectors.any())
                .build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("bank-slip-service")
                .description("<p>Esta aplicação tem como objetivo gerar boletos para avaliação.</p>")
                .version(version).build();
    }
}
