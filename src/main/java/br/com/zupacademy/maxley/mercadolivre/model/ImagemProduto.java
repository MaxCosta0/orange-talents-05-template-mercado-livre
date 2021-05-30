package br.com.zupacademy.maxley.mercadolivre.model;

import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class ImagemProduto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @Valid @ManyToOne
    private Produto produto;

    @URL
    private String link;

    @Deprecated
    public ImagemProduto() {}

    public ImagemProduto(@NotNull @Valid Produto produto, @URL String link) {
        this.produto = produto;
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImagemProduto)) return false;
        ImagemProduto that = (ImagemProduto) o;
        return produto.equals(that.produto) && link.equals(that.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produto, link);
    }

    @Override
    public String toString() {
        return "ImagemProduto{" +
                "link='" + link + '\'' +
                '}';
    }
}
