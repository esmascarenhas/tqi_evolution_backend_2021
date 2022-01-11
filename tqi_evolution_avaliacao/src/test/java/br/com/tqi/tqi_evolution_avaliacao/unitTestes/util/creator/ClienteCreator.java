package br.com.tqi.tqi_evolution_avaliacao.unitTestes.util.creator;

import br.com.tqi.tqi_evolution_avaliacao.domain.entity.Cliente;

public class ClienteCreator {

    public static Cliente criaClienteToBeSaved(){
        return Cliente.builder()
                .nome("Emerson")
                .cpf("928373795-49")
                .renda(5000)
                .rg("05207460-90")
                .build();
    }

    public static Cliente validaCliente(){
        return Cliente.builder()
                .nome("Emerson")
                .cpf("928373795-49")
                .renda(5000)
                .rg("05207460-90")
                .build();
    }
    public static Cliente validaUpdateCliente(){
        return Cliente.builder()
                .nome("Tiago")
                .cpf("928373795-49")
                .renda(5000)
                .rg("05207460-90")
                .build();
    }


}

