package br.com.tqi.tqi_evolution_avaliacao.domain.repository;

import br.com.tqi.tqi_evolution_avaliacao.domain.entity.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo,Integer> {

/*    Emprestimo findByClienteid(Integer clienteid);*/
}
