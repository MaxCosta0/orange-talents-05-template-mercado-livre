package br.com.zupacademy.maxley.mercadolivre.controller;

import br.com.zupacademy.maxley.mercadolivre.config.security.GeradorToken;
import br.com.zupacademy.maxley.mercadolivre.controller.dto.NovoLoginRequest;
import br.com.zupacademy.maxley.mercadolivre.controller.dto.NovoUsuarioRequest;
import br.com.zupacademy.maxley.mercadolivre.controller.dto.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacoesController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private GeradorToken geradorToken;

    @PostMapping
    public ResponseEntity<TokenResponse> autenticar(@RequestBody @Valid NovoLoginRequest novoLoginRequest){
        UsernamePasswordAuthenticationToken dadosLogin = novoLoginRequest.converte();

        try{
            Authentication authentication = authenticationManager.authenticate(dadosLogin);
            String token = geradorToken.geraToken(authentication);
            //System.out.println(token);
            return ResponseEntity.ok(new TokenResponse(token, "Bearer"));
        }catch (AuthenticationException ex){
            System.out.println(ex.getMessage());
            return ResponseEntity.badRequest().build();
        }

    }
}
