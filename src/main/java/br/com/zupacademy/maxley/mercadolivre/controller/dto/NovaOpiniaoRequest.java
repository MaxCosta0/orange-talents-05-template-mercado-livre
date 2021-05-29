package br.com.zupacademy.maxley.mercadolivre.controller.dto;

import br.com.zupacademy.maxley.mercadolivre.model.OpiniaoProduto;
import br.com.zupacademy.maxley.mercadolivre.model.Produto;
import br.com.zupacademy.maxley.mercadolivre.model.Usuario;
import io.jsonwebtoken.lang.Assert;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;

public class NovaOpiniaoRequest {

    @Min(1) @Max(5) @NotNull
    private Integer nota;
    @NotBlank
    private String titulo;
    @NotBlank @Size(max = 500)
    private String descricao;

    public NovaOpiniaoRequest(Integer nota, String titulo, String descricao,
                              Long usuarioId) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "NovaOpiniaoRequest{" +
                "nota=" + nota +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }

    public OpiniaoProduto toModel(EntityManager entityManager, Produto produto, Usuario consumidor) {
        Assert.state(produto != null, "[BUG] Este produto ainda nao esta cadastrado.");
        Assert.state(consumidor != null, "[BUG] Este usuario ainda nao esta cadastrado.");
        return new OpiniaoProduto(nota, titulo, descricao, consumidor, produto);
    }
}
