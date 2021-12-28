package br.com.tqi.tqi_evolution_avaliacao.api.dto.model.imput;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Getter
@Setter
public class ClienteIdImput {

    @NotEmpty(message = "Código do cliente deve ser informado")
    private Integer id;

}
