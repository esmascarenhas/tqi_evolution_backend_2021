package br.com.tqi.tqi_evolution_avaliacao.domain.service.emprestimo;

import br.com.tqi.tqi_evolution_avaliacao.api.dto.mapper.EmprestimoMapper;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.imput.EmprestimoDTOImput;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.response.MessageResponse;
import br.com.tqi.tqi_evolution_avaliacao.domain.entity.Cliente;
import br.com.tqi.tqi_evolution_avaliacao.domain.entity.Emprestimo;
import br.com.tqi.tqi_evolution_avaliacao.domain.enums.StatusEmprestimo;
import br.com.tqi.tqi_evolution_avaliacao.domain.exception.ClienteNaoEncontradoException;
import br.com.tqi.tqi_evolution_avaliacao.domain.exception.NegocioException;
import br.com.tqi.tqi_evolution_avaliacao.domain.repository.EmprestimoRepository;
import br.com.tqi.tqi_evolution_avaliacao.domain.service.cliente.BuscarClienteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@AllArgsConstructor
public class SolicitacaodeEmprestimoService {


    private EmprestimoRepository emprestimoRepository;
    private EmprestimoMapper emprestimoMapper;
    private BuscarClienteService buscarClienteService;


    @Transactional
    public MessageResponse solicitar(EmprestimoDTOImput emprestimoDTOImput) throws ClienteNaoEncontradoException {
        Cliente cliente = buscarClienteService.listarCliente(emprestimoDTOImput.getClienteid().getId());
        Emprestimo novoEmprestimo = emprestimoMapper.toEntity(emprestimoDTOImput);
        if (novoEmprestimo.getDataPrimeiraParcela().isAfter(LocalDate.now().plus(2, ChronoUnit.MONTHS))) {
            throw new NegocioException("Data da Primeira Parcela deve ser menor que 60 dias");
        }
        Emprestimo emprestimoCadastrado = emprestimoRepository.save(novoEmprestimo);

        emprestimoCadastrado.setCliente(cliente);
        emprestimoCadastrado.setStatus(StatusEmprestimo.ATIVO);

        MessageResponse messageResponse = createMessage("Emprestimo cadastrado com sucesso - Código do Emprestimo: ", emprestimoCadastrado.getCodigoEmprestimo(), " - Cliente: " + emprestimoCadastrado.getCliente().getNome());
        return messageResponse;


    }

 private MessageResponse createMessage (String msm, Integer id, String name){
     return MessageResponse.builder().message(msm + id + name).build();

 }

}

