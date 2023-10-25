package br.edu.atitus.atitusound.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.atitus.atitusound.dtos.ArtistDTO;
import br.edu.atitus.atitusound.entities.ArtistEntity;
import br.edu.atitus.atitusound.services.ArtistService;

@RestController
@RequestMapping("/artists")//tudo que for /artists essa classe vai controlar
public class ArtistController {
	
	private final ArtistService artistService;
	
	//injeção de dependencia
	public ArtistController(ArtistService artistService) { //Spring cria a instancia
		super();
		this.artistService = artistService;
	}

	protected ArtistEntity convertDTO2Entity(ArtistDTO dto) {
		ArtistEntity entity =  new ArtistEntity();
		entity.setName(dto.getName());
		entity.setImage(dto.getImage());
		return entity;
	}
	
	@PostMapping 
	public ResponseEntity<ArtistEntity> post(@RequestBody ArtistDTO dto){
		ArtistEntity entity = convertDTO2Entity(dto);
		try {
			artistService.save(entity);
		} catch (Exception e) {
			return ResponseEntity.badRequest().header("error", e.getMessage()).build(); //erro 400
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(entity);
	}
	@PutMapping("/{uuid}")
	public ResponseEntity<ArtistEntity> put(@RequestBody ArtistDTO dto,@PathVariable UUID uuid){
		ArtistEntity entity = convertDTO2Entity(dto);
		entity.setUuid(uuid);
		try {
			artistService.save(entity);
		} catch (Exception e) {
			return ResponseEntity.badRequest().header("error", e.getMessage()).build(); //erro 400
		}
		return ResponseEntity.status(HttpStatus.OK).body(entity);
		//return ResponseEntity.ok(entity);
	}
	
	@DeleteMapping("/{uuid}")
	public ResponseEntity<?> delete(@PathVariable UUID uuid){ //null ou ?(aceita qualquer coisa)
		try {
			artistService.deleteById(uuid);
		} catch (Exception e) {
			return ResponseEntity.badRequest().header("error", e.getMessage()).build();
		}
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{uuid}")
	public ResponseEntity<ArtistEntity> getById(@PathVariable UUID uuid) {
		Optional<ArtistEntity> entidade;
		try {
			entidade = artistService.findById(uuid);
		} catch (Exception e) {
			return ResponseEntity.badRequest().header("error", e.getMessage()).build();		
		}
		if (entidade.isEmpty())
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(entidade.get());
	}
	
	@GetMapping
	public ResponseEntity<Page<List<ArtistEntity>>> get(@PageableDefault(page = 0, size = 10, sort = "name", direction = Direction.ASC) Pageable pageable, @RequestParam String name){
		Page<List<ArtistEntity>> lista;
		try {
			lista = artistService.findByNameContainingIgnoreCase(pageable, name);
		} catch (Exception e) {
			return ResponseEntity.badRequest().header("error", e.getMessage()).build();
		}
		return ResponseEntity.ok(lista);
	}
}
