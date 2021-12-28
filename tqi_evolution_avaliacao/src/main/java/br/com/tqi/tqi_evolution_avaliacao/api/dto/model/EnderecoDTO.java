package br.com.tqi.tqi_evolution_avaliacao.api.dto.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class EnderecoDTO {


    private Integer id;
    private String logradouro;
    private int numero;
    private String complemento;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;
    private String pais;

}
