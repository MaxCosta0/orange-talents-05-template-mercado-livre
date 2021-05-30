package br.com.zupacademy.maxley.mercadolivre.controller.dto;

import br.com.zupacademy.maxley.mercadolivre.model.ImagemProduto;

public class DetalheImagemProduto {

    private String link;

    @Deprecated
    public DetalheImagemProduto(){}

    public DetalheImagemProduto(ImagemProduto imagem){
        this.link = imagem.getLink();
    }

    public String getLink() {
        return link;
    }
}
