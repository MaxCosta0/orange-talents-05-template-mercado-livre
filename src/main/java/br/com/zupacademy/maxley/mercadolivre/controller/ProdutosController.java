package br.com.zupacademy.maxley.mercadolivre.controller;

import br.com.zupacademy.maxley.mercadolivre.config.validation.ProibeCaracteristicaComNomeIgualValidator;
import br.com.zupacademy.maxley.mercadolivre.controller.dto.NovoProdutoRequest;
import br.com.zupacademy.maxley.mercadolivre.model.Produto;
import br.com.zupacademy.maxley.mercadolivre.model.Usuario;
import br.com.zupacademy.maxley.mercadolivre.repository.CategoriaRepository;
import br.com.zupacademy.maxley.mercadolivre.repository.ProdutoRepository;
import br.com.zupacademy.maxley.mercadolivre.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProibeCaracteristicaComNomeIgualValidator proibeCaracteristicaComNomeIgualValidator;

   @InitBinder
   private void init(WebDataBinder binder){
        binder.addValidators(proibeCaracteristicaComNomeIgualValidator);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid NovoProdutoRequest novoProdutoRequest){
        Usuario usuario = usuarioRepository.findById(1L).get();
        Produto produto = novoProdutoRequest.toModel(categoriaRepository, usuario);
        produtoRepository.save(produto);
        return ResponseEntity.ok().build();
    }
}
