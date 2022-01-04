package br.com.tqi.tqi_evolution_avaliacao.api.controller;

import br.com.tqi.tqi_evolution_avaliacao.api.dto.mapper.EmprestimoMapper;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.DetalheEmprestimo;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.EmprestimoDTO;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.EmprestimoResumoDTO;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.imput.ClienteDTOImput;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.imput.EmprestimoDTOImput;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.response.MessageResponse;
import br.com.tqi.tqi_evolution_avaliacao.domain.entity.Cliente;
import br.com.tqi.tqi_evolution_avaliacao.domain.entity.Emprestimo;
import br.com.tqi.tqi_evolution_avaliacao.domain.exception.ClienteNaoEncontradoException;
import br.com.tqi.tqi_evolution_avaliacao.domain.exception.EmprestimoNaoEncontradoException;
import br.com.tqi.tqi_evolution_avaliacao.domain.repository.EmprestimoRepository;
import br.com.tqi.tqi_evolution_avaliacao.domain.service.BuscarClienteService;
import br.com.tqi.tqi_evolution_avaliacao.domain.service.ConsultaDeEmprestimoService;
import br.com.tqi.tqi_evolution_avaliacao.domain.service.EmprestimoService;
import br.com.tqi.tqi_evolution_avaliacao.domain.service.SolicitacaodeEmprestimoService;
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
    private EmprestimoService emprestimoService;
    private EmprestimoMapper emprestimoMapper;
    private ConsultaDeEmprestimoService consultaDeEmprestimoService;
    private BuscarClienteService buscarClienteService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @ApiOperation(value = "Lista os emprestimos cadastrados. ")
    public List<EmprestimoDTO> listAll(){

        return emprestimoMapper.toCollectionModel(emprestimoService.listarEmprestimo());
    }
    @GetMapping("/detalhe/{emprestimoid}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @ApiOperation(value = "Detalha um emprestimo específico. ")
    public ResponseEntity<DetalheEmprestimo> detalheById(@PathVariable Integer emprestimoid,
                                                         @RequestHeader String Authorization){
        return emprestimoRepository.findById(emprestimoid)
                .map(emprestimo -> ResponseEntity.ok(emprestimoMapper.toModel3(emprestimo)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{clienteid}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @ApiOperation(value = "Lista os emprestimos de um cliente. ")
    public List<EmprestimoResumoDTO> listarEmprestimo (@PathVariable Integer clienteid,
                                                       @RequestHeader String Authorization) throws ClienteNaoEncontradoException {
        Cliente cliente = buscarClienteService.listarCliente(clienteid);
        return emprestimoMapper.toCollectionModel2(cliente.getEmprestimoList());
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Realiza o lançamento de um Emprestimo. ")
    public MessageResponse create (@Valid @RequestBody EmprestimoDTOImput emprestimoDTOImput,
                                   @RequestHeader String Authorization) throws ClienteNaoEncontradoException {

        return solicitacaodeEmprestimoService.solicitar(emprestimoDTOImput);
    }



    @ApiOperation(value = "Atualiza o cadastro de um Emprestimo. ")
    @PutMapping("/{emprestimoid}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponse update(@PathVariable Integer emprestimoid,
                                  @Valid @RequestBody EmprestimoDTO emprestimoDTO,
                                  @RequestHeader String Authorization) throws EmprestimoNaoEncontradoException {
        return emprestimoService.update(emprestimoid,emprestimoDTO);
    }
    @ApiOperation(value = "Exclui um emprestimo do cadastro. ")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{emprestimoid}")
    public void excluirEmprestimo (@PathVariable Integer emprestimoid,
                                   @RequestHeader String Authorization) throws EmprestimoNaoEncontradoException {
        emprestimoService.delete(emprestimoid);
    }
    private MessageResponse createMessage (String msm, Integer id, String name){
        return MessageResponse.builder().message(msm + id + name).build();

    }

}
