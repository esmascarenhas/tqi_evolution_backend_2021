package br.com.tqi.tqi_evolution_avaliacao.unitTestes.controller;

import br.com.tqi.tqi_evolution_avaliacao.api.controller.ClienteController;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.mapper.ClienteMapper;
import br.com.tqi.tqi_evolution_avaliacao.domain.repository.ClienteRepository;
import br.com.tqi.tqi_evolution_avaliacao.domain.repository.EmprestimoRepository;
import br.com.tqi.tqi_evolution_avaliacao.domain.service.cliente.ClienteService;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

import br.com.tqi.tqi_evolution_avaliacao.security.filter.TokenAuthenticationFilter;
import br.com.tqi.tqi_evolution_avaliacao.security.repository.UserRepository;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@WebMvcTest
class ClienteControllerTest {

    @Autowired//testa a classe em si
    private ClienteController clienteController;
    @MockBean// classe utilizadas dentro
    private ClienteService clienteService;
    @MockBean
    private ClienteRepository clienteRepository;
    @MockBean
    private ClienteMapper clienteMapper;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private EmprestimoRepository emprestimoRepository;
    @MockBean
    private TokenAuthenticationFilter filter;

    @BeforeEach // antes de cada teste
    public void setup(){
        standaloneSetup(this.clienteController);
    }

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

        //Cadastro de Cliente


        given()
        .headers("Authorization",token)
        .accept(ContentType.JSON)
        .when()
        .get("/api/v1/cliente")
        .then()
        .statusCode(HttpStatus.OK.value());

    }

}