package br.com.pegasus.Pegasus;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	//procurar o nome independente de ser maiusculo ou minusculo
	List<Cliente> findByNomeContainingIgnoreCase(String nome);
	//buscar mesmo nao tendo o nome completo
	@Query("SELECT c FROM Cliente c WHERE LOWER(c.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
	List<Cliente> buscarPorParteDoNome(@Param("nome") String nome);
}
