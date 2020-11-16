package br.com.fiap.capsuledev.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.capsuledev.domain.Estado;
import br.com.fiap.capsuledev.domain.dto.EstadoDTO;
import br.com.fiap.capsuledev.repository.EstadoRepository;

@RestController
@RequestMapping("estado")
public class EstadoResource {
	
	@Autowired
	EstadoRepository estadoRepository;
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<EstadoDTO> listar(String nome){
		List<Estado> estados = null;
		
		if (nome == null) {
			estados = estadoRepository.findAll();
		} else {
			estados = estadoRepository.findByNomeContaining(nome);
		}
		
		return EstadoDTO.converter(estados);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<EstadoDTO> listarId(@PathVariable("id") Long codigo){
		Optional<Estado> estados = estadoRepository.findById(codigo);
		return estados.map(e -> ResponseEntity.ok(new EstadoDTO(e))).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<EstadoDTO> adicionar(@RequestBody Estado estado, UriComponentsBuilder uriBuilder) {
		estadoRepository.save(estado);
		
		URI uri = uriBuilder.path("/estado/{id}").buildAndExpand(estado.getCodigo()).toUri();
		
		return ResponseEntity.created(uri).body(new EstadoDTO(estado));
	}
	
	@PutMapping("{id}")
	@Transactional
	public ResponseEntity<EstadoDTO> atualizar(@PathVariable("id") Long codigo, @RequestBody Estado estadoNovo) {
		Optional<Estado> estado = estadoRepository.findById(codigo);
		
		return estado.map(e -> {
			e.setNome(estadoNovo.getNome());
			estadoRepository.save(e);
			return ResponseEntity.ok(new EstadoDTO(e));
		}).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("{id}")
	@Transactional
	public ResponseEntity<?> apagar(@PathVariable("id") Long codigo){
		Optional<Estado> estado = estadoRepository.findById(codigo);
		return estado.map(e -> {
				estadoRepository.deleteById(codigo);
				
				return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

}
