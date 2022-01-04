package br.com.tqi.tqi_evolution_avaliacao.domain.service;

import br.com.tqi.tqi_evolution_avaliacao.api.dto.mapper.EmprestimoMapper;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.EmprestimoDTO;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.imput.EmprestimoDTOImput;
import br.com.tqi.tqi_evolution_avaliacao.domain.entity.Cliente;
import br.com.tqi.tqi_evolution_avaliacao.domain.entity.Emprestimo;
import br.com.tqi.tqi_evolution_avaliacao.domain.exception.ClienteNaoEncontradoException;
import br.com.tqi.tqi_evolution_avaliacao.domain.exception.NegocioException;
import br.com.tqi.tqi_evolution_avaliacao.domain.repository.ClienteRepository;
import br.com.tqi.tqi_evolution_avaliacao.domain.repository.EmprestimoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ConsultaDeEmprestimoService {

    private EmprestimoRepository emprestimoRepository;
    private EmprestimoMapper emprestimoMapper;
    private ClienteService clienteService;
    private ClienteRepository clienteRepository;


    public EmprestimoDTO listarEmprestimo(EmprestimoDTO emprestimoDTO){
                Emprestimo emprestimo = emprestimoRepository.findById(emprestimoDTO.getCodigoEmprestimo())
                .orElseThrow(() -> new NegocioException("Emprestimo não encontrado"));
                emprestimo.getCodigoEmprestimo();
                emprestimo.getCliente();
                emprestimo.getValorEmprestimo();
                emprestimo.getQuantidadeParcelas();
        return emprestimoMapper.toModel(emprestimo);

    }
/*
    public List<EmprestimoDTO> consultaEmprestimo(Integer clienteid) throws ClienteNaoEncontradoException {
        Cliente cliente = clienteRepository.findById(clienteid)
                .orElseThrow(() -> new ClienteNaoEncontradoException(clienteid));

        Emprestimo emp = new Emprestimo();
        List<Emprestimo> emprestimo = emp.getCliente().getId();
        emprestimo.add(new Emprestimo());
        Emprestimo emp = emprestimoRepository.findByClienteid(clienteid);
        Emprestimo emprestimo1 = new Emprestimo();
        emprestimo1.getCliente().getNome();
        emprestimo1.getId();


        emprestimo.add(emp.getId());
        emprestimo.getCliente();
        emprestimo.getValorEmprestimo();
        emprestimo.getQuantidadeParcelas();
        return emprestimoMapper.toCollectionModel(emprestimo1);

    }
*/


}
