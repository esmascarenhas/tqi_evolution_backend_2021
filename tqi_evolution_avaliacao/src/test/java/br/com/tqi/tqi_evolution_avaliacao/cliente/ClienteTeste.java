package br.com.tqi.tqi_evolution_avaliacao.cliente;


import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static  org.hamcrest.Matchers.*;


public class ClienteTeste {


    @Test
    @DisplayName("Teste de acesso e inclusão de cliente")
    public void testeDadoUmAdminQuandoCadastroClienteEntaoObbtenhoStatusCode201(){
        //configurar o caminho comum de acesso a minha api
        baseURI = "http://localhost";
        port = 8080;
        basePath = "/api";

        //login na api como admin

        String token = given()
                .body("{\n" +
                        "  \"email\": \"admin@email.com\",\n" +
                        "  \"senha\": \"654321\"\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .post("/v1/auth")
                .then()
                .log().all()
                .extract()
                .path("data.token");


        System.out.println(token);

        //Cadastro de Cliente

        given()
                .headers("Authorization",token)
                .body("{\n" +
                        "  \"cpf\": \"string\",\n" +
                        "  \"endereco\": {\n" +
                        "    \"bairro\": \"string\",\n" +
                        "    \"cep\": \"string\",\n" +
                        "    \"cidade\": \"string\",\n" +
                        "    \"complemento\": \"string\",\n" +
                        "    \"estado\": \"string\",\n" +
                        "    \"logradouro\": \"string\",\n" +
                        "    \"numero\": 0,\n" +
                        "    \"pais\": \"string\"\n" +
                        "  },\n" +
                        "  \"nome\": \"string\",\n" +
                        "  \"renda\": 0,\n" +
                        "  \"rg\": \"string\",\n" +
                        "  \"usuario\":{\n" +
                        "    \"email\" : \"string\",\n" +
                        "    \"senha\": \"string\"\n" +
                        "}\n" +
                        "  \n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .post("/v1/cliente")
                .then()
                .log().all()
                .assertThat()
                .statusCode(201);

    }
}
