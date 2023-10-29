package br.edu.atitus.atitusound.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.atitus.atitusound.dtos.UserDTO;
import br.edu.atitus.atitusound.entities.UserEntity;
import br.edu.atitus.atitusound.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private final UserService service;

	public AuthController(UserService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/signup")
	public ResponseEntity<UserEntity> PostSignup(@RequestBody UserDTO dto ) {
		UserEntity entity = new UserEntity();
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
		entity.setUsername(dto.getUsername());
		entity.setPassword(dto.getPassword());
		
		try {
			service.save(entity);
		} catch (Exception e) {
			return ResponseEntity.badRequest().header("error", e.getMessage()).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(entity);
	}
	
	
}