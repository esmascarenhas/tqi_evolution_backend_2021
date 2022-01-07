package br.com.tqi.tqi_evolution_avaliacao.api.controller;


import br.com.tqi.tqi_evolution_avaliacao.api.dto.mapper.ClienteMapper;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.ClienteDTO;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.imput.ClienteDTOImput;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.response.MessageResponse;
import br.com.tqi.tqi_evolution_avaliacao.domain.exception.ClienteNaoEncontradoException;
import br.com.tqi.tqi_evolution_avaliacao.domain.repository.ClienteRepository;
import br.com.tqi.tqi_evolution_avaliacao.domain.service.cliente.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api("SAC API Rest - Sistema de Análise de Crédito")
@CrossOrigin("*")
@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping(path = "/api")
public class ClienteController {

    private ClienteRepository clienteRepository;
    private ClienteService clienteService;
    private ClienteMapper clienteMapper;


    @GetMapping(produces = "application/json", value = "/v1/cliente")
    @PreAuthorize("hasAnyRole('ROLES_ADMIN','ROLES_USER')")
    @ApiOperation(value = "Retorna a carteira de clientes cadastrados. ")
    public List<ClienteDTO> listAll(){

        return clienteMapper.toCollectionModel(clienteService.listarCliente());
    }
    @GetMapping(path = "/v1/cliente/by-id/{id}")
    @PreAuthorize("hasRole('ROLES_ADMIN')")
    public ResponseEntity<ClienteDTO> findByIdAuthenticationPrincipal(@PathVariable Integer id,
                                                                 @AuthenticationPrincipal UserDetails userDetails,
                                                                 @RequestHeader String Authorization) {
        log.info(userDetails);
        return ResponseEntity.ok(clienteService.buscarCliente(id));
    }

    @GetMapping("/v1/cliente/{clienteid}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retorna um cliente específico. ")
    @PreAuthorize("hasAnyRole('ROLES_ADMIN','ROLES_USER')")
    public ClienteDTO ClienteById(@PathVariable Integer clienteid,
                                  @RequestHeader String Authorization) throws ClienteNaoEncontradoException {
        return clienteService.buscarCliente(clienteid);

    }



    @RequestMapping(value = "/v1/cliente", method = RequestMethod.POST, produces = "application/json" )
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLES_ADMIN')")
    @ApiOperation(value = "Realiza o cadastro de novos clientes. ")
    @ResponseBody
    public MessageResponse create (@Valid @RequestBody ClienteDTOImput clienteDTOImput,
                                   @RequestHeader String Authorization){
       return clienteService.create(clienteDTOImput);

    }


    @ApiOperation(value = "Atualiza o cadastro de um cliente. ")
    @PutMapping("/v1/cliente/{clienteid}")
    @PreAuthorize("hasRole('ROLES_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponse update(@PathVariable Integer clienteid,
                                  @Valid @RequestBody ClienteDTO clienteDTO,
                                  @RequestHeader String Authorization) throws ClienteNaoEncontradoException {
        return clienteService.update(clienteid,clienteDTO);
    }


    @ApiOperation(value = "Exclui o cadastro de um cliente. ")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLES_ADMIN')")
    @DeleteMapping("/v1/cliente/{clienteid}")
    public void excluirCliente (Integer clienteid,@RequestHeader String Authorization) throws ClienteNaoEncontradoException {
        clienteService.delete(clienteid);
    }

}
