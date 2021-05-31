package br.com.zupacademy.maxley.mercadolivre.controller;

import br.com.zupacademy.maxley.mercadolivre.controller.dto.CentralEmail;
import br.com.zupacademy.maxley.mercadolivre.controller.dto.NovaPerguntaRequest;
import br.com.zupacademy.maxley.mercadolivre.model.PerguntaProduto;
import br.com.zupacademy.maxley.mercadolivre.model.Produto;
import br.com.zupacademy.maxley.mercadolivre.model.Usuario;
import br.com.zupacademy.maxley.mercadolivre.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class PerguntasSobreProdutoController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CentralEmail centralEmail;

    @PostMapping(value = "/produtos/{id}/perguntas")
    @Transactional
    public ResponseEntity<?> fazerPergunta(@PathVariable("id") Long id, @RequestBody @Valid NovaPerguntaRequest request){
        Produto produto = manager.find(Produto.class, id);
        Usuario usuario = usuarioRepository.findByLogin("maxley2@email.com").get();
        PerguntaProduto pergunta = request.toModel(manager, produto, usuario);
        manager.persist(pergunta);
        centralEmail.enviarEmailPergunta(pergunta);
        return ResponseEntity.ok().build();
    }
}