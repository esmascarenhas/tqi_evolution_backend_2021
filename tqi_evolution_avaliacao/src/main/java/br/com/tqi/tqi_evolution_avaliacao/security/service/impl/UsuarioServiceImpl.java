package br.com.tqi.tqi_evolution_avaliacao.security.service.impl;

import br.com.tqi.tqi_evolution_avaliacao.domain.entity.UserSecurity;
import br.com.tqi.tqi_evolution_avaliacao.security.repository.UserRepository;
import br.com.tqi.tqi_evolution_avaliacao.security.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UserRepository repository;

    @Override
    public Optional<UserSecurity> buscarPorEmail(String email) {
        return Optional.ofNullable(this.repository.findByEmail(email));
    }
}
