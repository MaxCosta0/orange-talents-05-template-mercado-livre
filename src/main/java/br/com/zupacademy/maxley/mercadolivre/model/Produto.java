package br.com.zupacademy.maxley.mercadolivre.model;

import br.com.zupacademy.maxley.mercadolivre.controller.dto.NovaCaracteristicaRequest;
import io.jsonwebtoken.lang.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private final String nome;
    @Positive @NotNull
    private final BigDecimal valor;
    @Positive @NotNull
    private final Integer quantidadeDisponivel;
    @NotBlank @Size(max = 1000)
    private final String descricao;
    @NotNull @Valid @ManyToOne
    private Categoria categoria;
    @NotNull @Valid @ManyToOne
    private Usuario dono;
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();

    public Produto(@NotBlank String nome, @Positive @NotNull BigDecimal valor,
                   @Positive @NotNull Integer quantidadeDisponivel,
                   @NotBlank @Size(max = 1000) String descricao,
                   @NotNull @Valid Categoria categoria, @NotNull @Valid Usuario dono,
                   @Size(min = 3) @Valid Collection<NovaCaracteristicaRequest> caracteristicas) {

        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.descricao = descricao;
        this.categoria = categoria;
        this.dono = dono;

        Set<CaracteristicaProduto> caracteristicasProduto = new HashSet<>();

        for (NovaCaracteristicaRequest caracteristica : caracteristicas) {
            CaracteristicaProduto caracteristicaProduto = caracteristica.toModel(this);
            caracteristicasProduto.add(caracteristicaProduto);
        }

        this.caracteristicas.addAll(caracteristicasProduto);

        Assert.isTrue(this.caracteristicas.size() >= 3, "[BUG] Esse produto deveria ter 3 ou mais caracteristicas.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;
        Produto produto = (Produto) o;
        return nome.equals(produto.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                ", quantidadeDisponivel=" + quantidadeDisponivel +
                ", descricao='" + descricao + '\'' +
                ", categoria=" + categoria +
                ", dono=" + dono +
                ", caracteristicas=" + caracteristicas +
                '}';
    }
}
