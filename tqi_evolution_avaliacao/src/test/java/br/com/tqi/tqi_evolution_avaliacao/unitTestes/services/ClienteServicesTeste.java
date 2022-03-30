package br.com.tqi.tqi_evolution_avaliacao.unitTestes.services;

import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.ClienteDTO;
import br.com.tqi.tqi_evolution_avaliacao.domain.entity.Cliente;
import br.com.tqi.tqi_evolution_avaliacao.domain.repository.ClienteRepository;
import br.com.tqi.tqi_evolution_avaliacao.domain.service.cliente.ClienteService;
import br.com.tqi.tqi_evolution_avaliacao.unitTestes.util.creator.ClienteCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Test;

import javax.ws.rs.BadRequestException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class ClienteServicesTeste {

   /* @InjectMocks
    private ClienteService clienteService;
    @Mock
    private ClienteRepository clienteRepositoryMock;

    @BeforeEach
    void setUp(){
        List<Cliente> clienteList = new ArrayList<>(List.of(ClienteCreator.validaCliente()));
        BDDMockito.when(clienteRepositoryMock.findAll())
                .thenReturn(clienteList);

        BDDMockito.when(clienteRepositoryMock.findAll())
                .thenReturn(List.of(ClienteCreator.validaCliente()));

        BDDMockito.when(clienteRepositoryMock.findById(ArgumentMatchers.anyInt()))
                .thenReturn(Optional.of(ClienteCreator.validaCliente()));


        BDDMockito.when(clienteRepositoryMock.save(ArgumentMatchers.any(Cliente.class)))
                .thenReturn(ClienteCreator.validaCliente());

        BDDMockito.doNothing().when(clienteRepositoryMock).delete(ArgumentMatchers.any(Cliente.class));
    }
    @Test
    @DisplayName("listAll retorna lista de cliente quando sucesso.")
    void listAll_ReturnsListOfClient_WhenSuccessful(){
        String expectedName = ClienteCreator.validaCliente().getNome();

        List<Cliente> clienteList = clienteService.listarCliente();

        Assertions.assertThat(clienteList).isNotNull();

        Assertions.assertThat(clienteList)
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(clienteList.get(0).getNome()).isEqualTo(expectedName);
    }


  *//*  @Test
    @DisplayName("findByIdOrThrowBadRequestException returns client when successful")
    void findByIdOrThrowBadRequestException_ReturnsCliente_WhenSuccessful(){
        Integer expectedId = ClienteCreator.validaCliente().getId();

        ClienteDTO cliente = clienteService.buscarCliente(1);

        Assertions.assertThat(cliente).isNotNull();

        Assertions.assertThat(cliente.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findByIdOrThrowBadRequestException throws BadRequestException when client is not found")
    void findByIdOrThrowBadRequestException_ThrowsBadRequestException_WhenClientIsNotFound(){
        BDDMockito.when(clienteRepositoryMock.findById(ArgumentMatchers.anyInt()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> clienteService.buscarCliente(1));
    }
*/


    @Test
    @DisplayName("delete removes cliente when successful")
    void delete_RemovesCliente_WhenSuccessful(){

/*
        Assertions.assertThatCode(() ->clienteService.delete(1))
                .doesNotThrowAnyException();
*/

    }
}
