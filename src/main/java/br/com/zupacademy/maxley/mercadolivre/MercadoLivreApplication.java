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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class MercadoLivreApplication implements CommandLineRunner {

	@PersistenceContext
	private EntityManager entityManager;

	public static void main(String[] args) {
		SpringApplication.run(MercadoLivreApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		SenhaLimpa senhaLimpa = new SenhaLimpa("123456");
		Usuario usuario1 = new Usuario("maxley@email.com", senhaLimpa);
		Usuario usuario2 = new Usuario("maxley2@email.com", senhaLimpa);
		entityManager.persist(usuario1);
		entityManager.persist(usuario2);

		Categoria categoria1 = new Categoria("Tecnologia");
		entityManager.persist(categoria1);
	}
}
