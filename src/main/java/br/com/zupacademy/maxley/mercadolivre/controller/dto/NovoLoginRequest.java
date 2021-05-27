package br.com.zupacademy.maxley.mercadolivre.controller.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class NovoLoginRequest {

    private String login;
    private String senha;

    @Deprecated
    public NovoLoginRequest(){}

    public NovoLoginRequest(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public UsernamePasswordAuthenticationToken converte() {
        return new UsernamePasswordAuthenticationToken(login, senha);
    }
}
