package br.com.tqi.tqi_evolution_avaliacao.api.controller;


import br.com.tqi.tqi_evolution_avaliacao.api.dto.mapper.ClienteMapper;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.ClienteDTO;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.EmprestimoDTO;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.imput.ClienteDTOImput;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.response.MessageResponse;
import br.com.tqi.tqi_evolution_avaliacao.domain.entity.Cliente;
import br.com.tqi.tqi_evolution_avaliacao.domain.exception.ClienteNaoEncontradoException;
import br.com.tqi.tqi_evolution_avaliacao.domain.repository.ClienteRepository;
import br.com.tqi.tqi_evolution_avaliacao.domain.service.BuscarClienteService;
import br.com.tqi.tqi_evolution_avaliacao.domain.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.PrivateKey;
import java.util.List;

@Api("SAC API Rest - Sistema de Análise de Crédito")
@CrossOrigin("*")
@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/cliente")
public class ClienteController {

    private ClienteRepository clienteRepository;
    private ClienteService clienteService;
    private ClienteMapper clienteMapper;


    @GetMapping(produces = "application/json")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @ApiOperation(value = "Retorna a carteira de clientes cadastrados. ")
    public List<ClienteDTO> listAll(){

        return clienteMapper.toCollectionModel(clienteService.listarCliente());
    }
    @GetMapping(path = "by-id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ClienteDTO> findByIdAuthenticationPrincipal(@PathVariable Integer id,
                                                                 @AuthenticationPrincipal UserDetails userDetails,
                                                                 @RequestHeader String Authorization) {
        log.info(userDetails);
        return ResponseEntity.ok(clienteService.buscarCliente(id));
    }
/*    @GetMapping("/{clienteid}")
    @ApiOperation(value = "Retorna um cliente específico. ")
    public ResponseEntity<ClienteDTO> ClienteById(@PathVariable Integer clienteid){
        return clienteRepository.findById(clienteid)
                .map(cliente -> ResponseEntity.ok(clienteMapper.toModel(cliente)))
                .orElse(ResponseEntity.notFound().build());
    }*/
    @GetMapping("/{clienteid}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retorna um cliente específico. ")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ClienteDTO ClienteById(@PathVariable Integer clienteid,
                                  @RequestHeader String Authorization) throws ClienteNaoEncontradoException {
        return clienteService.buscarCliente(clienteid);

    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Realiza o cadastro de novos clientes. ")
    public MessageResponse create (@Valid @RequestBody ClienteDTOImput clienteDTOImput,
                                   @RequestHeader String Authorization){
        Cliente novoAluno = clienteMapper.toEntity(clienteDTOImput);
        return clienteService.create(novoAluno);

    }
    @ApiOperation(value = "Atualiza o cadastro de um cliente. ")
    @PutMapping("/{clienteid}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponse update(@PathVariable Integer clienteid,
                                  @Valid @RequestBody ClienteDTO clienteDTO,
                                  @RequestHeader String Authorization) throws ClienteNaoEncontradoException {
        return clienteService.update(clienteid,clienteDTO);
    }
    @ApiOperation(value = "Exclui o cadastro de um cliente. ")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{clienteid}")
    public void excluirCliente (Integer clienteid,@RequestHeader String Authorization) throws ClienteNaoEncontradoException {
        clienteService.delete(clienteid);
    }

}
