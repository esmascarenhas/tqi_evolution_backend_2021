package br.com.tqi.tqi_evolution_avaliacao.unitTestes.util.put;

import br.com.tqi.tqi_evolution_avaliacao.api.dto.request.EmprestimoPutRequestBody;
import br.com.tqi.tqi_evolution_avaliacao.unitTestes.util.creator.EmprestimoCreator;

public class EmprestimoPutRequestBodyCreator {

    public static EmprestimoPutRequestBody createEmprestimoPutRequestBody(){
        return EmprestimoPutRequestBody.builder()
                .id(EmprestimoCreator.createValidUpdatedEmprestimo().getCodigoEmprestimo())
                .valorEmprestimo(EmprestimoCreator.createValidUpdatedEmprestimo().getValorEmprestimo())
                .quantidadeParcelas(EmprestimoCreator.createValidUpdatedEmprestimo().getQuantidadeParcelas())
                .build();
    }
}
