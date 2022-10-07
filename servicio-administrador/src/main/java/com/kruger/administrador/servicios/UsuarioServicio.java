package com.kruger.administrador.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kruger.administrador.entidades.Empleado;
import com.kruger.administrador.entidades.Usuario;
import com.kruger.administrador.entidades.UsuarioAutoridad;
import com.kruger.administrador.repositorios.UsuarioRepositorio;

@Service
public class UsuarioServicio {
	@Autowired
	private UsuarioRepositorio repositorio;
	
	@Autowired
	private BCryptPasswordEncoder encripta;

	@Transactional(propagation = Propagation.MANDATORY, noRollbackFor = Exception.class)
	public Usuario guardar(Usuario usuario) throws Exception {
		try {
			usuario.setContrasenia(encripta.encode(usuario.getContrasenia()));
			usuario.setCredencialNoExpirada(true);
			usuario.setCuentaNoBloqueada(true);
			usuario.setHabilitado(true);
			usuario.setCuentaNoExpirada(true);
			repositorio.save(usuario);
			return usuario;
		} catch (Exception ex) {
			throw new Exception(ex.getMessage(), ex);
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = Exception.class)
	public void actualizar(String nombreUsuario, String contrasenia)
			throws Exception {
		try {
			contrasenia = encripta.encode(contrasenia);
			if (repositorio.actualizar(nombreUsuario, contrasenia) != 1) {
				throw new Exception("No se actualizo la información del usuario");
			}
		} catch (Exception ex) {
			throw new Exception(ex.getMessage(), ex);
		}
	}
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Usuario buscarPorNombreUsuario(String nombreUsuario) throws Exception {
		Usuario usuario = repositorio.findByNombreUsuario(nombreUsuario);
		if (usuario == null) {
			throw new Exception("Usuario no encontrado");
		}
		return usuario;
	}
	
	@Transactional(propagation = Propagation.MANDATORY, noRollbackFor = Exception.class)
	public void eliminarUsuario(Usuario usuario) throws Exception {
		repositorio.delete(usuario);
	}
}
