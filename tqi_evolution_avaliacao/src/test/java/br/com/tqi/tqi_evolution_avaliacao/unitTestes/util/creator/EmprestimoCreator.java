package br.com.tqi.tqi_evolution_avaliacao.unitTestes.util.creator;

import br.com.tqi.tqi_evolution_avaliacao.domain.entity.Emprestimo;


public class EmprestimoCreator {

    public static Emprestimo createEmprestimoToBeSaved(){
        return Emprestimo.builder()
                .valorEmprestimo(1000)
                .quantidadeParcelas(5)
                .build();
    }

    public static Emprestimo createValidEmprestimo(){
        return Emprestimo.builder()
                .valorEmprestimo(1000)
                .quantidadeParcelas(5)
                .codigoEmprestimo(1)
                .build();
    }

    public static Emprestimo createValidUpdatedEmprestimo(){
        return Emprestimo.builder()
                .valorEmprestimo(2000)
                .quantidadeParcelas(10)
                .codigoEmprestimo(1)
                .build();
    }
}
