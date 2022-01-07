package br.com.tqi.tqi_evolution_avaliacao.util;

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
}

//    public static Anime createAnimeToBeSaved(){
//        return Anime.builder()
//                .name("Hajime no Ippo")
//                .build();
//    }
//
//    public static Anime createValidAnime(){
//        return Anime.builder()
//                .name("Hajime no Ippo")
//                .id(1L)
//                .build();
//    }
//
//    public static Anime createValidUpdatedAnime(){
//        return Anime.builder()
//                .name("Hajime no Ippo 2")
//                .id(1L)
//                .build();
//    }
//