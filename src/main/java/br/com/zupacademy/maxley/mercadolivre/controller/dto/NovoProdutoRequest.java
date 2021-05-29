package br.com.zupacademy.maxley.mercadolivre.controller.dto;

import br.com.zupacademy.maxley.mercadolivre.config.annotation.ExisteId;
import br.com.zupacademy.maxley.mercadolivre.model.Categoria;
import br.com.zupacademy.maxley.mercadolivre.model.Produto;
import br.com.zupacademy.maxley.mercadolivre.model.Usuario;
import br.com.zupacademy.maxley.mercadolivre.repository.CategoriaRepository;
import io.jsonwebtoken.lang.Assert;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.*;

public class NovoProdutoRequest {
    @NotBlank
    private String nome;
    @Positive
    @NotNull
    private BigDecimal valor;
    @Positive
    @NotNull
    private Integer quantidadeDisponivel;
    @NotBlank
    @Size(max = 1000)
    private String descricao;
    @NotNull
    @ExisteId(domainClass = Categoria.class, fieldName = "id")
    private Long categoriaId;
    @Size(min = 3) @Valid
    private List<NovaCaracteristicaRequest> caracteristicas = new ArrayList<>();

    public NovoProdutoRequest(String nome, BigDecimal valor,
                              Integer quantidadeDisponivel,
                              String descricao, Long categoriaId,
                              List<NovaCaracteristicaRequest> caracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.descricao = descricao;
        this.categoriaId = categoriaId;
        this.caracteristicas.addAll(caracteristicas);
    }

    /*
    * Utilizei esse getter para retornar as categorias para
    * meu validator de caracteristicas iguais. Sem o getter
    * da um erro dizendo que nao consegue ler as caracteristicas
    * na hora de estourar uma exception e da erro 500.
    * */
    public List<NovaCaracteristicaRequest> getCaracteristicas() {
        return caracteristicas;
   }

    public Produto toModel(EntityManager entityManager, Usuario dono){
        //Optional<Categoria> categoria = categoriaRepository.findById(categoriaId);
        Categoria categoria = entityManager.find(Categoria.class, categoriaId);
        Assert.state(categoria != null, "[Bug] A categoria com id = "+categoriaId+" nao existe no banco.");
        return new Produto(nome, valor, quantidadeDisponivel, descricao, categoria, dono, caracteristicas);
    }

    @Override
    public String toString() {
        return "NovoProdutoRequest{" +
                "nome='" + nome + '\'' +
                ", valor=" + valor +
                ", quantidadeDisponivel=" + quantidadeDisponivel +
                ", descricao='" + descricao + '\'' +
                ", categoriaId=" + categoriaId +
                ", caracteristicas=" + caracteristicas +
                '}';
    }

    public boolean temCaracteristicasIguais() {
        HashSet<String> nomesIguais = new HashSet<>();

        for(NovaCaracteristicaRequest caracteristica: caracteristicas){
            if(!nomesIguais.add(caracteristica.getNome())){
                return true;
            }
        }
        return false;
    }
}
