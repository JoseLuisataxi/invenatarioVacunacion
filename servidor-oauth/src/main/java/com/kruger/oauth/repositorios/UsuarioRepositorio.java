package com.kruger.oauth.repositorios;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kruger.oauth.entidades.Usuario;

@Repository
public interface UsuarioRepositorio extends CrudRepository<Usuario, Long> {

	Optional<Usuario> findByUsername(String username);
}
