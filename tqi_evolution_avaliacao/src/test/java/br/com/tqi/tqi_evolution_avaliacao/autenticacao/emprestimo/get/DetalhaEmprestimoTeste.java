package br.com.tqi.tqi_evolution_avaliacao.autenticacao.emprestimo.get;

import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static io.restassured.RestAssured.*;

public class DetalhaEmprestimoTeste {


    @Test
    @DisplayName("TesteSucessoAoDetalharEmprestimo")
    public void testeDadoUmAdminQuandoDetalhaEmprestimoEntaoObbtenhoStatusCode200(){
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

        //Detalhe de Emprestimo

        given()
                .headers("Authorization",token)
                .contentType(ContentType.JSON)
                .queryParam("1")
                .when()
                .get("/v1/emprestimo/detalhe/{emprestimoid}")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);

    }
}
