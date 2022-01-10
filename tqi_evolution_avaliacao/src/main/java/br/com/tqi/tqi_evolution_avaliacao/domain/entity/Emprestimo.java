package br.com.tqi.tqi_evolution_avaliacao.domain.entity;

import br.com.tqi.tqi_evolution_avaliacao.domain.enums.StatusEmprestimo;
import br.com.tqi.tqi_evolution_avaliacao.domain.exception.NegocioException;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
@Builder
@ToString
@AllArgsConstructor
@Entity
@Component
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Emprestimo implements Serializable {

    private static final long serialVersionUID = 7878073364245101002L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    @ApiModelProperty(value = "Código do Emprestimo")
    private Integer codigoEmprestimo;

    @NotEmpty
    @Column(name = "valor_emprestimo")
    @ApiModelProperty(value = "Valor Contratado no Emprestimo")
    private double valorEmprestimo;


    @NotEmpty
    @Column(name = "data_primeira_parcela")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @ApiModelProperty(value = "Data de primeira parcela(yyyy-MM-dd). Não pode ser superior a 60 dias")
    private LocalDate dataPrimeiraParcela;


    @Column(name = "data_finalizacao")
    @ApiModelProperty(value = "Data de finalizção do emprestimo(yyyy-MM-dd).")
    private OffsetDateTime dataFinalizacao;

    @NotEmpty
    @Column(name = "quant_parcelas")
    @Range(max = 60,message = "Desculpa! Só é permitido 60 parcelas.")
    @ApiModelProperty(value = "Quantidades de parcelas do emprestimo")
    private int quantidadeParcelas;

    @Enumerated(EnumType.STRING)
    @ApiModelProperty(value = "Situação do Emprestimo")
    private StatusEmprestimo status;

    @ManyToOne
    @ApiModelProperty(value = "Código do cliente")
    private Cliente cliente;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @ApiModelProperty(value = "Código do usuário")
    @JoinColumn(name = "usuario_id")
    private UserSecurity usuario;


    public void finalizar() {
        if (naoPodeSerFinalizado()) {
			throw new NegocioException("Emprestimo não pode ser finalizada");}

        setStatus(StatusEmprestimo.FINALIZADO);
        setDataFinalizacao(OffsetDateTime.now());
    }
    public void cancelar() {
        if (naoPodeSerFinalizado()) {
            throw new NegocioException("Emprestimo não pode ser cancelado");}

        setStatus(StatusEmprestimo.CANCELADO);
        setDataFinalizacao(OffsetDateTime.now());
    }
    public void suspender() {
        if (naoPodeSerFinalizado()) {
            throw new NegocioException("Emprestimo não pode ser suspenso");}

        setStatus(StatusEmprestimo.SUSPENSO);
        setDataFinalizacao(OffsetDateTime.now());
    }


	public boolean podeSerFinalizado() {
		return StatusEmprestimo.ATIVO.equals(getStatus());
	}

	public boolean naoPodeSerFinalizado() {
		return !podeSerFinalizado();
	}
}
