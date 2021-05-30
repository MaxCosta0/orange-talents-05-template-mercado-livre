package br.com.zupacademy.maxley.mercadolivre.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

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

    public PerguntaProduto(@NotBlank String titulo, @Valid Produto produto,
                           @Valid Usuario usuario) {

        this.titulo = titulo;
        this.produto = produto;
        this.usuario = usuario;
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
    public String toString() {
        return "PerguntaProduto{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", produto=" + produto +
                ", usuario=" + usuario +
                '}';
    }
}
