package br.com.tqi.tqi_evolution_avaliacao.autenticacao.emprestimo.put;

import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static io.restassured.RestAssured.*;

public class CancelaEmprestimoTeste {


    @Test
    @DisplayName("TesteSucessoQuandoCancelaEmprestimo" )
    public void testeDadoUmAdminQuandoCancelaEmprestimoEntaoObtenhoStatusCode201(){
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

        //Cancela Emprestimo

        given()
                .headers("Authorization",token)
                .queryParam("1")
                .contentType(ContentType.JSON)
                .when()
                .put("/v1/emprestimo/{emprestimoId}/cancela")
                .then()
                .log().all()
                .assertThat()
                .statusCode(201);

    }
}
