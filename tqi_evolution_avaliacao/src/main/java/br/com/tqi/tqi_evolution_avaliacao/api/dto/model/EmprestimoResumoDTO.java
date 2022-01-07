package br.com.tqi.tqi_evolution_avaliacao.api.dto.model;

import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.resumo.ClienteEmprestimoModel;
import br.com.tqi.tqi_evolution_avaliacao.domain.enums.StatusEmprestimo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.io.Serializable;

@Relation(collectionRelation = "Relação de Clientes")
@Getter
@Setter
public class EmprestimoResumoDTO extends RepresentationModel<EmprestimoResumoDTO> implements Serializable {

    private Integer codigoEmprestimo;
    private double valorEmprestimo;
    private int quantidadeParcelas;
    private StatusEmprestimo status;
    private ClienteEmprestimoModel cliente;

}
