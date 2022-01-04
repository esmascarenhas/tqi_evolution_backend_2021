package br.com.tqi.tqi_evolution_avaliacao.domain.service;

import br.com.tqi.tqi_evolution_avaliacao.api.dto.mapper.EmprestimoMapper;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.imput.EmprestimoDTOImput;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.response.MessageResponse;
import br.com.tqi.tqi_evolution_avaliacao.domain.entity.Cliente;
import br.com.tqi.tqi_evolution_avaliacao.domain.entity.Emprestimo;
import br.com.tqi.tqi_evolution_avaliacao.domain.enums.StatusEmprestimo;
import br.com.tqi.tqi_evolution_avaliacao.domain.exception.ClienteNaoEncontradoException;
import br.com.tqi.tqi_evolution_avaliacao.domain.repository.EmprestimoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class SolicitacaodeEmprestimoService {


    private EmprestimoRepository emprestimoRepository;
    private EmprestimoMapper emprestimoMapper;
    private BuscarClienteService buscarClienteService;

/*    @Transactional
    public Emprestimo solicitar(Emprestimo emprestimo){
        Cliente cliente = clienteService.buscarCliente(emprestimo.getCliente().getId());

        emprestimo.setCliente(cliente);
        emprestimo.setStatus(StatusEmprestimo.ATIVO);

        return emprestimoRepository.save(emprestimo);

    }*/

    @Transactional
    public MessageResponse solicitar(EmprestimoDTOImput emprestimoDTOImput) throws ClienteNaoEncontradoException {
        Cliente cliente = buscarClienteService.listarCliente(emprestimoDTOImput.getClienteid().getId());
        Emprestimo novoEmprestimo = emprestimoMapper.toEntity(emprestimoDTOImput);
        Emprestimo emprestimoCadastrado = emprestimoRepository.save(novoEmprestimo);

        emprestimoCadastrado.setCliente(cliente);
        emprestimoCadastrado.setStatus(StatusEmprestimo.ATIVO);

        MessageResponse messageResponse = createMessage("Emprestimo cadastrado com sucesso ", emprestimoCadastrado.getCodigoEmprestimo(), " - " + emprestimoCadastrado.getCliente().getNome());
        return messageResponse;


    }
 /*   @Transactional
    public MessageResponse create (EmprestimoDTOImput emprestimoDTOImput){
        Emprestimo novoEmprestimo = emprestimoMapper.toEntity(emprestimoDTOImput);
        Emprestimo emprestimoCadastrado = emprestimoRepository.save(novoEmprestimo);
        MessageResponse messageResponse = createMessage("Emprestimo cadastrado com sucesso ", emprestimoCadastrado.getId(), " - " + emprestimoCadastrado.getCliente().getNome());
        return messageResponse;
    }*/
 private MessageResponse createMessage (String msm, Integer id, String name){
     return MessageResponse.builder().message(msm + id + name).build();

 }

}

