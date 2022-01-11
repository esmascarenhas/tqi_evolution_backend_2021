package br.com.tqi.tqi_evolution_avaliacao.api.dto.model;

import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.resumo.ClienteDetalhaEmprestimo;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.resumo.UsuarioDetalhaEmprestimo;
import br.com.tqi.tqi_evolution_avaliacao.domain.enums.StatusEmprestimo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Relation(collectionRelation = "Detalhes do Emprestimo")
public class DetalheEmprestimo extends RepresentationModel<EmprestimoResumoDTO> implements Serializable {

    private Integer codigoEmprestimo;
    private double valorEmprestimo;
    private LocalDate dataPrimeiraParcela;
    private int quantidadeParcelas;
    private StatusEmprestimo status;
    private ClienteDetalhaEmprestimo cliente;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private UsuarioDetalhaEmprestimo usuario;
}
