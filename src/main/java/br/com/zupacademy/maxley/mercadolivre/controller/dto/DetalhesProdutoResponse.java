package br.com.zupacademy.maxley.mercadolivre.controller.dto;

import br.com.zupacademy.maxley.mercadolivre.model.OpiniaoProduto;
import br.com.zupacademy.maxley.mercadolivre.model.Produto;

import java.math.BigDecimal;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.IntStream;

public class DetalhesProdutoResponse {
    private String nome;
    private BigDecimal preco;
    private String descricao;
    private Integer quantidadeNotas;
    private Double mediaNotas = 0D;
    private Set<DetalheCaracteristicaProduto> caracteristicas;
    private Set<DetalheImagemProduto> linksImagens;
    private Set<String> perguntas;
    private Set<DetalheOpiniaoProduto> opinioes;

    public DetalhesProdutoResponse(Produto produto) {
        this.nome = produto.getNome();
        this.preco = produto.getValor();
        this.descricao = produto.getDescricao();
        this.caracteristicas = produto.mapCaracteristicas(DetalheCaracteristicaProduto::new);
        this.linksImagens = produto.mapImagens(DetalheImagemProduto::new);
        this.perguntas = produto.mapPerguntas(pergunta -> pergunta.getTitulo());
        this.opinioes = produto.mapOpinioes(DetalheOpiniaoProduto::new);
        this.quantidadeNotas = opinioes.size();
        Set<Integer> notas = produto.mapOpinioes(opiniao -> opiniao.getNota());
        IntStream mapToInt = notas.stream().mapToInt(nota->nota);
        OptionalDouble media = mapToInt.average();
        if(media.isPresent()){
            this.mediaNotas = media.getAsDouble();
        }
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getQuantidadeNotas() {
        return quantidadeNotas;
    }

    public Double getMediaNotas() {
        return mediaNotas;
    }

    public Set<DetalheImagemProduto> getLinksImagens() {
        return linksImagens;
    }

    public Set<DetalheCaracteristicaProduto> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<String> getPerguntas() {
        return perguntas;
    }

    public Set<DetalheOpiniaoProduto> getOpinioes() {
        return opinioes;
    }
}
