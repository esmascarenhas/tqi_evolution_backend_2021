package br.com.tqi.tqi_evolution_avaliacao.commom;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@EnableSwagger2
@Configuration
public class Swaggerconfig {

    public Docket apiSACTqi (){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.tqi.tqi_evolution_avaliacao"))
                .paths(PathSelectors.regex("/api.*"))
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
