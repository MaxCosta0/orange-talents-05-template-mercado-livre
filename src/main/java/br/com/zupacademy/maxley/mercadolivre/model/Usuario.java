package br.com.zupacademy.maxley.mercadolivre.model;

import br.com.zupacademy.maxley.mercadolivre.controller.dto.SenhaLimpa;
import org.apache.tomcat.jni.Local;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private LocalDateTime instanteCadastro;
    @NotBlank
    @Email
    private String login;
    @NotBlank
    private String senha;

    @Deprecated
    public Usuario(){}

    public Usuario(String login, SenhaLimpa senhaLimpa) {
        this.login = login;
        this.senha = senhaLimpa.hash();
        this.instanteCadastro = LocalDateTime.now();
    }
}
