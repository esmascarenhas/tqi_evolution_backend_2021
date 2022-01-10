package br.com.tqi.tqi_evolution_avaliacao.security.repository;

import br.com.tqi.tqi_evolution_avaliacao.domain.entity.UserSecurity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;



@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<UserSecurity,Integer> {

   UserSecurity findByEmail(String email);
}
