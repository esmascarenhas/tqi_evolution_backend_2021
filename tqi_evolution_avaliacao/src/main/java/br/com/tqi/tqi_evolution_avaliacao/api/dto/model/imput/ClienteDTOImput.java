package br.com.tqi.tqi_evolution_avaliacao.api.dto.model.imput;

import lombok.*;
import org.hibernate.validator.constraints.br.CPF;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ClienteDTOImput {



    private String nome;

    private String email;

    private String cpf;

    private String rg;

    @Valid
    private EnderecoDTOImput endereco;


    private double renda;


    private String senha;

}
