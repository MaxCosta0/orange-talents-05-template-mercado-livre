package br.com.zupacademy.maxley.mercadolivre.controller;

import br.com.zupacademy.maxley.mercadolivre.config.validation.ProibeCaracteristicaComNomeIgualValidator;
import br.com.zupacademy.maxley.mercadolivre.controller.dto.NovaImagenRequest;
import br.com.zupacademy.maxley.mercadolivre.controller.dto.NovoProdutoRequest;
import br.com.zupacademy.maxley.mercadolivre.controller.dto.UploaderFake;
import br.com.zupacademy.maxley.mercadolivre.model.Produto;
import br.com.zupacademy.maxley.mercadolivre.model.Usuario;
import br.com.zupacademy.maxley.mercadolivre.repository.UsuarioRepository;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import java.util.Set;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {

    @PersistenceContext
    private EntityManager entityManager;

    private ProibeCaracteristicaComNomeIgualValidator proibeCaracteristicaComNomeIgualValidator;

    @Autowired
    private UploaderFake uploaderFake;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @InitBinder("novoProdutoRequest")
    private void init(WebDataBinder binder){
        binder.addValidators(new ProibeCaracteristicaComNomeIgualValidator());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid NovoProdutoRequest novoProdutoRequest){
        Usuario usuario = entityManager.find(Usuario.class, 1L);
        Produto produto = novoProdutoRequest.toModel(entityManager, usuario);
        entityManager.persist(produto);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/{id}/imagens")
    @Transactional
    public ResponseEntity<?> adicionaImagens(@PathVariable("id") Long id, @Valid NovaImagenRequest request){
        //Pensar em como recuperar o usuario pelo token.
        Usuario usuario = usuarioRepository.findByLogin("maxley@email.com").get();

        Set<String> links = uploaderFake.envia(request.getImagens());
        Produto produto = entityManager.find(Produto.class, id);
        Assert.state(produto != null, "[BUG] Tentativa de cadastrar imagem sem produto previamente cadastrado.");

        //Criar um Exception handler ou pensar em uma validacao customizada.
        if(!produto.pertecenceAoUsuario(usuario)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        produto.associaImagens(links);
        entityManager.merge(produto);
        return ResponseEntity.ok().build();
    }
}
