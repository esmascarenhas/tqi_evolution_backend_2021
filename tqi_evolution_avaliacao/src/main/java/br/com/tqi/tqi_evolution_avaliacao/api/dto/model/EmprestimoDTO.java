package br.com.tqi.tqi_evolution_avaliacao.api.dto.model;

import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.resumo.ClienteResumoModel;
import br.com.tqi.tqi_evolution_avaliacao.domain.enums.StatusEmprestimo;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.io.Serializable;
import java.time.LocalDate;

@Relation(collectionRelation = "Relação de Clientes")
@Getter
@Setter
public class EmprestimoDTO extends RepresentationModel<EmprestimoDTO> implements Serializable {

    private Integer codigoEmprestimo;
    private double valorEmprestimo;
    private LocalDate dataPrimeiraParcela;
    private int quantidadeParcelas;
    private StatusEmprestimo status;
    private ClienteResumoModel cliente;

}
