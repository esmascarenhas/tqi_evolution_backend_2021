package br.com.tqi.tqi_evolution_avaliacao.api.dto.model.resumo;


import br.com.tqi.tqi_evolution_avaliacao.domain.enums.RolesUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTOResumo {


    private Integer id;
    private String email;
    private String senha;
    private RolesUser roles;




}
