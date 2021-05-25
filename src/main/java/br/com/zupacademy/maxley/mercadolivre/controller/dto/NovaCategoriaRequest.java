package br.com.zupacademy.maxley.mercadolivre.controller.dto;

import br.com.zupacademy.maxley.mercadolivre.config.annotation.ExisteId;
import br.com.zupacademy.maxley.mercadolivre.config.annotation.ValorUnico;
import br.com.zupacademy.maxley.mercadolivre.model.Categoria;
import br.com.zupacademy.maxley.mercadolivre.repository.CategoriaRepository;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class NovaCategoriaRequest {

    @NotBlank @ValorUnico(domainClass = Categoria.class, fieldName = "nome")
    public String nome;
    @ExisteId(domainClass = Categoria.class, fieldName = "id")
    public Long categoriaMaeId;

    @Deprecated
    public NovaCategoriaRequest(){}

    public NovaCategoriaRequest(String nome){
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Long getCategoriaMaeId() {
        return categoriaMaeId;
    }

    public Categoria toModel(CategoriaRepository categoriaRepository){
        Categoria categoria = new Categoria(nome);

        if(categoriaMaeId != null){
            Optional<Categoria> categoriaMae = categoriaRepository.findById(categoriaMaeId);
            Assert.state(categoriaMae.isPresent(), "Nao foi encontrada uma categoria com o id dado");
            categoria.setCategoriaMae(categoriaMae.get());
        }
        return categoria;
    }
}
