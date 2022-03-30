package br.com.tqi.tqi_evolution_avaliacao.unitTestes.util.creator;

import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.imput.ClienteDTOImput;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.imput.EnderecoDTOImput;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.imput.UserDTOImput;
import lombok.Builder;

import javax.validation.Valid;

@Builder
public class ClienteDTOImputBuilder {

    @Builder.Default
    private String nome = "Emerson";
    @Builder.Default
    private String cpf = "928373795-49";
    @Builder.Default
    private String rg = "05207460-90";

    @Builder.Default
    private EnderecoDTOImput endereco = new EnderecoDTOImput( "Rua Anisio Fraga",54,"1005-B",
            "Barra","41150-000","Salvador","Bahia","Brasil");

    @Builder.Default
    private UserDTOImput usuario = new UserDTOImput("esmascarenhas@email.com","Aleluia@12");

    @Builder.Default
    private double renda = 2.500;

    public ClienteDTOImput toClienteDTOImput(){
        return new ClienteDTOImput(nome,cpf,rg,
                endereco,usuario,renda);
    }
}
