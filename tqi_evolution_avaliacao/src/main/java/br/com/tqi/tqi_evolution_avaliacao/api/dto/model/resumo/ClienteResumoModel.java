package br.com.tqi.tqi_evolution_avaliacao.api.dto.model.resumo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ClienteResumoModel {

    private Integer id;
    private String nome;

}
