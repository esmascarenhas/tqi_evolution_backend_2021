package br.com.tqi.tqi_evolution_avaliacao.security.config;


import br.com.tqi.tqi_evolution_avaliacao.domain.enums.RolesUser;
import br.com.tqi.tqi_evolution_avaliacao.security.autentication.JwtAuthenticationEntryPoint;
import br.com.tqi.tqi_evolution_avaliacao.security.filter.TokenAuthenticationFilter;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@Log4j2
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
@SuppressWarnings("java:S5344")
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;


    @Bean
    public TokenAuthenticationFilter authenticationTokenFilterBean() throws Exception {
        return new TokenAuthenticationFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }


    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    //Configura a Autenticação


    @Override
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    /*    PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        log.info("Password encoded {}", passwordEncoder.encode("Shalom@12"));
        auth.inMemoryAuthentication()
                .withUser("Admin")
                .password(passwordEncoder.encode("@654321"))
                .roles("ROLES_USER", "ROLES_ADMIN")
                .and()
                .withUser("Usuario")
                .password(passwordEncoder.encode("@123456"))
                .roles("ROLES_USER");*/

        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }//para validação dos dados de usuário irá chamar a classe userDetailsService




    //Autorização
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //.cors().and()
                .csrf().disable()
//                csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .exceptionHandling()
                    .authenticationEntryPoint(unauthorizedHandler)
                    .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and().authorizeRequests()
                .antMatchers("/v2/api-docs",  "/**/v2/api-docs/**","/swagger-resources/**", "/**/swagger-resources/**","/swagger-ui.html","/**/webjars/**", "/webjars/springfox-swagger-ui/**", "/v1/swagger.json","/api/v1/auth/**","/api/v1/status/**").permitAll()
                .and().formLogin()
                    .permitAll();

        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
        http.headers().cacheControl();


    }
}
