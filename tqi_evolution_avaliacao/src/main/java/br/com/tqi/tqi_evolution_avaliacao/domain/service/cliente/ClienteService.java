package br.com.tqi.tqi_evolution_avaliacao.domain.service.cliente;


import br.com.tqi.tqi_evolution_avaliacao.api.dto.mapper.ClienteMapper;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.ClienteDTO;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.imput.ClienteDTOImput;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.response.MessageResponse;
import br.com.tqi.tqi_evolution_avaliacao.domain.entity.Cliente;
import br.com.tqi.tqi_evolution_avaliacao.domain.enums.RolesUser;
import br.com.tqi.tqi_evolution_avaliacao.domain.exception.ClienteNaoEncontradoException;
import br.com.tqi.tqi_evolution_avaliacao.domain.exception.NegocioException;
import br.com.tqi.tqi_evolution_avaliacao.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class ClienteService {

    private ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public List<Cliente> listarCliente(){
        return clienteRepository.findAll();
    }

    public ClienteDTO buscarCliente (Integer clienteid){
        Cliente cliente = clienteRepository.findById(clienteid)
                .orElseThrow(() -> new NegocioException("Cliente não encontrado"));
        return clienteMapper.toModel(cliente);
    }
/*    @Transactional
    public MessageResponse create (Cliente cliente){
        clienteRepository.save(cliente);
        MessageResponse messageResponse = createMessage("Cliente cadastrado com sucesso ", cliente.getId(), " - " + cliente.getNome());
        return messageResponse;
    }*/



    @Transactional
    public MessageResponse create (ClienteDTOImput clienteDTOImput){
        Cliente novoCliente = clienteMapper.toEntity(clienteDTOImput);
        novoCliente.getUsuario().setRoles(RolesUser.ROLES_USER);
        Cliente alunoCadastrado = clienteRepository.save(novoCliente);
        MessageResponse messageResponse = createMessage("Cliente cadastrado com sucesso ", alunoCadastrado.getId(), " - " + alunoCadastrado.getNome());
        return messageResponse;
    }



    public MessageResponse update (Integer id, ClienteDTO clienteDTO) throws ClienteNaoEncontradoException {
        clienteRepository.findById(id).orElseThrow(() -> new ClienteNaoEncontradoException(id));
        Cliente clienteAtualizado = clienteMapper.toEntity2(clienteDTO);
        Cliente clientesalvo = clienteRepository.save(clienteAtualizado);

        MessageResponse messageResponse = createMessage("Cliente atualizado com sucesso ", clientesalvo.getId()," - " + clientesalvo.getNome());
        return messageResponse;

    }

    /*public MessageResponse alterar (Integer id, ClienteDTO clienteDTO) throws ClienteNaoEncontradoException {
        Cliente clienteExiste =clienteRepository.findById(id).orElseThrow(() -> new ClienteNaoEncontradoException(id));

        clienteExiste.setId(clienteDTO.getId());
        clienteExiste.setCpf(clienteDTO.getCpf());
        clienteExiste.setEmail(clienteDTO.getEmail());
        clienteExiste.setEndereco(clienteDTO.getEndereco().getBairro().);
        clienteExiste.setRenda(clienteDTO.getRenda());
        clienteExiste.setNome(clienteDTO.getNome());
        clienteExiste.setRg(clienteDTO.getRg());
        clienteExiste.setSenha(clienteDTO.getSenha());


        Cliente clienteAtualizado = clienteMapper.toEntity2(clienteDTO);
        Cliente clientesalvo = clienteRepository.save(clienteAtualizado);

        MessageResponse messageResponse = createMessage("Cliente atualizado com sucesso ", clientesalvo.getId()," - " + clientesalvo.getNome());
        return messageResponse;

    }*/
    @Transactional
    public void delete (Integer id) throws ClienteNaoEncontradoException {
        clienteRepository.findById(id).orElseThrow(() -> new ClienteNaoEncontradoException(id));
        clienteRepository.deleteById(id);
    }

    private MessageResponse createMessage (String msm, Integer id, String name){
        return MessageResponse.builder().message(msm + id + name).build();

    }


}
