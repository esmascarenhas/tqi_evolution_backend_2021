package br.com.tqi.tqi_evolution_avaliacao.api.dto.mapper;

import br.com.tqi.tqi_evolution_avaliacao.api.controller.ClienteController;
import br.com.tqi.tqi_evolution_avaliacao.api.controller.EmprestimoController;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.ClienteDTO;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.DetalheEmprestimo;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.EmprestimoDTO;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.EmprestimoResumoDTO;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.imput.ClienteDTOImput;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.imput.EmprestimoDTOImput;
import br.com.tqi.tqi_evolution_avaliacao.domain.entity.Cliente;
import br.com.tqi.tqi_evolution_avaliacao.domain.entity.Emprestimo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@AllArgsConstructor
public class EmprestimoMapper {

    private ModelMapper modelMapper;

    public EmprestimoDTO toModel (Emprestimo emprestimo){
        EmprestimoDTO emprestimoDTO = modelMapper.map(emprestimo,EmprestimoDTO.class);
        emprestimoDTO.add(linkTo(methodOn(EmprestimoController.class).listAll()).withRel("Lista de Emprestimo"));
        return emprestimoDTO;
    }
    public EmprestimoResumoDTO toModel2 (Emprestimo emprestimo){
        EmprestimoResumoDTO emprestimoResumoDTO = modelMapper.map(emprestimo,EmprestimoResumoDTO.class);
        emprestimoResumoDTO.add(linkTo(methodOn(EmprestimoController.class).listAll()).withRel("Lista de Emprestimo"));
        return emprestimoResumoDTO;
    }
    public DetalheEmprestimo toModel3 (Emprestimo emprestimo){
        DetalheEmprestimo detalheEmprestimo = modelMapper.map(emprestimo,DetalheEmprestimo.class);
        detalheEmprestimo.add(linkTo(methodOn(EmprestimoController.class).listAll()).withRel("Lista de Emprestimo"));
        return detalheEmprestimo;
    }

    public List<EmprestimoDTO> toCollectionModel(List<Emprestimo> emprestimos){
        return emprestimos.stream().map(this::toModel).collect(Collectors.toList());
    }
    public List<EmprestimoResumoDTO> toCollectionModel2(List<Emprestimo> emprestimos){
        return emprestimos.stream().map(this::toModel2).collect(Collectors.toList());
    }
    public Emprestimo toEntity(EmprestimoDTOImput emprestimoDTOImput){
        return modelMapper.map(emprestimoDTOImput,Emprestimo.class);
    }
    public Emprestimo toEntity2(EmprestimoDTO emprestimoDTO){
        return modelMapper.map(emprestimoDTO,Emprestimo.class);
    }

}
