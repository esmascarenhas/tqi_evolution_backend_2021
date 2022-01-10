package br.com.tqi.tqi_evolution_avaliacao.autenticacao.cliente.put;


import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static io.restassured.RestAssured.*;


public class ClientePutTeste {


    @Test
    @DisplayName("TesteSucessoQuandoAlteraCadastroDeCliente")
    public void testeDadoUmUsuarioQuandoAtualizoClienteEntaoObtenhoStatusCode200(){
        //configurar o caminho comum de acesso a minha api
        baseURI = "http://localhost";
        port = 8080;
        basePath = "/api";

        //login na api como admin

        String token = given()
                .body("{\n" +
                        "  \"email\": \"usuario@email.com\",\n" +
                        "  \"senha\": \"123456\"\n" +
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
                        "  \"nome\": \"Josenilda\",\n" +
                        "  \"renda\": 4000,\n" +
                        "  \"rg\": \"05207460-90\",\n" +
                        "  \"usuario\": {\n" +
                        "    \"email\": \"esm@email.com\",\n" +
                        "    \"senha\": \"@654321\"\n" +
                        "  }\n" +
                        "}")
                .contentType(ContentType.JSON)
                .queryParam(String.valueOf(1))
                .when()
                .put("/v1/cliente/{clienteid}")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);

    }
}
