package br.com.zupacademy.maxley.mercadolivre.model;

import br.com.zupacademy.maxley.mercadolivre.controller.dto.GatewayPagamento;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Positive
    private Integer quantidade;
    @NotNull @Valid
    @ManyToOne
    private Produto produto;
    @NotNull @Valid
    @ManyToOne
    private Usuario comprador;
    @NotNull @Enumerated
    private GatewayPagamento formaPagamento;

    public Compra(@NotNull @Valid Produto produtoASerComprado,
                  @NotNull @Positive Integer quantidade,
                  @NotNull @Valid Usuario comprador,
                  @NotNull GatewayPagamento formaPagamento) {

        this.quantidade = quantidade;
        this.produto = produtoASerComprado;
        this.comprador = comprador;
        this.formaPagamento = formaPagamento;
    }

    public Long getId() {
        return id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public GatewayPagamento getFormaPagamento() {
        return formaPagamento;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", quantidade=" + quantidade +
                ", produto=" + produto +
                ", comprador=" + comprador +
                '}';
    }
}
