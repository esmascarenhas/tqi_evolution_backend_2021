package br.com.tqi.tqi_evolution_avaliacao.unitTestes.util.post;

import br.com.tqi.tqi_evolution_avaliacao.api.dto.request.ClientePostRequestBody;
import br.com.tqi.tqi_evolution_avaliacao.unitTestes.util.creator.ClienteCreator;

public class ClientePostRequestBodyCreator {
    public static ClientePostRequestBody createClientePostRequestBody(){
        return ClientePostRequestBody.builder()
                .nome(ClienteCreator.criaClienteToBeSaved().getNome())
                .rg(ClienteCreator.criaClienteToBeSaved().getRg())
                .cpf(ClienteCreator.criaClienteToBeSaved().getCpf())
                .renda(ClienteCreator.criaClienteToBeSaved().getRenda())
                .build();
    }
}
