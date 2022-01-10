package br.com.tqi.tqi_evolution_avaliacao.domain.repository;

import br.com.tqi.tqi_evolution_avaliacao.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Integer> {
}
