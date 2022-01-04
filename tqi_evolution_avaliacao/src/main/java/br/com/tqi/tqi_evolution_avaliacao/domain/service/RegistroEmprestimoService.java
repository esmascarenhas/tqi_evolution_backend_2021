package br.com.tqi.tqi_evolution_avaliacao.domain.service;

import br.com.tqi.tqi_evolution_avaliacao.domain.entity.Cliente;
import br.com.tqi.tqi_evolution_avaliacao.domain.entity.Emprestimo;
import br.com.tqi.tqi_evolution_avaliacao.domain.exception.ClienteNaoEncontradoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RegistroEmprestimoService {

    private BuscarClienteService buscarClienteService;

    @Transactional
    public Emprestimo registrar (Integer clienteid) throws ClienteNaoEncontradoException {
       Cliente cliente = buscarClienteService.listarCliente(clienteid);
       return cliente.adicionaEmprestimo(clienteid);
    }
}
