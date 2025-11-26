package andre.dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import andre.dev.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	@Transactional(readOnly = true)
	@Query("SELECT p FROM Produto p WHERE LOWER(p.nome) LIKE %:nome%")
	List<Produto> buscarPorNome(@Param("nome") String nome);
	

}