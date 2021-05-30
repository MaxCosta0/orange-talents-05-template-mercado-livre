package br.com.zupacademy.maxley.mercadolivre.controller.dto;

import br.com.zupacademy.maxley.mercadolivre.model.OpiniaoProduto;

public class DetalheOpiniaoProduto {
    private String titulo;
    private String descricao;
    private Integer nota;

    public DetalheOpiniaoProduto(OpiniaoProduto opiniaoProduto) {
        this.titulo = opiniaoProduto.getTitulo();
        this.descricao = opiniaoProduto.getDescricao();
        this.nota = opiniaoProduto.getNota();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getNota() {
        return nota;
    }
}
