package az.azerconnect.checker.msisdn.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@CrossOrigin
@Configuration
@EnableSwagger2
public class Swagger2Config{
    @Bean
    public Docket productApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("az.azerconnect.checker.msisdn"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData());
    }

    private ApiInfo metaData(){
        return new ApiInfoBuilder()
                .title("Swagger Documentation API")
                .description("Msisdn checker rest api")
                .version("1.0.0")
                .contact(new Contact(
                        "azerconnect.az",
                        "http://azerconnect.az",
                        "info@azerconnect.az"))
                .build();
    }
}
