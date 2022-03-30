package br.com.tqi.tqi_evolution_avaliacao.api.dto.model.imput;


import lombok.*;

import javax.validation.Valid;
import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@Builder
public class ClienteDTOImput implements Serializable {


    private static final long serialVersionUID = -3497069937363697605L;

    private String nome;

    private String cpf;

    private String rg;

    @Valid
    private EnderecoDTOImput endereco;

    @Valid
    private UserDTOImput usuario;


    private double renda;




}
