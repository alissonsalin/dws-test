package dws.test.configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()      
          .apis(RequestHandlerSelectors.any())              
          .apis(RequestHandlerSelectors.basePackage("dws.test.controller"))
          .paths(PathSelectors.any())                          
          .build()
          .globalResponseMessage(RequestMethod.GET, responseMessageList());
    }
	
	private List<ResponseMessage> responseMessageList() {
        return Arrays.asList(
               new ResponseMessageBuilder().code(200).message("Return the band list by name sorted").build(),
                new ResponseMessageBuilder().code(404).message("Band(s) not found").build(),
                new ResponseMessageBuilder().code(500).message("Internal server error").build()
        );
    }
}
