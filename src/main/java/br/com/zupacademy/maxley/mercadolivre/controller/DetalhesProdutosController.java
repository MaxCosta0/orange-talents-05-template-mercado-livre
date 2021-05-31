package br.com.zupacademy.maxley.mercadolivre.controller;


import br.com.zupacademy.maxley.mercadolivre.controller.dto.DetalhesProdutoResponse;
import br.com.zupacademy.maxley.mercadolivre.model.Produto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
public class DetalhesProdutosController {

    @PersistenceContext
    private EntityManager manager;

    @GetMapping(value = "/produtos/{id}/detalhes")
    public ResponseEntity<DetalhesProdutoResponse> detalharProduto(@PathVariable("id") Long id){
        Produto produto = manager.find(Produto.class, id);
        DetalhesProdutoResponse response = new DetalhesProdutoResponse(produto);
        return ResponseEntity.ok(response);
    }
}
