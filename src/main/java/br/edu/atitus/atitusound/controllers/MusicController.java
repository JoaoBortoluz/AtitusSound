package br.edu.atitus.atitusound.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.atitus.atitusound.dtos.MusicDTO;
import br.edu.atitus.atitusound.entities.ArtistEntity;
import br.edu.atitus.atitusound.entities.MusicEntity;
import br.edu.atitus.atitusound.services.GenericServices;
import br.edu.atitus.atitusound.services.MusicService;

@RestController
@RequestMapping("/musics")
public class MusicController extends GenericController<MusicEntity, MusicDTO>{
	
	private final MusicService service;
	
	public MusicController(MusicService service) {
		super();
		this.service = service;
	}

	@Override
	public GenericServices<MusicEntity> getService() {
		return service;
	}

	@Override
	protected MusicEntity convertDTO2Entity(MusicDTO dto) {
		MusicEntity entity =  new MusicEntity();
		entity.setName(dto.getName());
		entity.setDuration(dto.getDuration());
		entity.setUrl(dto.getUrl());
		ArtistEntity entityArtista = new ArtistEntity();
		entityArtista.setUuid(dto.getArtist().getUuid());
		entity.setArtist(entityArtista);
		
		return entity;
		
		
		
	}
}
