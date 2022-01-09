package br.com.tqi.tqi_evolution_avaliacao.api.controller;

import br.com.tqi.tqi_evolution_avaliacao.domain.service.cliente.ClienteService;
import br.com.tqi.tqi_evolution_avaliacao.domain.service.emprestimo.CancelarEmprestimoService;
import br.com.tqi.tqi_evolution_avaliacao.domain.service.emprestimo.EmprestimoService;
import br.com.tqi.tqi_evolution_avaliacao.domain.service.emprestimo.SolicitacaodeEmprestimoService;
import br.com.tqi.tqi_evolution_avaliacao.domain.service.emprestimo.SuspenderEmprestimoService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class EmprestimoControllerTest {

    @InjectMocks
    private EmprestimoController emprestimoController;
    @Mock
    private EmprestimoService emprestimoService;
    @Mock
    private SolicitacaodeEmprestimoService solicitacaodeEmprestimoService;
    @Mock
    private CancelarEmprestimoService cancelarEmprestimoService;
    @Mock
    private SuspenderEmprestimoService suspenderEmprestimoService;



}