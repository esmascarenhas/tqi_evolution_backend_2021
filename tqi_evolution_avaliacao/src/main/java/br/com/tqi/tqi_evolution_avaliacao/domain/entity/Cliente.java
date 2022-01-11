package br.com.tqi.tqi_evolution_avaliacao.domain.entity;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Data
@ToString
@Component
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1553118749462320630L;

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Código do cliente")
    private Integer id;

    @NotNull
    @Size(min = 2 , max = 120)
    @ApiModelProperty(value = "Nome Completo do Cliente")
    private String nome;


    @NotNull
    @CPF
    @Size (min = 2 , max = 12)
    @ApiModelProperty(value = "CPF do cliente, conforme o documento")
    private String cpf;

    @NotNull
    @Size (min = 2 , max = 14)
    @ApiModelProperty(value = "RG do cliente, conforme o documento")
    private String rg;

    @Embedded
    private Endereco endereco;

    @OneToOne(cascade=CascadeType.PERSIST)
    private UserSecurity usuario;

    @NotEmpty
    @ApiModelProperty(value = "Renda Bruta do cliente")
    private double renda;



    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Emprestimo> emprestimoList = new ArrayList<>();



}
