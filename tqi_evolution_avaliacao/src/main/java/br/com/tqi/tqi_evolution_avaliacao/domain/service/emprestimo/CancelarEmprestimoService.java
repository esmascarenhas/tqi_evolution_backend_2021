package br.com.tqi.tqi_evolution_avaliacao.domain.service.emprestimo;

import br.com.tqi.tqi_evolution_avaliacao.domain.entity.Emprestimo;
import br.com.tqi.tqi_evolution_avaliacao.domain.exception.EmprestimoNaoEncontradoException;
import br.com.tqi.tqi_evolution_avaliacao.domain.repository.EmprestimoRepository;
import br.com.tqi.tqi_evolution_avaliacao.domain.service.emprestimo.BuscaEmprestimoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CancelarEmprestimoService {

    private EmprestimoRepository repository;
    private BuscaEmprestimoService buscaEmprestimo;

	@Transactional
	public void cancelar(Integer emprestimoId) throws EmprestimoNaoEncontradoException {
	Emprestimo emprestimo = buscaEmprestimo.buscarEmprestimo(emprestimoId);

		emprestimo.cancelar();

 	repository.save(emprestimo);
}



}
