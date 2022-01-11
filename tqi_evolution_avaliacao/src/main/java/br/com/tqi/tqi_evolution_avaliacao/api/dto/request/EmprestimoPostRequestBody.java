package br.com.tqi.tqi_evolution_avaliacao.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmprestimoPostRequestBody {

    @NotEmpty
    private int quantidadeParcelas;
    @NotEmpty
    private double valorEmprestimo;
}
