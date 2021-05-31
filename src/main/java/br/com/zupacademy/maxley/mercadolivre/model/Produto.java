package br.com.zupacademy.maxley.mercadolivre.model;

import br.com.zupacademy.maxley.mercadolivre.controller.dto.DetalheCaracteristicaProduto;
import br.com.zupacademy.maxley.mercadolivre.controller.dto.DetalheImagemProduto;
import br.com.zupacademy.maxley.mercadolivre.controller.dto.NovaCaracteristicaRequest;
import io.jsonwebtoken.lang.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @Positive @NotNull
    private BigDecimal valor;
    @Positive @NotNull
    private Integer quantidadeDisponivel;
    @NotBlank @Size(max = 1000)
    private String descricao;
    @NotNull @Valid @ManyToOne
    private Categoria categoria;
    @NotNull @Valid @ManyToOne
    private Usuario dono;
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<ImagemProduto> imagens = new HashSet<>();
    @OneToMany(mappedBy = "produto")
    private Set<PerguntaProduto> perguntas = new HashSet<>();
    @OneToMany(mappedBy = "produto")
    private Set<OpiniaoProduto> opinioes = new HashSet<>();

    @Deprecated
    public Produto(){}

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

    public void associaImagens(Set<String> links) {
        this.imagens.addAll(links.stream().map(link -> new ImagemProduto(this, link))
                .collect(Collectors.toSet()));
    }

    public boolean pertecenceAoUsuario(Usuario usuario) {
        return this.dono.equals(usuario);
    }

    public String getEmailDono() {
        return this.dono.getUsername();
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
                ", imagens=" + imagens +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

     public <T> Set<T> mapCaracteristicas(Function<CaracteristicaProduto, T> funcaoMapeadora){
        return this.caracteristicas.stream().map(funcaoMapeadora)
                .collect(Collectors.toSet());
    }

    public <T> Set<T> mapImagens(Function<ImagemProduto, T> funcaoMapeadora) {
        return this.imagens.stream().map(funcaoMapeadora).collect(Collectors.toSet());
    }

    public <T> Set<T> mapPerguntas(Function<PerguntaProduto, T> funcaoMapeadora) {
        return this.perguntas.stream().map(funcaoMapeadora).collect(Collectors.toSet());
    }

    public <T> Set<T> mapOpinioes(Function<OpiniaoProduto, T> funcaoMapeadora){
        return this.opinioes.stream().map(funcaoMapeadora).collect(Collectors.toSet());
    }

    public boolean temQuantidadeSuficiente(Integer quantidade){
        if(this.quantidadeDisponivel >= quantidade) {
            this.quantidadeDisponivel -= quantidade;
            return true;
        }
        return false;
    }
}
