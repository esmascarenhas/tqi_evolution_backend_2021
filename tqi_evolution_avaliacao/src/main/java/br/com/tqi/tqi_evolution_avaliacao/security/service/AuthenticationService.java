package br.com.tqi.tqi_evolution_avaliacao.security.service;

import br.com.tqi.tqi_evolution_avaliacao.domain.entity.UserSecurity;
import br.com.tqi.tqi_evolution_avaliacao.security.repository.UserRepository;


import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserSecurity> usuario = usuarioService.buscarPorEmail(username);

        if (usuario.isPresent()) {
            return JwtUserFactory.create(usuario.get());
        }
// busca no repositório pelo usuário e verificar se ele existe e devolver para o processo de autenticação
        throw new UsernameNotFoundException("Email não encontrado.");
    }
}
