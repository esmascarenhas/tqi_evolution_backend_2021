package br.com.tqi.tqi_evolution_avaliacao.domain.service;

import br.com.tqi.tqi_evolution_avaliacao.api.dto.mapper.EmprestimoMapper;
import br.com.tqi.tqi_evolution_avaliacao.domain.entity.Cliente;
import br.com.tqi.tqi_evolution_avaliacao.domain.entity.Emprestimo;
import br.com.tqi.tqi_evolution_avaliacao.domain.exception.NegocioException;
import br.com.tqi.tqi_evolution_avaliacao.domain.repository.EmprestimoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConsultaDeEmprestimoService {

    private EmprestimoRepository emprestimoRepository;
    private EmprestimoMapper emprestimoMapper;
    private ClienteService clienteService;


    public Emprestimo listarEmprestimo(Integer emprestimoid){
        return emprestimoRepository.findById(emprestimoid)
                .orElseThrow(() -> new NegocioException("Emprestimo não encontrado"));
    }


}
