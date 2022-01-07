package br.com.tqi.tqi_evolution_avaliacao.api.controller;

import br.com.tqi.tqi_evolution_avaliacao.domain.service.cliente.ClienteService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
class ClienteControllerTest {

    @InjectMocks
    private ClienteController clienteController;
    @Mock
    private ClienteService clienteService;

}