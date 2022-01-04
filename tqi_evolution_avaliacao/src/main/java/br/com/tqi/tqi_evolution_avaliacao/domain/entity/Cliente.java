package br.com.tqi.tqi_evolution_avaliacao.domain.entity;

import br.com.tqi.tqi_evolution_avaliacao.domain.enums.StatusEmprestimo;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Data
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
    @CPF
    @Size (min = 2 , max = 12)
    private String cpf;

    @NotNull
    @Size (min = 2 , max = 14)
    private String rg;

    @Embedded
    private Endereco endereco;

    @OneToOne
    private UserSecurity usuario;

    @NotEmpty
    private double renda;



    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Emprestimo> emprestimoList = new ArrayList<>();

    public Emprestimo adicionaEmprestimo (Integer clienteid){
        Emprestimo emprestimo =new Emprestimo();
        emprestimo.getCodigoEmprestimo();
        emprestimo.getCliente().getNome();
        emprestimo.getValorEmprestimo();
        emprestimo.getQuantidadeParcelas();
        emprestimo.setStatus(StatusEmprestimo.ATIVO);
        return emprestimo;
    }


}
