package br.com.zupacademy.maxley.mercadolivre.controller;

import br.com.zupacademy.maxley.mercadolivre.controller.dto.GatewayPagamento;
import br.com.zupacademy.maxley.mercadolivre.controller.dto.NovaCompraRequest;
import br.com.zupacademy.maxley.mercadolivre.model.Compra;
import br.com.zupacademy.maxley.mercadolivre.model.Produto;
import br.com.zupacademy.maxley.mercadolivre.model.Usuario;
import br.com.zupacademy.maxley.mercadolivre.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class ComprasController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping(value = "/compras")
    @Transactional
    public String comprar(@RequestBody @Valid NovaCompraRequest request,
                                  UriComponentsBuilder uriComponentsBuilder) throws BindException {
        Produto produtoASerComprado = manager.find(Produto.class, request.getProdutoId());
        boolean temSuficiente = produtoASerComprado.temQuantidadeSuficiente(request.getQuantidade());

        if(temSuficiente){
            Usuario comprador = usuarioRepository.findByLogin("maxley@email.com").get();
            Compra compra = new Compra(produtoASerComprado, request.getQuantidade(),
                    comprador, request.getFormaPagamento());
            manager.persist(compra);

            if(compra.getFormaPagamento().equals(GatewayPagamento.pagseguro)){
                String urlPagseguro = uriComponentsBuilder.path("retorno-pagseguro")
                        .buildAndExpand(compra.getId()).toString();

                return "pagseguro.com?buyerId=" + compra.getId() + "&redirectUrl="+urlPagseguro;
            }
            else{
                String urlPaypal = uriComponentsBuilder.path("retorno-paypal")
                        .buildAndExpand(compra.getId()).toString();
                return "pagseguro.com?buyerId=" + compra.getId() + "&redirectUrl="+urlPaypal;
            }
        }

        BindException prolemaComEstoque = new BindException(request, "novaCompraRequest");
        prolemaComEstoque.reject(null, "Nao temos quantidade suficiente no estoque");
        throw prolemaComEstoque;
    }
}
