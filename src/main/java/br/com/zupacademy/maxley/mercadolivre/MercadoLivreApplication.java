package br.com.zupacademy.maxley.mercadolivre;

import br.com.zupacademy.maxley.mercadolivre.controller.dto.SenhaLimpa;
import br.com.zupacademy.maxley.mercadolivre.model.Categoria;
import br.com.zupacademy.maxley.mercadolivre.model.Usuario;
import br.com.zupacademy.maxley.mercadolivre.repository.CategoriaRepository;
import br.com.zupacademy.maxley.mercadolivre.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class MercadoLivreApplication implements CommandLineRunner {

	@Autowired
	public UsuarioRepository usuarioRepository;

	@Autowired
	public CategoriaRepository categoriaRepository;

	public static void main(String[] args) {
		SpringApplication.run(MercadoLivreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		SenhaLimpa senhaLimpa = new SenhaLimpa("123456");
		Usuario usuario = new Usuario("maxley@email.com", senhaLimpa);
		usuarioRepository.save(usuario);

		Categoria categoria = new Categoria("Tecnologia");
		categoriaRepository.save(categoria);
	}
}
