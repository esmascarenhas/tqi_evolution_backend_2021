package br.com.tqi.tqi_evolution_avaliacao.api.dto.model.imput;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Getter
@Setter
public class EmprestimoDTOImput {

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
