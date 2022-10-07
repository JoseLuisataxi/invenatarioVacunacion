package com.kruger.administrador.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kruger.administrador.entidades.Empleado;
import com.kruger.administrador.enumeradores.EstadoRespuestaEnum;
import com.kruger.administrador.servicios.EmpleadoServicio;
import com.kruger.administrador.utilitario.EntradaEmpleadoDto;
import com.kruger.administrador.utilitario.RespuestaTo;

import lombok.AccessLevel;
import lombok.Getter;

@RestController
public class EmpleadoControlador {

	@Getter(value = AccessLevel.PROTECTED)
	@Autowired
	private EmpleadoServicio servicio;

	@GetMapping("/cedula/{cedula}")
	public ResponseEntity<RespuestaTo<Empleado>> buscarPorCedula(@PathVariable String cedula) {
		try {
			return new ResponseEntity<>(new RespuestaTo<>(EstadoRespuestaEnum.OK, null, servicio.buscarPorId(cedula)),
					HttpStatus.OK);
		} catch (Exception ex) {
			return responderError(ex);
		}
	}

	@ResponseBody
	@PostMapping(path = "/crear", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaTo<Empleado>> crear(@RequestBody @Valid Empleado empleado) {
		try {
			return new ResponseEntity<>(new RespuestaTo<>(EstadoRespuestaEnum.OK, null, servicio.guardar(empleado)),
					HttpStatus.OK);
		} catch (Exception ex) {
			return responderError(ex);
		}
	}

	@ResponseBody
	@PutMapping(path = "/actualizar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaTo<String>> actualizar(@RequestBody @Valid EntradaEmpleadoDto empleadoDto) {
		try {
			servicio.actualizar(empleadoDto.getCedula(), empleadoDto.getFechaNacimiento(),
					empleadoDto.getDireccionDomicilio(), empleadoDto.getTelefonoMovil(),
					empleadoDto.isEstadoVacunacion(), empleadoDto.getTipoVacuna(), empleadoDto.getFechaVacunacion(),
					empleadoDto.getNumeroDosis());
			return new ResponseEntity<>(new RespuestaTo<>(EstadoRespuestaEnum.OK, null, null), HttpStatus.OK);
		} catch (Exception ex) {
			return responderError(ex);
		}
	}

	@GetMapping("/listarEmpleados")
	public ResponseEntity<RespuestaTo<Iterable<Empleado>>> listarEmpleados() {
		try {
			return new ResponseEntity<>(new RespuestaTo<>(EstadoRespuestaEnum.OK, null, servicio.listarTodos()),
					HttpStatus.OK);
		} catch (Exception ex) {
			return responderError(ex);
		}
	}

	@DeleteMapping("/eliminar/{cedula}")
	public ResponseEntity<RespuestaTo<String>> eliminar(@PathVariable String cedula) {
		try {
			servicio.eliminarEmpleado(cedula);
			return new ResponseEntity<>(new RespuestaTo<>(EstadoRespuestaEnum.OK, null, null), HttpStatus.OK);
		} catch (Exception ex) {
			return responderError(ex);
		}
	}

	public <X> ResponseEntity<RespuestaTo<X>> responderError(Exception ex) {
		return new ResponseEntity<>(new RespuestaTo<>(EstadoRespuestaEnum.ERROR, ex.getMessage(), null),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
