package br.com.tqi.tqi_evolution_avaliacao.securityTeste.autenticacao.emprestimo.put;

import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static io.restassured.RestAssured.*;

public class AlteraEmprestimoTeste {


    @Test
    @DisplayName("TesteSucessoQuandoAlteraEmprestimo" )
    public void testeDadoUmAdminQuandoAlteraEmprestimoEntaoObbtenhoStatusCode200(){
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

        //Altera Emprestimo

        given()
                .headers("Authorization",token)
                .body("{\n" +
                        "  \"clienteid\": {\n" +
                        "    \"id\": 1\n" +
                        "  },\n" +
                        "  \"dataPrimeiraParcela\": \"2022-03-04\",\n" +
                        "  \"quantidadeParcelas\": 10,\n" +
                        "  \"valorEmprestimo\": 50000\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .put("/v1/emprestimo/{emprestimoid}")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);

    }
}
