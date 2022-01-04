package br.com.tqi.tqi_evolution_avaliacao.api.dto.model;

import br.com.tqi.tqi_evolution_avaliacao.domain.enums.StatusEmprestimo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.OffsetDateTime;
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
