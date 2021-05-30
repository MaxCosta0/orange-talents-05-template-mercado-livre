package br.com.zupacademy.maxley.mercadolivre.controller.dto;

import br.com.zupacademy.maxley.mercadolivre.model.PerguntaProduto;
import br.com.zupacademy.maxley.mercadolivre.model.Produto;
import br.com.zupacademy.maxley.mercadolivre.model.Usuario;
import io.jsonwebtoken.lang.Assert;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class NovaPerguntaRequest {
    @NotBlank
    private String titulo;

    @Deprecated
    public NovaPerguntaRequest(){}

    public NovaPerguntaRequest(@NotBlank String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo(){
        return titulo;
    }

    public PerguntaProduto toModel(EntityManager manager, @Valid Produto produto, @Valid Usuario usuario) {
        Assert.state(produto != null, "[BUG] Este produto ainda nao esta cadastrado.");
        Assert.state(usuario != null, "[BUG] Este usuario ainda nao esta cadastrado.");

        return new PerguntaProduto(titulo, produto, usuario);
    }
}
