package br.com.tqi.tqi_evolution_avaliacao.api.dto.model;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;


@Getter
@Setter
public class ClienteDTO extends RepresentationModel<ClienteDTO> {


    private Integer id;
    private String nome;
    private UserDTO usuario;
    private String cpf;
    private String rg;
    private EnderecoDTO endereco;
    private double renda;


}
