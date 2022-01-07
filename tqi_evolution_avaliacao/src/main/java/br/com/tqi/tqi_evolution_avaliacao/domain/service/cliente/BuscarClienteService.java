package br.com.tqi.tqi_evolution_avaliacao.domain.service.cliente;

import br.com.tqi.tqi_evolution_avaliacao.domain.entity.Cliente;
import br.com.tqi.tqi_evolution_avaliacao.domain.entity.Emprestimo;
import br.com.tqi.tqi_evolution_avaliacao.domain.exception.ClienteNaoEncontradoException;
import br.com.tqi.tqi_evolution_avaliacao.domain.exception.NegocioException;
import br.com.tqi.tqi_evolution_avaliacao.domain.repository.ClienteRepository;
import br.com.tqi.tqi_evolution_avaliacao.domain.repository.EmprestimoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.Access;

@Service
@AllArgsConstructor
public class BuscarClienteService {

        private ClienteRepository clienteRepository;

    public Cliente listarCliente(Integer clienteid) throws ClienteNaoEncontradoException {
        return clienteRepository.findById(clienteid)
                .orElseThrow(() -> new ClienteNaoEncontradoException(clienteid));
    }
}
