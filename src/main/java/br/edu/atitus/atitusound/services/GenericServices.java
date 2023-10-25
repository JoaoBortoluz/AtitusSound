package br.edu.atitus.atitusound.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.edu.atitus.atitusound.entities.ArtistEntity;
import br.edu.atitus.atitusound.entities.GenericEntity;

public interface GenericServices<TEntidade extends GenericEntity> {
	
	
	default ArtistEntity save(ArtistEntity entity) throws Exception {
		validateSave(entity);
		artistRepository.save(entity);
		return entity;
	}
	
	default default List<ArtistEntity> findAll() throws Exception {
		return artistRepository.findAll();
	}
	
	default void validateFindByName(Pageable pageable, String name) throws Exception{
		
	}
	
	default default Page<List<ArtistEntity>> findByNameContainingIgnoreCase(Pageable pageable, String name) throws Exception {
		validateFindByName(pageable, name);
		return artistRepository.findByNameContainingIgnoreCase(pageable, name);
	}
	
	default void validateSave(ArtistEntity entity) throws Exception {
		if (entity.getName() == null || entity.getName().isEmpty())
			throw new Exception("Campo name invalido");
		if (entity.getUuid() == null) {
			if (artistRepository.existsByName(entity.getName()))
				throw new Exception("Já existe registro com este nome");
		} else {
			if (!artistRepository.existsById(entity.getUuid()))
				throw new Exception("Registro não encontrado com este UUID");
			if (artistRepository.existsByNameAndUuidNot(entity.getName(), entity.getUuid()))
				throw new Exception("Já existe registro com este nome");
		}
	}
	
	
	default default Optional<ArtistEntity> findById(UUID uuid) throws Exception {
		validateFindById(uuid);
		return artistRepository.findById(uuid);
	}
	default void validateFindById(UUID uuid) {
		
	}
	
	default void validateDeleteById(UUID uuid) throws Exception{
		if(!artistRepository.existsById(uuid))
			throw new Exception("Registro não encontrado com este uuid");
	}

	default void deleteById(UUID uuid) throws Exception {
		validateDeleteById(uuid);
		artistRepository.deleteById(uuid);
	}

}
