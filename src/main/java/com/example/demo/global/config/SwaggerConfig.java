package com.example.demo.global.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo.global.config.SwaggerConfig.Menu.*;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Getter
    @RequiredArgsConstructor
    enum Menu {
        KAKAO("카카오", "/kakao");

        private final String name;
        private final String path;
    }

    private final List<Parameter> parameters = new ArrayList<>();

    public SwaggerConfig() {
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        String headerRefreshToken = "Access-Token";
        parameterBuilder
                .name(headerRefreshToken)
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();
        parameters.add(parameterBuilder.build());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Server")
                .version("1.01")
                .build();
    }

    @Bean
    public Docket kakao() {
        return getDocket(KAKAO);
    }

    private Docket getDocket(Menu menu) {
        return new Docket(DocumentationType.SWAGGER_2)
//                .globalOperationParameters(parameters)
                .useDefaultResponseMessages(false)
                .groupName(menu.getName())
                .apiInfo(this.apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant(menu.getPath() + "/**"))
                .build();
    }

}
