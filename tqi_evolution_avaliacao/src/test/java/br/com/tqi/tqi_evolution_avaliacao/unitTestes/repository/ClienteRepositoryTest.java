package br.com.tqi.tqi_evolution_avaliacao.unitTestes.repository;

import br.com.tqi.tqi_evolution_avaliacao.domain.entity.Cliente;
import br.com.tqi.tqi_evolution_avaliacao.domain.repository.ClienteRepository;
import br.com.tqi.tqi_evolution_avaliacao.unitTestes.util.creator.ClienteCreator;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@Log4j2
@DataJpaTest
@DisplayName("Teste para o repositório de clientes")
class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    @DisplayName("Salva Cliente quando for bem sucedido")
    void save_PersistCliente_QuandoSucesso(){
        Cliente clientToBeSaved = ClienteCreator.criaClienteToBeSaved();

        Cliente clienteSaved = this.clienteRepository.save(clientToBeSaved);

        Assertions.assertThat(clienteSaved).isNotNull();
        Assertions.assertThat(clienteSaved.getId()).isNotNull();
        Assertions.assertThat(clienteSaved.getNome()).isEqualTo(clientToBeSaved.getNome());


    }


   }