package br.com.zupacademy.maxley.mercadolivre.repository;

import br.com.zupacademy.maxley.mercadolivre.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
