package br.com.tqi.tqi_evolution_avaliacao.api.dto.model;

import lombok.*;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
public class ClienteDTO extends RepresentationModel<ClienteDTO> {


    private Integer id;
    private String nome;
    private String email;
    private String cpf;
    private String rg;
    private EnderecoDTO endereco;
    private double renda;
    private String senha;

}
