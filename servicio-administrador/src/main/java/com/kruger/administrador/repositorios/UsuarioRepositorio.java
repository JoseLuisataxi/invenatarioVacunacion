package com.kruger.administrador.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kruger.administrador.entidades.Usuario;

@Repository
public interface UsuarioRepositorio extends CrudRepository<Usuario, Long> {
	@Modifying
	@Query("UPDATE Usuario u SET u.contrasenia = :contrasenia WHERE u.nombreUsuario = :nombreUsuario")
	int actualizar(String nombreUsuario, String contrasenia);
	
	Usuario findByNombreUsuario(String nombreUsuario);
}
