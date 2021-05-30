package br.com.zupacademy.maxley.mercadolivre.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Objects;

@Entity
public class OpiniaoProduto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Min(1) @Max(5) @NotNull
    private Integer nota;
    @NotBlank
    private String titulo;
    @NotBlank @Size
    private String descricao;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Produto produto;

    @Deprecated
    public OpiniaoProduto(){}

    public OpiniaoProduto(@Min(1) @Max(5) @NotNull Integer nota, @NotBlank String titulo,
                          @NotBlank @Size(max = 500) String descricao, Usuario usuario,
                          Produto produto) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuario = usuario;
        this.produto = produto;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OpiniaoProduto)) return false;
        OpiniaoProduto that = (OpiniaoProduto) o;
        return titulo.equals(that.titulo) && descricao.equals(that.descricao) && usuario.equals(that.usuario) && produto.equals(that.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, descricao, usuario, produto);
    }

    @Override
    public String toString() {
        return "OpiniaoProduto{" +
                "id=" + id +
                ", nota=" + nota +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", usuario=" + usuario +
                ", produto=" + produto +
                '}';
    }
}
