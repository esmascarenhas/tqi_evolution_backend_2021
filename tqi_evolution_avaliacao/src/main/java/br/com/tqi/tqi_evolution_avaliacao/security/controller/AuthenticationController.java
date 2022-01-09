package br.com.tqi.tqi_evolution_avaliacao.security.controller;


import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.imput.UserDTOImput;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.response.Response;
import br.com.tqi.tqi_evolution_avaliacao.security.model.TokenDTO;


import javax.validation.Valid;


import br.com.tqi.tqi_evolution_avaliacao.security.utils.JwtTokenUtil;

import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api")
@ApiOperation(value = "Sistema de Análise de Crédito - Realiza autenticação do usuário." )
public class AuthenticationController {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil tokenService;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Gera e retorna um novo token JWT.
     *
     * //@param //authenticationDto
     * //@param //result
     * //@return ResponseEntity<Response<TokenDto>>
     * //@throws AuthenticationException
     */
    @PostMapping("/v1/auth")
    @ApiOperation(value = "Gera um Token de acesso")
    public ResponseEntity<Response<TokenDTO>> gerarTokenJwt(@Valid @RequestBody UserDTOImput authenticationDto,
                                                            BindingResult result) throws AuthenticationException {
        Response<TokenDTO> response = new Response<TokenDTO>();

        if (result.hasErrors()) {
            log.error("Erro: {}", result.getAllErrors());
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        log.info("Gerando token para o email {}.", authenticationDto.getEmail());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationDto.getEmail(), authenticationDto.getSenha()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationDto.getEmail());
        String token = tokenService.obterToken(userDetails);
        response.setData(new TokenDTO(token));

        return ResponseEntity.ok(response);
    }



}
