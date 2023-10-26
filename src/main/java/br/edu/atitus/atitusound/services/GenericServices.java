package br.edu.atitus.atitusound.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.edu.atitus.atitusound.entities.GenericEntity;
import br.edu.atitus.atitusound.repositories.GenericRepository;

public interface GenericServices<TEntidade extends GenericEntity> {
	
	GenericRepository<TEntidade> getRepository();
	
	default TEntidade save(TEntidade entity) throws Exception {
		validateSave(entity);
		getRepository().save(entity);
		return entity;
	}
	
	default List<TEntidade> findAll() throws Exception {
		return getRepository().findAll();
	}
	
	default void validateFindByName(Pageable pageable, String name) throws Exception{
		
	}
	
	default Page<List<TEntidade>> findByNameContainingIgnoreCase(Pageable pageable, String name) throws Exception {
		validateFindByName(pageable, name);
		return getRepository().findByNameContainingIgnoreCase(pageable, name);
	}
	
	default void validateSave(TEntidade entity) throws Exception {
		if (entity.getName() == null || entity.getName().isEmpty())
			throw new Exception("Campo name invalido");
		if (entity.getUuid() == null) {
			if (getRepository().existsByName(entity.getName()))
				throw new Exception("Já existe registro com este nome");
		} else {
			if (!getRepository().existsById(entity.getUuid()))
				throw new Exception("Registro não encontrado com este UUID");
			if (getRepository().existsByNameAndUuidNot(entity.getName(), entity.getUuid()))
				throw new Exception("Já existe registro com este nome");
		}
	}
	
	
	default Optional<TEntidade> findById(UUID uuid) throws Exception {
		validateFindById(uuid);
		return getRepository().findById(uuid);
	}
	default void validateFindById(UUID uuid) {
		
	}
	
	default void validateDeleteById(UUID uuid) throws Exception{
		if(!getRepository().existsById(uuid))
			throw new Exception("Registro não encontrado com este uuid");
	}

	default void deleteById(UUID uuid) throws Exception {
		validateDeleteById(uuid);
		getRepository().deleteById(uuid);
	}

}
