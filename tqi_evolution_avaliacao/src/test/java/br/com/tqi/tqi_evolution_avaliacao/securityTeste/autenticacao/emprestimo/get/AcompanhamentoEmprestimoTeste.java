package br.com.tqi.tqi_evolution_avaliacao.securityTeste.autenticacao.emprestimo.get;

import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static io.restassured.RestAssured.*;

public class AcompanhamentoEmprestimoTeste {


    @Test
    @DisplayName("TesteSucessoParaAcompanhamentoDeEmprestimo")
    public void testeDadoUmUsuarioQuandoListaEmprestimoEntaoObtenhoStatusCode200(){
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

        //Acompanhamento de  Emprestimo

        given()
                .headers("Authorization",token)
                .contentType(ContentType.JSON)
                .queryParam("1")
                .when()
                .get("/v1/emprestimo/{clienteid}")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);

    }
}
