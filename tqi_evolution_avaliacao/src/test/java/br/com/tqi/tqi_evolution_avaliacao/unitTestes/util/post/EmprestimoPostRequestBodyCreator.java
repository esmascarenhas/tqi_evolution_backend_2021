package br.com.tqi.tqi_evolution_avaliacao.unitTestes.util.post;

import br.com.tqi.tqi_evolution_avaliacao.api.dto.request.EmprestimoPostRequestBody;
import br.com.tqi.tqi_evolution_avaliacao.unitTestes.util.creator.EmprestimoCreator;

public class EmprestimoPostRequestBodyCreator {
    public static EmprestimoPostRequestBody createEmprestimoPutRequestBody(){
        return EmprestimoPostRequestBody.builder()
                .valorEmprestimo(EmprestimoCreator.createEmprestimoToBeSaved().getValorEmprestimo())
                .quantidadeParcelas(EmprestimoCreator.createEmprestimoToBeSaved().getQuantidadeParcelas())
                .build();
    }
}
