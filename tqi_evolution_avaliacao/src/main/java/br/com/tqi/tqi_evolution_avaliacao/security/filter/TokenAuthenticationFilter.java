package br.com.tqi.tqi_evolution_avaliacao.security.filter;

import br.com.tqi.tqi_evolution_avaliacao.security.repository.UserRepository;


import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.tqi.tqi_evolution_avaliacao.security.service.AuthenticationService;
import br.com.tqi.tqi_evolution_avaliacao.security.utils.SecurityConstants;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    private static final Logger logger = LoggerFactory.getLogger(TokenAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        final HttpServletRequest httpRequest = (HttpServletRequest) request;

        final HttpServletResponse httpResponse = (HttpServletResponse) response;

        String token = request.getHeader(constants.HEADER_STRING);
        if (token == null) {
            token = httpRequest.getParameter("token");
        }
        if (httpRequest.getRequestURI().contains("/api/v1/auth") ||
                httpRequest.getRequestURI().contains("v2/api-docs") ||
                httpRequest.getRequestURI().contains("/public/") ||
                httpRequest.getRequestURI().contains("/api/v1/status") ||
                httpRequest.getRequestURI().contains("/swagger-ui/") ||
                httpRequest.getRequestURI().contains("swagger") ||
                httpRequest.getRequestURI().contains("/favicon.ico") ||
                httpRequest.getMethod().equalsIgnoreCase("OPTIONS")
        ) {
            chain.doFilter(request, response);
            return;
        }
        if (token == null) {
            httpResponse.sendError(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        if (token.startsWith(constants.TOKEN_PREFIX)) {
            token = token.replace(constants.TOKEN_PREFIX, Strings.EMPTY);
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




}
