package br.com.tqi.tqi_evolution_avaliacao.violacaoSeguranca.emprestimo.get;

import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static io.restassured.RestAssured.*;

public class NãoListarEmprestimoTeste {


    @Test
    @DisplayName("TesteAcessoNegadoAoListarEmprestimo")
    public void testeDadoUmAdminQuandoListaEmprestimoEntaoObtenhoStatusCode403(){
        //configurar o caminho comum de acesso a minha api
        baseURI = "http://localhost";
        port = 8080;
        basePath = "/api";

        //login na api como admin

        String token = given()
                .body("{\n" +
                        "  \"email\": \"teste@email.com\",\n" +
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

        //Não Lista de Emprestimo

        given()
                .headers("Authorization",token)
                .contentType(ContentType.JSON)
                .when()
                .get("/v1/emprestimo")
                .then()
                .log().all()
                .assertThat()
                .statusCode(403);

    }
}
