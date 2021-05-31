package br.com.zupacademy.maxley.mercadolivre.controller.dto;

import br.com.zupacademy.maxley.mercadolivre.config.annotation.ExisteId;
import br.com.zupacademy.maxley.mercadolivre.model.Produto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class NovaCompraRequest {

    @NotNull @Positive
    private Integer quantidade;
    @NotNull @ExisteId(domainClass = Produto.class, fieldName = "id")
    private Long produtoId;
    @NotNull
    private GatewayPagamento formaPagamento;

    public NovaCompraRequest(@NotNull @Positive Integer  quantidade,
                             @NotNull Long produtoId,
                             @NotNull GatewayPagamento formaPagamento) {
        this.quantidade = quantidade;
        this.produtoId = produtoId;
        this.formaPagamento = formaPagamento;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public GatewayPagamento getFormaPagamento() {
        return formaPagamento;
    }
}
