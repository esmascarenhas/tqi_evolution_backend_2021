package br.com.tqi.tqi_evolution_avaliacao.unitTestes.services;

import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.ClienteDTO;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.EmprestimoDTO;
import br.com.tqi.tqi_evolution_avaliacao.domain.entity.Cliente;
import br.com.tqi.tqi_evolution_avaliacao.domain.entity.Emprestimo;
import br.com.tqi.tqi_evolution_avaliacao.domain.exception.EmprestimoNaoEncontradoException;
import br.com.tqi.tqi_evolution_avaliacao.domain.repository.EmprestimoRepository;
import br.com.tqi.tqi_evolution_avaliacao.domain.service.emprestimo.BuscaEmprestimoService;
import br.com.tqi.tqi_evolution_avaliacao.domain.service.emprestimo.EmprestimoService;
import br.com.tqi.tqi_evolution_avaliacao.unitTestes.util.creator.ClienteCreator;
import br.com.tqi.tqi_evolution_avaliacao.unitTestes.util.creator.EmprestimoCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.ws.rs.BadRequestException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class EmprestimoServiceTeste {

    @InjectMocks
    private EmprestimoService emprestimoService;
    @InjectMocks
    private BuscaEmprestimoService buscaEmprestimoService;
    @Mock
    private EmprestimoRepository repositoryMock;

    @BeforeEach
    void setUp() {
          List<Emprestimo> emprestimoList = new ArrayList<>(List.of(EmprestimoCreator.createValidEmprestimo()));
            BDDMockito.when(repositoryMock.findAll())
                    .thenReturn(emprestimoList);

            BDDMockito.when(repositoryMock.findAll())
                    .thenReturn(List.of(EmprestimoCreator.createValidEmprestimo()));

            BDDMockito.when(repositoryMock.findById(ArgumentMatchers.anyInt()))
                    .thenReturn(Optional.of(EmprestimoCreator.createValidEmprestimo()));


            BDDMockito.when(repositoryMock.save(ArgumentMatchers.any(Emprestimo.class)))
                    .thenReturn(EmprestimoCreator.createValidEmprestimo());

            BDDMockito.doNothing().when(repositoryMock).delete(ArgumentMatchers.any(Emprestimo.class));
        }
        @Test
        @DisplayName("listAll retorna lista de emprestimo quando sucesso.")
        void listAll_ReturnsListOfEmprestimo_WhenSuccessful () {
            Double expectedValue = EmprestimoCreator.createValidEmprestimo().getValorEmprestimo();

            List<Emprestimo> emprestimoList = emprestimoService.listarEmprestimo();

            Assertions.assertThat(emprestimoList).isNotNull();

            Assertions.assertThat(emprestimoList)
                    .isNotEmpty()
                    .hasSize(1);

            Assertions.assertThat(emprestimoList.get(0).getValorEmprestimo()).isEqualTo(expectedValue);
        }


        @Test
        @DisplayName("findByIdOrThrowBadRequestException returns emprestimo when successful")
        void findByIdOrThrowBadRequestException_ReturnsEmprestimo_WhenSuccessful () throws EmprestimoNaoEncontradoException {
            Integer expectedId = EmprestimoCreator.createValidEmprestimo().getCodigoEmprestimo();

            Emprestimo emprestimo = buscaEmprestimoService.buscarEmprestimo(1);

            Assertions.assertThat(emprestimo).isNotNull();

            Assertions.assertThat(emprestimo.getCodigoEmprestimo()).isNotNull().isEqualTo(expectedId);
        }

        @Test
        @DisplayName("findByIdOrThrowBadRequestException throws BadRequestException when emprestimo is not found")
        void findByIdOrThrowBadRequestException_ThrowsBadRequestException_WhenEmprestimoIsNotFound () {
            BDDMockito.when(repositoryMock.findById(ArgumentMatchers.anyInt()))
                    .thenReturn(Optional.empty());

            Assertions.assertThatExceptionOfType(BadRequestException.class)
                    .isThrownBy(() -> buscaEmprestimoService.buscarEmprestimo(1));
        }


        @Test
        @DisplayName("delete removes Emprestimo when successful")
        void delete_RemovesEmprestimo_WhenSuccessful () {

            Assertions.assertThatCode(() -> emprestimoService.delete(1))
                    .doesNotThrowAnyException();

        }
    }

