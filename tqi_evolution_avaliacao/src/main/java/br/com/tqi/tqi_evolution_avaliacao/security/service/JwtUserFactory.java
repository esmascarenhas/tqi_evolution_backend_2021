package br.com.tqi.tqi_evolution_avaliacao.security.service;


import java.util.ArrayList;
import java.util.List;

import br.com.tqi.tqi_evolution_avaliacao.domain.entity.UserSecurity;
import br.com.tqi.tqi_evolution_avaliacao.domain.enums.RolesUser;
import br.com.tqi.tqi_evolution_avaliacao.security.model.JwtUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


public class JwtUserFactory {


    private JwtUserFactory() {
    }

/*    *//**
     * Converte e gera um JwtUser com base nos dados de um funcionário.
     *
     * //@param //funcionario
     * @return JwtUser
     *
     */
    public static JwtUser create(UserSecurity usuario) {
        return new JwtUser(usuario.getId(), usuario.getEmail(), usuario.getSenha(),
                mapToGrantedAuthorities(usuario.getRoles()));
    }

    /**
     * Converte o perfil do usuário para o formato utilizado pelo Spring Security.
     *
     * //@param //rolesUser
     * @return List<GrantedAuthority>
     */
    private static List<GrantedAuthority> mapToGrantedAuthorities(RolesUser rolesUser) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(rolesUser.toString()));
        return authorities;
    }

}
