package br.com.tqi.tqi_evolution_avaliacao.domain.service;


import br.com.tqi.tqi_evolution_avaliacao.api.dto.mapper.ClienteMapper;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.imput.ClienteDTOImput;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.response.MessageResponse;
import br.com.tqi.tqi_evolution_avaliacao.domain.entity.Cliente;
import br.com.tqi.tqi_evolution_avaliacao.domain.exception.ClienteNaoEncontradoException;
import br.com.tqi.tqi_evolution_avaliacao.domain.exception.NegocioException;
import br.com.tqi.tqi_evolution_avaliacao.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class ClienteService {

    private ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public List<Cliente> listarCliente(){
        return clienteRepository.findAll();
    }

    public Cliente buscarCliente (Integer clienteid){
        return clienteRepository.findById(clienteid)
                .orElseThrow(() -> new NegocioException("Cliente não encontrado"));
    }

    @Transactional
    public MessageResponse create (ClienteDTOImput clienteDTOImput){
        Cliente novoAluno = clienteMapper.toEntity(clienteDTOImput);
        Cliente alunoCadastrado = clienteRepository.save(novoAluno);
        MessageResponse messageResponse = createMessage("Cliente cadastrado com sucesso ", alunoCadastrado.getId(), " - " + alunoCadastrado.getNome());
        return messageResponse;
    }


    public MessageResponse update (Integer id, ClienteDTOImput clienteDTOImput) throws ClienteNaoEncontradoException {
        clienteRepository.findById(id).orElseThrow(() -> new ClienteNaoEncontradoException(id));
        Cliente clienteAtualizado = clienteMapper.toEntity(clienteDTOImput);
        Cliente clientesalvo = clienteRepository.save(clienteAtualizado);

        MessageResponse messageResponse = createMessage("Cliente atualizado com sucesso ", clientesalvo.getId()," - " + clientesalvo.getNome());
        return messageResponse;

    }
    @Transactional
    public void delete (Integer id) throws ClienteNaoEncontradoException {
        clienteRepository.findById(id).orElseThrow(() -> new ClienteNaoEncontradoException(id));
        clienteRepository.deleteById(id);
    }

    private MessageResponse createMessage (String msm, Integer id, String name){
        return MessageResponse.builder().message(msm + id + name).build();

    }


}
