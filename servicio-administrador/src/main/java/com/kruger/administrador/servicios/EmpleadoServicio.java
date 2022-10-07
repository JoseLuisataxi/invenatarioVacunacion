package com.kruger.administrador.servicios;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kruger.administrador.entidades.Empleado;
import com.kruger.administrador.entidades.Usuario;
import com.kruger.administrador.entidades.UsuarioAutoridad;
import com.kruger.administrador.entidades.UsuarioAutoridadId;
import com.kruger.administrador.enumeradores.TipoVacunaEnum;
import com.kruger.administrador.repositorios.EmpleadoRepositorio;

@Service
public class EmpleadoServicio {
	@Autowired
	private EmpleadoRepositorio repositorio;

	@Autowired
	private UsuarioServicio usuarioServicio;

	@Autowired
	private UsuarioAutoridadServicio usuarioAutoridadServicio;

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Empleado buscarPorId(String cedula) throws Exception {
		Optional<Empleado> oEmpleado = repositorio.findById(cedula);
		if (oEmpleado.isEmpty()) {
			throw new Exception("Empleado no encontrado");
		}
		return oEmpleado.get();
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = Exception.class)
	public Empleado guardar(Empleado empleado) throws Exception {
		try {
			if (repositorio.existsById(empleado.getCedula())) {
				throw new EntityExistsException("Ya se tiene registrado la cédula: " + empleado.getCedula());
			}
			repositorio.save(empleado);

			Usuario usuario = new Usuario();
			usuario.setNombreUsuario(empleado.getCedula());
			usuario.setContrasenia(empleado.getCedula());
			usuarioServicio.guardar(usuario);

			UsuarioAutoridadId usuarioAutoridadId = new UsuarioAutoridadId();
			usuarioAutoridadId.setUsuarioId(usuario.getId());
			usuarioAutoridadId.setAutoridad("EMPLEADO");

			usuarioAutoridadServicio.guardar(new UsuarioAutoridad(usuarioAutoridadId));

			return empleado;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(ex.getMessage(), ex);
		}
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = Exception.class)
	public void actualizar(String cedula, LocalDate fechaNacimiento, String direccionDomicilio, String telefonoMovil,
			boolean estadoVacunacion, TipoVacunaEnum tipoVacuna, LocalDate fechaVacunacion, int numeroDosis)
			throws Exception {
		try {
			if (estadoVacunacion) {
				if (Objects.isNull(tipoVacuna) || Objects.isNull(fechaVacunacion) || numeroDosis == 0) {
					throw new Exception(
							"Debe ingresar valores para los campos tipoVacuna, fechaVacunacion y numeroDosis mayor 0");
				}
			}
			if (repositorio.actualizar(cedula, fechaNacimiento, direccionDomicilio, telefonoMovil, estadoVacunacion,
					tipoVacuna, fechaVacunacion, numeroDosis) != 1) {
				throw new Exception("No se actualizo la información del empleado");
			}
		} catch (Exception ex) {
			throw new Exception(ex.getMessage(), ex);
		}
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Iterable<Empleado> listarTodos() throws Exception {
		return repositorio.findAll();
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = Exception.class)
	public void eliminarEmpleado(String cedula) throws Exception {
		Empleado empleado = new Empleado();
		empleado.setCedula(cedula);
		
		Usuario usuario = usuarioServicio.buscarPorNombreUsuario(cedula);
		
		UsuarioAutoridadId usuarioAutoridadId = new UsuarioAutoridadId();
		usuarioAutoridadId.setUsuarioId(usuario.getId());
		usuarioAutoridadId.setAutoridad("EMPLEADO");
		usuarioAutoridadServicio.eliminarUsuarioAutoridad(new UsuarioAutoridad(usuarioAutoridadId));
		
		usuarioServicio.eliminarUsuario(usuario);
		
		repositorio.delete(empleado);
	}
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Iterable<Empleado> buscarPorEstadoVacunacion(boolean estadoVacunacion) throws Exception {
		return repositorio.findByEstadoVacunacion(estadoVacunacion);
	}
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Iterable<Empleado> buscarPorTipoVacuna(TipoVacunaEnum tipoVacuna) throws Exception {
		return repositorio.findByTipoVacuna(tipoVacuna);
	}
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Iterable<Empleado> buscarPorRangoFechaVacunacion(LocalDate fechaInicio, LocalDate fechaFin) throws Exception {
		return repositorio.findByFechaVacunacionBetween(fechaInicio, fechaFin);
	}
}
