package br.edu.atitus.atitusound.servicesimpl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.atitus.atitusound.entities.UserEntity;
import br.edu.atitus.atitusound.repositories.GenericRepository;
import br.edu.atitus.atitusound.repositories.UserRepository;
import br.edu.atitus.atitusound.services.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService{
	
	private final PasswordEncoder encoder;
	private final UserRepository repository;
	
	public UserServiceImpl(UserRepository repository, PasswordEncoder encoder) {
		super();
		this.encoder = encoder;
		this.repository = repository;
	}

	@Override
	public GenericRepository<UserEntity> getRepository() {
		return repository;
	}

	@Override
	public void validateSave(UserEntity entity) throws Exception {
		UserService.super.validateSave(entity);
		if (entity.getUsername() == null || entity.getUsername().isEmpty())
			throw new Exception("Username inválido");
		if (entity.getPassword() == null || entity.getPassword().isEmpty())
			throw new Exception("Password inválido");
		
		entity.setPassword(encoder.encode(entity.getPassword()));
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var user = repository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com esse nome"));
		return user;
	}
	
	
}
