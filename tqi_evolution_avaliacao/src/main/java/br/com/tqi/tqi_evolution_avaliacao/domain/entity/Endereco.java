package br.com.tqi.tqi_evolution_avaliacao.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {


    @NotNull
    @Size(min = 2 , max = 255)
    @Column(name = "endereco_logradouro")
    private String logradouro;

    @NotNull
    @Column(name = "endereco_numero")
    private int numero;

    @Size(min = 2 , max = 255)
    @Column(name = "endereco_complemento")
    private String complemento;

    @NotNull
    @Size (min = 2 , max = 80)
    @Column(name = "endereco_bairro")
    private String bairro;

    @NotNull
    @Size (min = 2 , max = 11)
    @Column(name = "endereco_cep")
    private String cep;

    @NotNull
    @Size (min = 2 , max = 80)
    @Column(name = "endereco_cidade")
    private String cidade;

    @NotNull
    @Size (min = 2 , max = 80)
    @Column(name = "endereco_estado")
    private String estado;

    @NotNull
    @Size (min = 2 , max = 80)
    @Column(name = "endereco_pais")
    private String pais;

}
