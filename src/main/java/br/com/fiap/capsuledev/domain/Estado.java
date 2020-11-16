package br.com.fiap.capsuledev.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table (name = "TB_ESTADO")
@SequenceGenerator(name = "estadoSequence", sequenceName = "SQ_ESTADO", allocationSize = 1)
public class Estado {
	
	@Id
	@Column(name = "cd_estado")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estadoSequence")
	private Long codigo;
	
	@Column(name = "nm_estado")
	private String nome;

	public Estado() {
	}

	public Estado(Long codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
