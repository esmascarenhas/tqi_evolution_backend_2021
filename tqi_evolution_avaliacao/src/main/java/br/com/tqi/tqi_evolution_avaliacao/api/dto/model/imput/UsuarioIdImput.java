package br.com.tqi.tqi_evolution_avaliacao.api.dto.model.imput;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;


@Getter
@Setter
public class UsuarioIdImput implements Serializable {


    private static final long serialVersionUID = 418988294750909655L;

    @NotEmpty(message = "Código do usuário deve ser informado")
    private Integer id;

}
