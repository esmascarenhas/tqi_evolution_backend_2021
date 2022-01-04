package br.com.tqi.tqi_evolution_avaliacao.api.dto.mapper;

import br.com.tqi.tqi_evolution_avaliacao.api.controller.ClienteController;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.ClienteDTO;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.imput.ClienteDTOImput;
import br.com.tqi.tqi_evolution_avaliacao.domain.entity.Cliente;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ClienteMapper {

    public ModelMapper modelMapper;

    public ClienteDTO toModel (Cliente cliente){
        ClienteDTO clienteDTO = modelMapper.map(cliente,ClienteDTO.class);
        clienteDTO.add(linkTo(methodOn(ClienteController.class).listAll()).withRel("Lista de Clientes"));
        return clienteDTO;
    }

    public List<ClienteDTO> toCollectionModel(List<Cliente> cliente){
        return cliente.stream().map(this::toModel).collect(Collectors.toList());
    }
    public Cliente toEntity(ClienteDTOImput clienteDTOImput){
        return modelMapper.map(clienteDTOImput,Cliente.class);
    }

    public Cliente toEntity2(ClienteDTO clienteDTO){

        return modelMapper.map(clienteDTO,Cliente.class);
    }
}
