package br.com.tqi.tqi_evolution_avaliacao.securityTeste.violacaoSeguranca.emprestimo.delete;

import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static io.restassured.RestAssured.*;

public class NaoDeletaEmprestimoTeste {


    @Test
    @DisplayName("TesteAcessoNegadoAoExcluirEmprestimo")
    public void testeDadoUmUsuarioQuandoDeletaEmprestimoEntaoObtenhoStatusCode403(){
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

        //Deleta Emprestimo

        given()
                .headers("Authorization",token)
                .queryParam("5")
                .contentType(ContentType.JSON)
                .when()
                .delete("/api/v1/emprestimo/{emprestimoid}")
                .then()
                .log().all()
                .assertThat()
                .statusCode(403);

    }
}
