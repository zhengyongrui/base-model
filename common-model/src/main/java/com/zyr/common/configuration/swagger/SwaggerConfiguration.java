package com.zyr.common.configuration.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Value("${info.app_name:into.app_name}")
    private String appName;

    @Value("${info.description:info.description}")
    private String description;

    @Value("${info.version:info.version}")
    private String version;

    @Value("${info.author:info.author}")
    private String author;

    @Value("${info.url:info.url}")
    private String url;

    @Value("${info.email:info.email}")
    private String email;

    /**
     * swagger 信息
     *
     * @return 页面信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(appName)
                .description(description)
                .termsOfServiceUrl("")
                .version(version)
                .contact(new Contact(author, url, email))
                .build();
    }

    @Bean
    public Docket customImplementation() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zyr"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.apiInfo());
    }
}
