package br.com.tqi.tqi_evolution_avaliacao.api.dto.model.imput;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
public class EnderecoDTOImput implements Serializable {


    private static final long serialVersionUID = -4217120013049575331L;

    private String logradouro;


    private int numero;



    private String complemento;


    private String bairro;


    private String cep;


    private String cidade;


    private String estado;


    private String pais;

}
