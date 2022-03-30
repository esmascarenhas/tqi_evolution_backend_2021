package br.com.tqi.tqi_evolution_avaliacao.unitTestes.services;

import br.com.tqi.tqi_evolution_avaliacao.api.dto.mapper.ClienteMapper;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.imput.ClienteDTOImput;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.response.MessageResponse;
import br.com.tqi.tqi_evolution_avaliacao.domain.entity.Cliente;
import br.com.tqi.tqi_evolution_avaliacao.domain.repository.ClienteRepository;
import br.com.tqi.tqi_evolution_avaliacao.domain.service.cliente.ClienteService;
import br.com.tqi.tqi_evolution_avaliacao.unitTestes.util.creator.ClienteDTOImputBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTeste {

    @Mock
    private ClienteRepository repository;
    @Mock
    private ClienteMapper mapper;
    @InjectMocks
    private ClienteService service;

    @Test
    @DisplayName("Mensagem de Sucesso quando cadastra cliente.")
    void create_GivenClientRequestThenReturnSucessMessageSaved(){
     /*   //given
        ClienteDTOImput clienteEsperado = ClienteDTOImputBuilder.builder().build().toClienteDTOImput();
        Cliente clienteSalvoEsperado = mapper.toEntity(clienteEsperado);

        //when
        when(repository.save(clienteSalvoEsperado)).thenReturn(clienteSalvoEsperado);

        //Then
        MessageResponse createMessage = createExpectedSuccessMessage(clienteSalvoEsperado.getId());
        MessageResponse SucessMessage = service.create(clienteEsperado);
*/
    }
/*
    private MessageResponse createExpectedSuccessMessage(Integer savedClientId) {
        return MessageResponse.builder()
                .message("Client successfully created with ID " + savedClientId)
                .build();
    }*/
}
