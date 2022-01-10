package br.com.tqi.tqi_evolution_avaliacao.commom;

import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@EnableSwagger2
@Configuration
public class Swaggerconfig {

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }

    @Bean
    public Docket apiSACTqi (ServletContext servletContext){
        return new Docket(DocumentationType.SWAGGER_2)
                //.securitySchemes(Arrays.asList(new ApiKey("Token Access", HttpHeaders.AUTHORIZATION, SecurityScheme.In.HEADER.name())))
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.tqi.tqi_evolution_avaliacao"))
                .paths(PathSelectors.any())
                .paths(Predicate.not(PathSelectors.regex("/*.error.*")))
                .build()
                .apiInfo(metaInfo());
    }


    private ApiInfo metaInfo(){
        ApiInfo apiInfo = new ApiInfo("API Sistema de análise de crédito ",
                "Api para cadastro de cliente, solicitação e acompanhamento de Emprestimos",
                "1.0","Termos de Serviço", new Contact("Emerson Mascarenhas",
                "https://www.emprestimos.com.br" ,"esmascarenhas@gmail.com"),
                "Apache License Version 2.0",  "https://www.apache.org/license.html",
                new ArrayList<VendorExtension>());

                return apiInfo;

    }


}
