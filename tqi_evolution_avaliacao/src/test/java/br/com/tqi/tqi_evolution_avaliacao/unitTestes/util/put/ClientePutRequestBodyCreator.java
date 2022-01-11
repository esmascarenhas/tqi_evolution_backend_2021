package br.com.tqi.tqi_evolution_avaliacao.unitTestes.util.put;

import br.com.tqi.tqi_evolution_avaliacao.api.dto.request.ClientePutRequestBody;
import br.com.tqi.tqi_evolution_avaliacao.unitTestes.util.creator.ClienteCreator;

public class ClientePutRequestBodyCreator {

    public static ClientePutRequestBody createClientePutRequestBody(){
        return ClientePutRequestBody.builder()
                .id(ClienteCreator.validaUpdateCliente().getId())
                .nome(ClienteCreator.validaUpdateCliente().getNome())
                .cpf(ClienteCreator.validaUpdateCliente().getCpf())
                .rg(ClienteCreator.validaUpdateCliente().getRg())
                .build();
    }
}
