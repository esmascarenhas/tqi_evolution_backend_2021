package br.com.tqi.tqi_evolution_avaliacao.domain.entity;

import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min = 2 , max = 120)
    private String nome;

    @NotNull
    @Size (min = 2 , max = 80)
    @Email
    private String email;

    @NotNull
    @CPF
    @Size (min = 2 , max = 12)
    private String cpf;

    @NotNull
    @Size (min = 2 , max = 14)
    private String rg;

    @Embedded
    private Endereco endereco;

    @NotEmpty
    private double renda;

    @NotNull
    private String senha;

}
