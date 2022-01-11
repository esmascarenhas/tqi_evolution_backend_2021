package br.com.tqi.tqi_evolution_avaliacao.api.dto.model.imput;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioIdImput implements Serializable {


    private static final long serialVersionUID = 418988294750909655L;

    @NotEmpty(message = "Código do usuário deve ser informado")
    private Integer id;

}
