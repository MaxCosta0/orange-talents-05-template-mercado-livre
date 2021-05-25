package br.com.zupacademy.maxley.mercadolivre.repository;

import br.com.zupacademy.maxley.mercadolivre.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
