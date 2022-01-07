package br.com.tqi.tqi_evolution_avaliacao.domain.service.emprestimo;


import br.com.tqi.tqi_evolution_avaliacao.domain.entity.Emprestimo;
import br.com.tqi.tqi_evolution_avaliacao.domain.exception.EmprestimoNaoEncontradoException;
import br.com.tqi.tqi_evolution_avaliacao.domain.repository.EmprestimoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BuscaEmprestimoService {

        private EmprestimoRepository repository;

    public Emprestimo buscarEmprestimo(Integer emprestimoid) throws EmprestimoNaoEncontradoException {
        return repository.findById(emprestimoid)
                .orElseThrow(() -> new EmprestimoNaoEncontradoException(emprestimoid));
    }
}
