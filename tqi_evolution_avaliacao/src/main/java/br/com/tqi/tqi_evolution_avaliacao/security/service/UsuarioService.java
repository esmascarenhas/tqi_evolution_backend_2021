package br.com.tqi.tqi_evolution_avaliacao.security.service;

import br.com.tqi.tqi_evolution_avaliacao.domain.entity.UserSecurity;

import java.util.Optional;

public interface UsuarioService {

    /**
     * Busca e retorna um usuário dado um email.
     *
     * //@param //email
     * @return Optional<Usuario>
     */
    Optional<UserSecurity> buscarPorEmail(String email);
}
