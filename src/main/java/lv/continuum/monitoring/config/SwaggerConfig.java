package lv.continuum.monitoring.config;

import lv.continuum.monitoring.util.VersionUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

@Configuration
@ComponentScan("lv.continuum.monitoring.controller.rest")
public class SwaggerConfig {

    @Bean
    public Docket docket() {

        // Global response error codes and their reasons
        var responses = new ArrayList<Response>();
        responses.add(getResponse(HttpStatus.BAD_REQUEST));
        responses.add(getResponse(HttpStatus.NOT_FOUND));
        responses.add(getResponse(HttpStatus.INTERNAL_SERVER_ERROR));

        var apiInfo = new ApiInfoBuilder()
                .title("Monitoring REST API")
                .description("Version " + VersionUtils.getVersionNumber())
                .build();

        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo)
                .useDefaultResponseMessages(false)
                .globalResponses(HttpMethod.DELETE, responses)
                .globalResponses(HttpMethod.GET, responses)
                .globalResponses(HttpMethod.POST, responses)
                .globalResponses(HttpMethod.PUT, responses)
                .select()
                .apis(basePackage("lv.continuum.monitoring.controller.rest"))
                .build();
    }

    private Response getResponse(HttpStatus httpStatus) {
        return new ResponseBuilder()
                .code(String.valueOf(httpStatus.value()))
                .description(httpStatus.getReasonPhrase())
                .build();
    }
}
