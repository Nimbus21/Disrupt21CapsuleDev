package br.com.fiap.capsuledev.domain.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.fiap.capsuledev.domain.Estado;

public class EstadoDTO {

	private Long codigo;
	private String nome;

	public EstadoDTO(Estado estado) {
		this.codigo = estado.getCodigo();
		this.nome = estado.getNome();
	}
	
	public Long getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}
	
	public static List<EstadoDTO> converter (List<Estado> estados) {
		return estados.stream().map(EstadoDTO::new).collect(Collectors.toList());
	}
	
}
