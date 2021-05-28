package br.com.zupacademy.maxley.mercadolivre.config.security;

import br.com.zupacademy.maxley.mercadolivre.controller.AutenticacoesController;
import br.com.zupacademy.maxley.mercadolivre.model.Usuario;
import br.com.zupacademy.maxley.mercadolivre.repository.UsuarioRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutenticacaoViaTolkenFilter extends OncePerRequestFilter {

    private ValidaToken validaToken;
    private RecuperaIdToken recuperaIdToken;
    private UsuarioRepository usuarioRepository;

    public AutenticacaoViaTolkenFilter(ValidaToken validaToken, UsuarioRepository usuarioRepository, RecuperaIdToken recuperaIdToken){
        this.validaToken = validaToken;
        this.usuarioRepository = usuarioRepository;
        this.recuperaIdToken = recuperaIdToken;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = recuperarToken(httpServletRequest);
        boolean valido = validaToken.validar(token);

        if(valido){
            autenticarUsuario(token);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void autenticarUsuario(String token) {
        Long idUsuario = recuperaIdToken.recuperar(token);
        Usuario usuario = usuarioRepository.findById(idUsuario).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

    }

    private String recuperarToken(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")){
            return null;
        }
        return token.substring(7, token.length());
    }
}
