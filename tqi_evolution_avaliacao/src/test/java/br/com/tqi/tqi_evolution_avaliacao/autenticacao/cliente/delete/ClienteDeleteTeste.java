package br.com.tqi.tqi_evolution_avaliacao.autenticacao.cliente.delete;


import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static io.restassured.RestAssured.*;


public class ClienteDeleteTeste {


    @Test
    @DisplayName("TesteSucessoAoExcluirCliente")
    public void testeDadoUmAdminQuandoDeletaClienteEntaoObtenhoStatusCode204(){
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
                .queryParam("2")
                .contentType(ContentType.JSON)
                .when()
                .delete("/v1/cliente/{clienteid}")
                .then()
                .log().all()
                .assertThat()
                .statusCode(204);

    }
}
