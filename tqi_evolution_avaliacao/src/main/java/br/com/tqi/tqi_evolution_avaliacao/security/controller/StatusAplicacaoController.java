package br.com.tqi.tqi_evolution_avaliacao.security.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin(origins = "*")
@ApiOperation(value = "Verifica se a Aplicação está no ar")
@Api("StatusAplicacaoController")
public class StatusAplicacaoController {

    @GetMapping("v1/status")
    @ApiOperation(value = "Verifica se a Aplicação está no ar")
    public String verificaStatusAplicacao() {

        return "Aplicação está funcionando corretamente";
    }
}
