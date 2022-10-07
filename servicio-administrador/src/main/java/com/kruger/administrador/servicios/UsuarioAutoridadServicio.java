package com.kruger.administrador.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kruger.administrador.entidades.UsuarioAutoridad;
import com.kruger.administrador.repositorios.UsuarioAutoridadRepositorio;

@Service
public class UsuarioAutoridadServicio {
	@Autowired
	private UsuarioAutoridadRepositorio repositorio;

	@Transactional(propagation = Propagation.MANDATORY, noRollbackFor = Exception.class)
	public UsuarioAutoridad guardar(UsuarioAutoridad usuarioAutoridad) throws Exception {
		try {
			repositorio.save(usuarioAutoridad);
			return usuarioAutoridad;
		} catch (Exception ex) {
			throw new Exception(ex.getMessage(), ex);
		}
	}
	
	@Transactional(propagation = Propagation.MANDATORY, noRollbackFor = Exception.class)
	public void eliminarUsuarioAutoridad(UsuarioAutoridad usuarioAutoridad) throws Exception {
		repositorio.delete(usuarioAutoridad);
	}

}
