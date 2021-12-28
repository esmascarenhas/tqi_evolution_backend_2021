package br.com.tqi.tqi_evolution_avaliacao.api.controller;

import br.com.tqi.tqi_evolution_avaliacao.api.dto.mapper.EmprestimoMapper;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.EmprestimoDTO;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.imput.ClienteDTOImput;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.imput.EmprestimoDTOImput;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.response.MessageResponse;
import br.com.tqi.tqi_evolution_avaliacao.domain.entity.Emprestimo;
import br.com.tqi.tqi_evolution_avaliacao.domain.exception.EmprestimoNaoEncontradoException;
import br.com.tqi.tqi_evolution_avaliacao.domain.repository.EmprestimoRepository;
import br.com.tqi.tqi_evolution_avaliacao.domain.service.EmprestimoService;
import br.com.tqi.tqi_evolution_avaliacao.domain.service.SolicitacaodeEmprestimoService;
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
@RequestMapping("/api/v1/emprestimo")
public class EmprestimoController {

    private EmprestimoRepository emprestimoRepository;
    private SolicitacaodeEmprestimoService solicitacaodeEmprestimoService;
    private EmprestimoService emprestimoService;
    private EmprestimoMapper emprestimoMapper;

    @GetMapping
    @ApiOperation(value = "Lista os emprestimos cadastrados. ")
    public List<EmprestimoDTO> listAll(){

        return emprestimoMapper.toCollectionModel(emprestimoService.listarEmprestimo());
    }
    @GetMapping("/{emprestimoid}")
    @ApiOperation(value = "Detalha um emprestimo específico. ")
    public ResponseEntity<EmprestimoDTO> EmprestimoById(@PathVariable Integer emprestimoid){
        return emprestimoRepository.findById(emprestimoid)
                .map(emprestimo -> ResponseEntity.ok(emprestimoMapper.toModel(emprestimo)))
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Realiza o lançamento de um Emprestimo. ")
    public MessageResponse create (@Valid @RequestBody EmprestimoDTOImput emprestimoDTOImput){

        return solicitacaodeEmprestimoService.solicitar(emprestimoDTOImput);
    }



    @ApiOperation(value = "Atualiza o cadastro de um Emprestimo. ")
    @PutMapping("/{emprestimoid}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponse update(@PathVariable Integer emprestimoid, @Valid @RequestBody EmprestimoDTOImput emprestimoDTOImput) throws EmprestimoNaoEncontradoException {
        return emprestimoService.update(emprestimoid,emprestimoDTOImput);
    }
    @ApiOperation(value = "Exclui um emprestimo do cadastro. ")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{emprestimoid}")
    public void excluirEmprestimo (@PathVariable Integer emprestimoid) throws EmprestimoNaoEncontradoException {
        emprestimoService.delete(emprestimoid);
    }
    private MessageResponse createMessage (String msm, Integer id, String name){
        return MessageResponse.builder().message(msm + id + name).build();

    }

}
