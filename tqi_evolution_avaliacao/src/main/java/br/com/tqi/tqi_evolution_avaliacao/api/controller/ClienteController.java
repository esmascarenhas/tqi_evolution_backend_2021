package br.com.tqi.tqi_evolution_avaliacao.api.controller;


import br.com.tqi.tqi_evolution_avaliacao.api.dto.mapper.ClienteMapper;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.ClienteDTO;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.imput.ClienteDTOImput;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.response.MessageResponse;
import br.com.tqi.tqi_evolution_avaliacao.domain.exception.ClienteNaoEncontradoException;
import br.com.tqi.tqi_evolution_avaliacao.domain.repository.ClienteRepository;
import br.com.tqi.tqi_evolution_avaliacao.domain.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api("SAC API Rest - Sistema de Análise de Crédito")
@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/cliente")
public class ClienteController {

    private ClienteRepository clienteRepository;
    private ClienteService clienteService;
    private ClienteMapper clienteMapper;

    @GetMapping
    @ApiOperation(value = "Retorna a carteira de clientes cadastrados. ")
    public List<ClienteDTO> listAll(){

        return clienteMapper.toCollectionModel(clienteService.listarCliente());
    }
    @GetMapping("/{clienteid}")
    @ApiOperation(value = "Retorna um cliente específico. ")
    public ResponseEntity<ClienteDTO> ClienteById(@PathVariable Integer clienteid){
        return clienteRepository.findById(clienteid)
                .map(cliente -> ResponseEntity.ok(clienteMapper.toModel(cliente)))
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Realiza o cadastro de novos clientes. ")
    public MessageResponse create (@Valid @RequestBody ClienteDTOImput clienteDTOImput){
        return clienteService.create(clienteDTOImput);
    }
    @ApiOperation(value = "Atualiza o cadastro de um cliente. ")
    @PutMapping("/{clienteid}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponse update(@PathVariable Integer clienteid, @Valid @RequestBody ClienteDTOImput clienteDTOImput) throws ClienteNaoEncontradoException {
        return clienteService.update(clienteid,clienteDTOImput);
    }
    @ApiOperation(value = "Exclui o cadastro de um cliente. ")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{clienteid}")
    public void excluirCliente (Integer clienteid) throws ClienteNaoEncontradoException {
        clienteService.delete(clienteid);
    }

}
