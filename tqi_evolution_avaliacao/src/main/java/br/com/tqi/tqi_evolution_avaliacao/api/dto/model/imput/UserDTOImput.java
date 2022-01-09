package br.com.tqi.tqi_evolution_avaliacao.api.dto.model.imput;


import br.com.tqi.tqi_evolution_avaliacao.domain.enums.RolesUser;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Builder
@AllArgsConstructor
public class UserDTOImput  {



    private String email;
    private String senha;





    public UserDTOImput() {
    }

    @NotEmpty(message = "Email não pode ser vazio.")
    @Email(message = "Email inválido.")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotEmpty(message = "Senha não pode ser vazia.")
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "UserRequestDTO [email=" + email + ", senha=" + senha + "]";
    }


}
