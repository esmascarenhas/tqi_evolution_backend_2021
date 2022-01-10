package br.com.tqi.tqi_evolution_avaliacao.domain.repository;

import br.com.tqi.tqi_evolution_avaliacao.domain.entity.Cliente;
import br.com.tqi.tqi_evolution_avaliacao.util.ClienteCreator;
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


    //
    //    @Test
    //    @DisplayName("Save updates anime when Successful")
    //    void save_UpdatesAnime_WhenSuccessful(){
    //        Anime animeToBeSaved = AnimeCreator.createAnimeToBeSaved();
    //
    //        Anime animeSaved = this.animeRepository.save(animeToBeSaved);
    //
    //        animeSaved.setName("Overlord");
    //
    //        Anime animeUpdated = this.animeRepository.save(animeSaved);
    //
    //        Assertions.assertThat(animeUpdated).isNotNull();
    //
    //        Assertions.assertThat(animeUpdated.getId()).isNotNull();
    //
    //        Assertions.assertThat(animeUpdated.getName()).isEqualTo(animeSaved.getName());
    //    }
    //
    //    @Test
    //    @DisplayName("Delete removes anime when Successful")
    //    void delete_RemovesAnime_WhenSuccessful(){
    //        Anime animeToBeSaved = AnimeCreator.createAnimeToBeSaved();
    //
    //        Anime animeSaved = this.animeRepository.save(animeToBeSaved);
    //
    //        this.animeRepository.delete(animeSaved);
    //
    //        Optional<Anime> animeOptional = this.animeRepository.findById(animeSaved.getId());
    //
    //        Assertions.assertThat(animeOptional).isEmpty();
    //
    //    }
    //
    //    @Test
    //    @DisplayName("Find By Name returns list of anime when Successful")
    //    void findByName_ReturnsListOfAnime_WhenSuccessful(){
    //        Anime animeToBeSaved = AnimeCreator.createAnimeToBeSaved();
    //
    //        Anime animeSaved = this.animeRepository.save(animeToBeSaved);
    //
    //        String name = animeSaved.getName();
    //
    //        List<Anime> animes = this.animeRepository.findByName(name);
    //
    //        Assertions.assertThat(animes)
    //                .isNotEmpty()
    //                .contains(animeSaved);
    //
    //    }
    //
    //    @Test
    //    @DisplayName("Find By Name returns empty list when no anime is found")
    //    void findByName_ReturnsEmptyList_WhenAnimeIsNotFound(){
    //        List<Anime> animes = this.animeRepository.findByName("xaxa");
    //
    //        Assertions.assertThat(animes).isEmpty();
    //    }
    //
    //    @Test
    //    @DisplayName("Save throw ConstraintViolationException when name is empty")
    //    void save_ThrowsConstraintViolationException_WhenNameIsEmpty(){
    //        Anime anime = new Anime();
    ////        Assertions.assertThatThrownBy(() -> this.animeRepository.save(anime))
    ////                .isInstanceOf(ConstraintViolationException.class);
    //
    //        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
    //                .isThrownBy(() -> this.animeRepository.save(anime))
    //                .withMessageContaining("The anime name cannot be empty");
    //    }



}