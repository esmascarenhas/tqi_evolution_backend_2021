package br.com.tqi.tqi_evolution_avaliacao.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClienteNaoEncontradoException extends Exception{

    public ClienteNaoEncontradoException (Integer id){

        super(String.format("Cliente não encontrado com o ID %s",id));
    }

}
