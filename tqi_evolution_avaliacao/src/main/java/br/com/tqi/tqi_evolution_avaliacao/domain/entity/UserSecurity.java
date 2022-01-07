package br.com.tqi.tqi_evolution_avaliacao.domain.entity;

import br.com.tqi.tqi_evolution_avaliacao.domain.enums.RolesUser;

import lombok.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario")
public class UserSecurity  implements Serializable {


    private static final long serialVersionUID = 4804914140206993767L;

    private Integer id;
    private String email;
    private String senha;
    private RolesUser roles;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "senha", nullable = false)
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "roles", nullable = false)
    public RolesUser getRoles() {
        return roles;
    }

    public void setRoles(RolesUser roles) {
        this.roles = roles;
    }

}
