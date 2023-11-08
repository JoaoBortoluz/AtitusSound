package br.edu.atitus.atitusound.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.atitus.atitusound.dtos.PlaylistDTO;
import br.edu.atitus.atitusound.entities.PlaylistEntity;
import br.edu.atitus.atitusound.services.GenericServices;
import br.edu.atitus.atitusound.services.PlaylistService;

@RestController
@RequestMapping("/playlists")
public class PlaylistController extends GenericController<PlaylistEntity, PlaylistDTO>{
	
	private final PlaylistService service;
	
	

	public PlaylistController(PlaylistService service) {
		super();
		this.service = service;
	}

	@Override
	public GenericServices<PlaylistEntity> getService() {
		return service;
	}

	@Override
	protected PlaylistEntity convertDTO2Entity(PlaylistDTO dto) {
		PlaylistEntity entity = new PlaylistEntity();
		entity.setName(dto.getName());
		entity.setPublicShare(dto.getPublic_share());
			
		return entity;
	}
	

}
