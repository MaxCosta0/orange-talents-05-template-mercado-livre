package br.com.zupacademy.maxley.mercadolivre.controller;

import br.com.zupacademy.maxley.mercadolivre.controller.dto.NovoUsuarioRequest;
import br.com.zupacademy.maxley.mercadolivre.model.Usuario;
import br.com.zupacademy.maxley.mercadolivre.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid NovoUsuarioRequest novoUsuarioRequest){
        Usuario usuario = novoUsuarioRequest.toModel();
        usuarioRepository.save(usuario);
        return ResponseEntity.ok().build();
    }
}
