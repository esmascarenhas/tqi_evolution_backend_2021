package br.com.tqi.tqi_evolution_avaliacao.api.controller;

import br.com.tqi.tqi_evolution_avaliacao.api.dto.mapper.EmprestimoMapper;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.DetalheEmprestimo;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.EmprestimoDTO;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.EmprestimoResumoDTO;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.imput.EmprestimoDTOImput;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.response.MessageResponse;
import br.com.tqi.tqi_evolution_avaliacao.domain.entity.Cliente;
import br.com.tqi.tqi_evolution_avaliacao.domain.exception.ClienteNaoEncontradoException;
import br.com.tqi.tqi_evolution_avaliacao.domain.exception.EmprestimoNaoEncontradoException;
import br.com.tqi.tqi_evolution_avaliacao.domain.repository.EmprestimoRepository;
import br.com.tqi.tqi_evolution_avaliacao.domain.service.cliente.BuscarClienteService;
import br.com.tqi.tqi_evolution_avaliacao.domain.service.emprestimo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api("SAC API Rest - Sistema de Análise de Crédito")
@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/emprestimo")
public class EmprestimoController {

    private EmprestimoRepository emprestimoRepository;
    private SolicitacaodeEmprestimoService solicitacaodeEmprestimoService;
    private FinalizarEmprestimoService finalizarEmprestimoService;
    private CancelarEmprestimoService cancelarEmprestimoService;
    private SuspenderEmprestimoService suspenderEmprestimoService;
    private EmprestimoService emprestimoService;
    private EmprestimoMapper emprestimoMapper;
    private ConsultaDeEmprestimoService consultaDeEmprestimoService;
    private BuscarClienteService buscarClienteService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLES_ADMIN')")
    @ApiOperation(value = "Lista os emprestimos cadastrados. ")
    public List<EmprestimoDTO> listAll(){

        return emprestimoMapper.toCollectionModel(emprestimoService.listarEmprestimo());
    }
    @GetMapping("/detalhe/{emprestimoid}")
    @PreAuthorize("hasAnyRole('ROLES_ADMIN','ROLES_USER')")
    @ApiOperation(value = "Detalha um emprestimo específico. ")
    public ResponseEntity<DetalheEmprestimo> detalheById(@PathVariable Integer emprestimoid,
                                                         @RequestHeader String Authorization){
        return emprestimoRepository.findById(emprestimoid)
                .map(emprestimo -> ResponseEntity.ok(emprestimoMapper.toModel3(emprestimo)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{clienteid}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @ApiOperation(value = "Acompanhamento das solicitações de empréstimo.")
    public List<EmprestimoResumoDTO> listarEmprestimo (@PathVariable Integer clienteid,
                                                       @RequestHeader String Authorization) throws ClienteNaoEncontradoException {
        Cliente cliente = buscarClienteService.listarCliente(clienteid);
        return emprestimoMapper.toCollectionModel2(cliente.getEmprestimoList());
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLES_ADMIN')")
    @ApiOperation(value = "Realiza o lançamento de um Emprestimo. ")
    public MessageResponse create (@Valid @RequestBody EmprestimoDTOImput emprestimoDTOImput,
                                   @RequestHeader String Authorization) throws ClienteNaoEncontradoException {

        return solicitacaodeEmprestimoService.solicitar(emprestimoDTOImput);
    }


    @ApiOperation(value = "Atualiza o cadastro de um Emprestimo. ")
    @PutMapping("/{emprestimoid}")
    @PreAuthorize("hasRole('ROLES_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponse update(@PathVariable Integer emprestimoid,
                                  @Valid @RequestBody EmprestimoDTO emprestimoDTO,
                                  @RequestHeader String Authorization) throws EmprestimoNaoEncontradoException {
        return emprestimoService.update(emprestimoid,emprestimoDTO);
    }

    @PutMapping("/{emprestimoId}/finaliza")
    @PreAuthorize("hasRole('ROLES_ADMIN')")
    @ApiOperation(value = "Finaliza um emprestimo. ")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizar(@PathVariable Integer emprestimoId) throws EmprestimoNaoEncontradoException {
        finalizarEmprestimoService.finalizar(emprestimoId);
    }
    @PutMapping("/{emprestimoId}/cancela")
    @PreAuthorize("hasRole('ROLES_ADMIN')")
    @ApiOperation(value = "Cancela um emprestimo. ")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelar(@PathVariable Integer emprestimoId) throws EmprestimoNaoEncontradoException {
        cancelarEmprestimoService.cancelar(emprestimoId);
    }

    @PutMapping("/{emprestimoId}/suspende")
    @PreAuthorize("hasRole('ROLES_ADMIN')")
    @ApiOperation(value = "Suspende um emprestimo. ")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void suspender(@PathVariable Integer emprestimoId) throws EmprestimoNaoEncontradoException {
        suspenderEmprestimoService.suspender(emprestimoId);
    }


    @ApiOperation(value = "Exclui um emprestimo do cadastro. ")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLES_ADMIN')")
    @DeleteMapping("/{emprestimoid}")
    public void excluirEmprestimo (@PathVariable Integer emprestimoid,
                                   @RequestHeader String Authorization) throws EmprestimoNaoEncontradoException {
        emprestimoService.delete(emprestimoid);
    }
    private MessageResponse createMessage (String msm, Integer id, String name){
        return MessageResponse.builder().message(msm + id + name).build();

    }


}
