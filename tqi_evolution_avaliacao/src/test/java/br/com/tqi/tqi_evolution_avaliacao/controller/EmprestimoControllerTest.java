package br.com.tqi.tqi_evolution_avaliacao.controller;

import br.com.tqi.tqi_evolution_avaliacao.api.controller.EmprestimoController;
import br.com.tqi.tqi_evolution_avaliacao.domain.service.cliente.ClienteService;
import br.com.tqi.tqi_evolution_avaliacao.domain.service.emprestimo.CancelarEmprestimoService;
import br.com.tqi.tqi_evolution_avaliacao.domain.service.emprestimo.EmprestimoService;
import br.com.tqi.tqi_evolution_avaliacao.domain.service.emprestimo.SolicitacaodeEmprestimoService;
import br.com.tqi.tqi_evolution_avaliacao.domain.service.emprestimo.SuspenderEmprestimoService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class EmprestimoControllerTest {

    @InjectMocks
    private EmprestimoController emprestimoController;
    @Mock
    private EmprestimoService emprestimoService;
    @Mock
    private SolicitacaodeEmprestimoService solicitacaodeEmprestimoService;
    @Mock
    private CancelarEmprestimoService cancelarEmprestimoService;
    @Mock
    private SuspenderEmprestimoService suspenderEmprestimoService;

    @Test
    @DisplayName("Teste para listar Clientes")
    public void deveRetornarSucesso_quandoListarClientes(){
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

        //Lista de Emprestimo


        given()
                .headers("Authorization",token)
                .accept(ContentType.JSON)
                .when()
                .get("/api/v1/emprestimo")
                .then()
                .statusCode(HttpStatus.OK.value());

    }


}