package br.edu.atitus.atitusound.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.atitus.atitusound.dtos.ArtistDTO;
import br.edu.atitus.atitusound.entities.ArtistEntity;
import br.edu.atitus.atitusound.services.ArtistService;
import br.edu.atitus.atitusound.services.GenericServices;

@RestController
@RequestMapping("/artists")//tudo que for /artists essa classe vai controlar
public class ArtistController extends GenericController<ArtistEntity, ArtistDTO>{
	
	private final ArtistService artistService;
	
	//injeção de dependencia
	public ArtistController(ArtistService artistService) { //Spring cria a instancia
		super();
		this.artistService = artistService;
	}

	protected ArtistEntity convertDTO2Entity(ArtistDTO dto) {
		ArtistEntity entity =  new ArtistEntity();
		BeanUtils.copyProperties(dto, entity);
		return entity;
	}

	@Override
	public GenericServices<ArtistEntity> getService() {
		return artistService;
	}
	
}
