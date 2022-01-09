package br.com.tqi.tqi_evolution_avaliacao.api.dto.model;

import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.imput.UserDTOImput;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.resumo.UserDTOResumo;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO extends RepresentationModel<ClienteDTO> {


    private Integer id;
    private String nome;
    private UserDTOResumo usuario;
    private String cpf;
    private String rg;
    private EnderecoDTO endereco;
    private double renda;


}
