package br.com.tqi.tqi_evolution_avaliacao.unitTestes.util.creator;

import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.ClienteDTO;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.EnderecoDTO;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.resumo.UserDTOResumo;
import br.com.tqi.tqi_evolution_avaliacao.domain.enums.RolesUser;
import lombok.Builder;

@Builder
public class ClienteBuilder {

    @Builder.Default
    private Integer id = 1;
    @Builder.Default
    private String nome = "Emerson";
    @Builder.Default
    private UserDTOResumo usuario = new UserDTOResumo(1,"esmascarenhas@email.com","Aleluia@12", RolesUser.ROLE_USER);
    @Builder.Default
    private String cpf = "928373795-49";
    @Builder.Default
    private String rg = "05207460-90";
    @Builder.Default
    private EnderecoDTO endereco = new EnderecoDTO(
            "Rua Anisio Fraga",54,"1005-B",
            "Barra","41150-000","Salvador","Bahia","Brasil");

    @Builder.Default
    private double renda = 2.500;

    public ClienteDTO toClienteDTO(){
        return new ClienteDTO(id,
                nome,
                usuario,
                cpf,
                rg,
                endereco,
                renda);
    }
}
