package br.com.tqi.tqi_evolution_avaliacao.api.dto.model.imput;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.Valid;
import java.io.Serializable;
import java.time.LocalDate;


@Getter
@Setter
public class EmprestimoDTOImput implements Serializable {


    private static final long serialVersionUID = -2828780400064566821L;

    @Valid
    private ClienteIdImput clienteid;


    private double valorEmprestimo;

   // @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
   // @JsonDeserialize(using = LocalDateDeserializer.class)
   // @JsonSerialize(using = LocalDateSerializer.class)
   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataPrimeiraParcela;


    private int quantidadeParcelas;





}
