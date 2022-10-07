package com.kruger.administrador.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kruger.administrador.entidades.UsuarioAutoridad;
import com.kruger.administrador.entidades.UsuarioAutoridadId;

@Repository
public interface UsuarioAutoridadRepositorio extends CrudRepository<UsuarioAutoridad, UsuarioAutoridadId> {

}
