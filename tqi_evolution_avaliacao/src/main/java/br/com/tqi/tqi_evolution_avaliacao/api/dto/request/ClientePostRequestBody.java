package br.com.tqi.tqi_evolution_avaliacao.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientePostRequestBody {

    @NotEmpty
    private String nome;
    @NotEmpty
    @CPF
    private String cpf;
    @NotEmpty
    private String rg;
    @NotEmpty
    private double renda;

}
