package br.com.zupacademy.maxley.mercadolivre.controller.dto;

import br.com.zupacademy.maxley.mercadolivre.model.Usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class NovoUsuarioRequest {

    @NotBlank @Email
    private String login;
    @NotBlank @Min(6)
    private String senha;

    @Deprecated
    public NovoUsuarioRequest(){}

    public NovoUsuarioRequest(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    /*
    * Os getters estao aqui por que os campos de login e senha
    * são recebidos como nulo pelo jackson. Acredito que seja
    * um conflito de bind.
    * */
    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public Usuario toModel() {
        return new Usuario(this.login, new SenhaLimpa(this.senha));
    }
}
