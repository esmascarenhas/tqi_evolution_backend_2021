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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api("SAC API Rest - Sistema de Análise de Crédito")
@ApiOperation(value = "Sistema de Análise de Crédito - Controle de Clientes" )
@CrossOrigin("*")
@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class ClienteController {

    private ClienteRepository clienteRepository;
    private ClienteService clienteService;
    private ClienteMapper clienteMapper;
    private final PasswordEncoder encoder;





    @GetMapping(produces = "application/json", value = "/v1/cliente")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retorna a carteira de clientes cadastrados - Acesso Admin. ")
    public List<ClienteDTO> listAll(){

        return clienteMapper.toCollectionModel(clienteService.listarCliente());
    }


    @GetMapping("/v1/cliente/{clienteid}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retorna um cliente específico - Acesso Admin e Usuário. ")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    public ClienteDTO ClienteById(@PathVariable Integer clienteid) throws ClienteNaoEncontradoException {
        return clienteService.buscarCliente(clienteid);

    }



    @RequestMapping(value = "/v1/cliente", method = RequestMethod.POST, produces = "application/json" )
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')" )
    @ApiOperation(value = "Realiza o cadastro de novos clientes - Acesso Admin e Usuário. ")
    public MessageResponse create (@Valid @RequestBody ClienteDTOImput clienteDTOImput){
      clienteDTOImput.getUsuario().setSenha(encoder.encode(clienteDTOImput.getUsuario().getSenha()));
       return clienteService.create(clienteDTOImput);

    }


    @ApiOperation(value = "Atualiza o cadastro de um cliente - Acesso Admin e Usuário. ")
    @PutMapping("/v1/cliente/{clienteid}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponse update(@PathVariable Integer clienteid,
                                  @Valid @RequestBody ClienteDTO clienteDTO) throws ClienteNaoEncontradoException {
        return clienteService.update(clienteid,clienteDTO);
    }


    @ApiOperation(value = "Exclui o cadastro de um cliente - Acesso Admin. ")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/v1/cliente/{clienteid}")
    public void excluirCliente (Integer clienteid) throws ClienteNaoEncontradoException {
        clienteService.delete(clienteid);
    }

    /*    @GetMapping(path = "/v1/cliente/by-id/{id}")
    @ApiOperation(value = "Consulta o perfil do usuário - Acesso Admin e Usuário. ")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    public ResponseEntity<ClienteDTO> findByIdAuthenticationPrincipal(@PathVariable Integer id,
                                                                 @AuthenticationPrincipal UserDetails userDetails) {
        log.info(userDetails);
        return ResponseEntity.ok(clienteService.buscarCliente(id));
    }*/

}
