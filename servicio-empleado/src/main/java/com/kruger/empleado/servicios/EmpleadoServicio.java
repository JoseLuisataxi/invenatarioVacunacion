package com.kruger.empleado.servicios;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kruger.empleado.entidades.Empleado;
import com.kruger.empleado.enumeradores.TipoVacunaEnum;
import com.kruger.empleado.repositorios.EmpleadoRepositorio;

@Service
public class EmpleadoServicio {
	@Autowired
	private EmpleadoRepositorio repositorio;

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

			return empleado;
		} catch (Exception ex) {
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
}
