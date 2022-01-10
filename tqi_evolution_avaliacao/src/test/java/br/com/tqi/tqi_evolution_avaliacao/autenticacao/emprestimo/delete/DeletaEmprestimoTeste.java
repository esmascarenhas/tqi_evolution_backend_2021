package br.com.tqi.tqi_evolution_avaliacao.autenticacao.emprestimo.delete;

import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.*;

public class DeletaEmprestimoTeste {


    @Test
    @DisplayName("TesteSucessoAoExcluirEmprestimo")
    public void testeDadoUmAdminQuandoDeletaEmprestimoEntaoObtenhoStatusCode204(){
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

        //Deleta Emprestimo

        given()
                .headers("Authorization",token)
                .queryParam("5")
                .contentType(ContentType.JSON)
                .when()
                .delete("/v1/emprestimo/{emprestimoid}")
                .then()
                .log().all()
                .assertThat()
                .statusCode(204);

    }
}
