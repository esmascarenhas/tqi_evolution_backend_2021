package br.com.tqi.tqi_evolution_avaliacao.securityTeste.violacaoSeguranca.emprestimo.put;

import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static io.restassured.RestAssured.*;

public class NãoAlteraEmprestimoTeste {


    @Test
    @DisplayName("TesteAcessoNegadoQuandoAlteraEmprestimo" )
    public void testeDadoUmUsuarioQuandoAlteraEmprestimoEntaoObtenhoStatusCode403(){
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

        //Não Altera Emprestimo

        given()
                .headers("Authorization",token)
                .body("{\n" +
                        "  \"clienteid\": {\n" +
                        "    \"id\": 1\n" +
                        "  },\n" +
                        "  \"dataPrimeiraParcela\": \"2022-03-04\",\n" +
                        "  \"quantidadeParcelas\": 5,\n" +
                        "  \"valorEmprestimo\": 50000\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .put("/v1/emprestimo/{emprestimoid}")
                .then()
                .log().all()
                .assertThat()
                .statusCode(403);

    }
}
