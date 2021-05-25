package br.com.zupacademy.maxley.mercadolivre.controller.dto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class SenhaLimpa {

    private String senha;

    public SenhaLimpa(@NotBlank @Min(6) String senha){
        Assert.hasLength(senha, "Senha nao pode estar em branco.");
        Assert.isTrue(senha.length() >= 6, "Senha tem que ter no minimo 6 caracteres.");
        this.senha = senha;
    }

    public String hash(){
        return new BCryptPasswordEncoder().encode(senha);
    }
}
