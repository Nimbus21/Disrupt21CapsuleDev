package br.com.fiap.capsuledev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.capsuledev.domain.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
	List<Estado> findByNomeContaining (String nome);
}
