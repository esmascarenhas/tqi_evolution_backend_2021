package br.com.tqi.tqi_evolution_avaliacao.security.filter;

import br.com.tqi.tqi_evolution_avaliacao.security.repository.UserRepository;


import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.tqi.tqi_evolution_avaliacao.security.utils.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.tqi.tqi_evolution_avaliacao.security.utils.JwtTokenUtil;



public class TokenAuthenticationFilter extends OncePerRequestFilter {
//faz a interceptação das requisições e temos o
// método doFilterInternal onde podemos manipular vários dados.


    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    private SecurityConstants constants;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        String token = request.getHeader(constants.HEADER_STRING);
        if (token != null && token.startsWith(constants.TOKEN_PREFIX)) {
            token = token.substring(7);
//extrair o token do cabeçalho Authorization e verifica se ele existe e
// se ele é um token do tipo Bearer
        }
        String username = jwtTokenUtil.getUsernameFromToken(token);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            if (jwtTokenUtil.tokenValido(token)) {
//passando o userSecurity, null no parâmetro da senha pois não precisamos dela nesse ponto e a lista de Authorities
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                //objeto que lida com o contexto de segurança da aplicação
            }
        }

        chain.doFilter(request, response);
    }

/*    private void authenticate(String tokenFromHeader) {
        Integer id = tokenService.getTokenId(tokenFromHeader);

        Optional<UserSecurity> optionalUser = repository.findById(id);
//extrair o id que é enviado no token, no Subject e após isso faz uma
//// busca por esse id no repositório.

        if(optionalUser.isPresent()) {

            UserSecurity user = optionalUser.get();
//passando o userSecurity, null no parâmetro da senha pois não precisamos dela nesse ponto e a lista de perfis
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getPerfis());
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            //objeto que lida com o contexto de segurança da aplicação
        }
    }*/

/*    private String getTokenFromHeader(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
//extrair o token do cabeçalho Authorization e verifica se ele existe e
// se ele é um token do tipo Bearer
        }

        return token.substring(7, token.length());
    }*/
}
