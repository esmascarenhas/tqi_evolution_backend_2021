package br.com.tqi.tqi_evolution_avaliacao.api.dto.model.imput;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Getter
@Setter
public class ClienteIdImput implements Serializable {

    private static final long serialVersionUID = -7091837752128156281L;

    @NotEmpty(message = "Código do cliente deve ser informado")
    private Integer id;

}
