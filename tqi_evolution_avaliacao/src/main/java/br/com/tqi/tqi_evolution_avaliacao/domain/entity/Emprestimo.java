package br.com.tqi.tqi_evolution_avaliacao.domain.entity;

import br.com.tqi.tqi_evolution_avaliacao.domain.enums.StatusEmprestimo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Integer codigoEmprestimo;

    @NotEmpty
    @Column(name = "valor_emprestimo")
    private double valorEmprestimo;


    @NotEmpty
    //@JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
   /// @JsonDeserialize(using = LocalDateDeserializer.class)
   // @JsonSerialize(using = LocalDateSerializer.class)
    @Column(name = "data_primeira_parcela")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataPrimeiraParcela;

    @NotEmpty
    @Column(name = "quant_parcelas")
    @Range(max = 60,message = "Desculpa! Só é permitido 60 parcelas.")
    private int quantidadeParcelas;

    @Enumerated(EnumType.STRING)
    private StatusEmprestimo status;

    @ManyToOne
    private Cliente cliente;



}
