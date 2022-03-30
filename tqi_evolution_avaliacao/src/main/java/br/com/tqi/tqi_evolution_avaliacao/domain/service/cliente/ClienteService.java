package br.com.tqi.tqi_evolution_avaliacao.domain.service.cliente;


import br.com.tqi.tqi_evolution_avaliacao.api.dto.mapper.ClienteMapper;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.ClienteDTO;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.imput.ClienteDTOImput;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.response.MessageResponse;
import br.com.tqi.tqi_evolution_avaliacao.domain.entity.Cliente;
import br.com.tqi.tqi_evolution_avaliacao.domain.entity.Endereco;
import br.com.tqi.tqi_evolution_avaliacao.domain.entity.UserSecurity;
import br.com.tqi.tqi_evolution_avaliacao.domain.enums.RolesUser;
import br.com.tqi.tqi_evolution_avaliacao.domain.exception.ClienteNaoEncontradoException;
import br.com.tqi.tqi_evolution_avaliacao.domain.exception.NegocioException;
import br.com.tqi.tqi_evolution_avaliacao.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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


    @Transactional
    public MessageResponse create (ClienteDTOImput clienteDTOImput){
        Cliente novoCliente = clienteMapper.toEntity(clienteDTOImput);
        novoCliente.getUsuario().setRoles(RolesUser.ROLE_USER);
        Cliente alunoCadastrado = clienteRepository.save(novoCliente);
        MessageResponse messageResponse = createMessage("Cliente cadastrado com sucesso ", alunoCadastrado.getId(), " - " + alunoCadastrado.getNome());
        return messageResponse;
    }



    public MessageResponse update (Integer id, ClienteDTO clienteDTO) throws ClienteNaoEncontradoException {
       var cli= clienteRepository.findById(id).orElseThrow(() -> new ClienteNaoEncontradoException(id));
        Cliente clientesalvo = clienteMapper.toEntity2(clienteDTO);
        Cliente clienteatualizado = clienteRepository.save(cli);

/*

            UserSecurity user = new UserSecurity();
        user.setSenha(clientesalvo.getUsuario().getSenha());
        user.setEmail(clientesalvo.getUsuario().getEmail());
        cli.setUsuario(user);

        cli.setNome(clientesalvo.getNome());
        cli.setRg(clientesalvo.getRg());
        cli.setRenda(clientesalvo.getRenda());
        cli.setCpf(clientesalvo.getCpf());

        Endereco end = new Endereco();

            end.setLogradouro(clientesalvo.getEndereco().getLogradouro());
            end.setComplemento(clientesalvo.getEndereco().getComplemento());
            end.setNumero(clientesalvo.getEndereco().getNumero());
            end.setBairro(clientesalvo.getEndereco().getBairro());
            end.setCidade(clientesalvo.getEndereco().getCidade());
            end.setEstado(clientesalvo.getEndereco().getEstado());
            end.setPais(clientesalvo.getEndereco().getPais());
            end.setCep(clientesalvo.getEndereco().getCep());

            cli.setEndereco(end);*/


            MessageResponse messageResponse = createMessage("Cliente atualizado com sucesso ", clienteatualizado.getId()," - " + clienteatualizado.getNome());
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

    private Cliente responseBody(Integer id) throws ClienteNaoEncontradoException {
        var cli= clienteRepository.findById(id).orElseThrow(() -> new ClienteNaoEncontradoException(id));

        UserSecurity user = new UserSecurity();
        user.setSenha(cli.getUsuario().getSenha());
        user.setEmail(cli.getUsuario().getEmail());
        cli.setUsuario(user);

        cli.setNome(cli.getNome());
        cli.setRg(cli.getRg());
        cli.setRenda(cli.getRenda());
        cli.setCpf(cli.getCpf());

        Endereco end = new Endereco();

        end.setLogradouro(cli.getEndereco().getLogradouro());
        end.setComplemento(cli.getEndereco().getComplemento());
        end.setNumero(cli.getEndereco().getNumero());
        end.setBairro(cli.getEndereco().getBairro());
        end.setCidade(cli.getEndereco().getCidade());
        end.setEstado(cli.getEndereco().getEstado());
        end.setPais(cli.getEndereco().getPais());
        end.setCep(cli.getEndereco().getCep());

        cli.setEndereco(end);
        return cli;
    }


}
