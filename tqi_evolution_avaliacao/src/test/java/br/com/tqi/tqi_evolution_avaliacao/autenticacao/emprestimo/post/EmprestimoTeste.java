package br.com.tqi.tqi_evolution_avaliacao.autenticacao.emprestimo.post;

import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.*;

public class EmprestimoTeste {


    @Test
    @DisplayName("TesteSucessoAoCadastrarEmprestimo")
    public void testeDadoUmAdminQuandoCadastroEmprestimoEntaoObtenhoStatusCode201(){
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

        //Cadastro de Emprestimo

        given()
                .headers("Authorization",token)
                .body("{\n" +
                        "  \"clienteid\": {\n" +
                        "    \"id\": 1\n" +
                        "  },\n" +
                        "  \"dataPrimeiraParcela\": \"2022-02-04\",\n" +
                        "  \"quantidadeParcelas\": 6,\n" +
                        "  \"valorEmprestimo\": 50000\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .post("/v1/emprestimo")
                .then()
                .log().all()
                .assertThat()
                .statusCode(201);

    }
}
