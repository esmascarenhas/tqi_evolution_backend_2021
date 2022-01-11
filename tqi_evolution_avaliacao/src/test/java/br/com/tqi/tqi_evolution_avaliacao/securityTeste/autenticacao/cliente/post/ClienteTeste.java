package br.com.tqi.tqi_evolution_avaliacao.securityTeste.autenticacao.cliente.post;


import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static io.restassured.RestAssured.*;


public class ClienteTeste {


    @Test
    @DisplayName("TesteSucesoAoIncluirCliente")
    public void testeDadoUmAdminQuandoCadastroClienteEntaoObtenhoStatusCode201(){
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
                        "  \"cpf\": \"928373795-49\",\n" +
                        "  \"endereco\": {\n" +
                        "    \"bairro\": \"Cabula\",\n" +
                        "    \"cep\": \"41150-000\",\n" +
                        "    \"cidade\": \"Salvador\",\n" +
                        "    \"complemento\": \"105\",\n" +
                        "    \"estado\": \"Bahia\",\n" +
                        "    \"logradouro\": \"Av. Sileira Martins\",\n" +
                        "    \"numero\": 95,\n" +
                        "    \"pais\": \"Brasil\"\n" +
                        "  },\n" +
                        "  \"nome\": \"Sonia\",\n" +
                        "  \"renda\": 4000,\n" +
                        "  \"rg\": \"05207460-90\",\n" +
                        "  \"usuario\": {\n" +
                        "    \"email\": \"esm@email.com\",\n" +
                        "    \"senha\": \"@654321\"\n" +
                        "  }\n" +
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
