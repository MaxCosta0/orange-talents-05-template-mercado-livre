package br.com.zupacademy.maxley.mercadolivre;

import br.com.zupacademy.maxley.mercadolivre.controller.dto.NovaCaracteristicaRequest;
import br.com.zupacademy.maxley.mercadolivre.controller.dto.SenhaLimpa;
import br.com.zupacademy.maxley.mercadolivre.model.CaracteristicaProduto;
import br.com.zupacademy.maxley.mercadolivre.model.Categoria;
import br.com.zupacademy.maxley.mercadolivre.model.Produto;
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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collector;

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

		NovaCaracteristicaRequest caracteristica1 = new NovaCaracteristicaRequest("Cor", "Preto");
		NovaCaracteristicaRequest caracteristica2 = new NovaCaracteristicaRequest("Tamanho", "Normal");
		NovaCaracteristicaRequest caracteristica3 = new NovaCaracteristicaRequest("Largura", "Normal");
		Collection<NovaCaracteristicaRequest> caracteristicas = new ArrayList<>();
		caracteristicas.add(caracteristica1);
		caracteristicas.add(caracteristica2);
		caracteristicas.add(caracteristica3);

		Produto produto = new Produto("teclado", new BigDecimal("310"), 1, "teclado mecanico", categoria1, usuario1, caracteristicas);
		entityManager.persist(produto);
	}
}
