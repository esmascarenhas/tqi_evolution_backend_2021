package br.com.tqi.tqi_evolution_avaliacao.autenticacao.cliente.get;


import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static io.restassured.RestAssured.*;


public class ClienteGetTeste {


    @Test
    @DisplayName("Teste de acesso a lista de cliente")
    public void testeDadoUmAdminQuandolistaClienteEntaoObtenhoStatusCode200(){
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
                .contentType(ContentType.JSON)
                .when()
                .get("/v1/cliente")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);

    }
}
