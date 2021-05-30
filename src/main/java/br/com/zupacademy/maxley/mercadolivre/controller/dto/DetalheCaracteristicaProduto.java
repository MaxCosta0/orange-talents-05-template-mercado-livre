package br.com.zupacademy.maxley.mercadolivre.controller.dto;

import br.com.zupacademy.maxley.mercadolivre.model.CaracteristicaProduto;

public class DetalheCaracteristicaProduto {
    private String nome;
    private String descricao;

    public DetalheCaracteristicaProduto(CaracteristicaProduto caracteristica) {
        this.nome = caracteristica.getNome();
        this.descricao = caracteristica.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
