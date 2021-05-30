package br.com.zupacademy.maxley.mercadolivre.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class PerguntaProduto {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    private String titulo;
    @ManyToOne
    private Produto produto;
    @ManyToOne
    private Usuario usuario;
    private LocalDateTime instanteCriacao;

    @Deprecated
    public PerguntaProduto(){}

    public PerguntaProduto(@NotBlank String titulo, @Valid Produto produto,
                           @Valid Usuario usuario) {

        this.titulo = titulo;
        this.produto = produto;
        this.usuario = usuario;
        this.instanteCriacao = LocalDateTime.now();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getEmailDonoProduto() {
        return this.produto.getEmailDono();
    }

    public String getEmailUsuario() {
        return this.usuario.getUsername();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PerguntaProduto)) return false;
        PerguntaProduto that = (PerguntaProduto) o;
        return getTitulo().equals(that.getTitulo()) && produto.equals(that.produto) && usuario.equals(that.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitulo(), produto, usuario);
    }

    @Override
    public String toString() {
        return "PerguntaProduto{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", produto=" + produto +
                ", usuario=" + usuario +
                ", instanteCriacao=" + instanteCriacao +
                '}';
    }
}
