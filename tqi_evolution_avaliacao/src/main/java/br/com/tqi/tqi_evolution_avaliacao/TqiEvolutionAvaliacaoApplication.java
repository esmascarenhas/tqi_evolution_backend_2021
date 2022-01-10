package br.com.tqi.tqi_evolution_avaliacao;

import br.com.tqi.tqi_evolution_avaliacao.domain.entity.UserSecurity;
import br.com.tqi.tqi_evolution_avaliacao.domain.enums.RolesUser;
import br.com.tqi.tqi_evolution_avaliacao.security.repository.UserRepository;
import br.com.tqi.tqi_evolution_avaliacao.security.utils.SenhaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


import java.util.Objects;

@Configuration
@ComponentScan("br.com.tqi.tqi_evolution_avaliacao")
@SpringBootApplication
public class TqiEvolutionAvaliacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TqiEvolutionAvaliacaoApplication.class, args);}

		@Autowired
		private UserRepository userRepository;



	@Bean
		public CommandLineRunner commandLineRunner(){
			return args -> {

				UserSecurity usuario = new UserSecurity();
				usuario = userRepository.findByEmail("usuario@email.com");
				if (Objects.isNull(usuario)){
					usuario = new UserSecurity();
				}
				usuario.setEmail("usuario@email.com");
				usuario.setRoles(RolesUser.ROLE_USER);
				usuario.setSenha(SenhaUtils.gerarBCrypt("123456"));
				userRepository.save(usuario);

				usuario = userRepository.findByEmail("admin@email.com");
				if (Objects.isNull(usuario)){
					usuario = new UserSecurity();
				}
				usuario.setEmail("admin@email.com");
				usuario.setRoles(RolesUser.ROLE_ADMIN);
				usuario.setSenha(SenhaUtils.gerarBCrypt("654321"));
				userRepository.save(usuario);


			};
		}


	}


