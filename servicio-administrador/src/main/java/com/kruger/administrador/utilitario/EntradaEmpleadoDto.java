package com.kruger.administrador.utilitario;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.kruger.administrador.enumeradores.TipoVacunaEnum;

import lombok.Data;

@Data
public class EntradaEmpleadoDto {
	@NotNull
	String cedula;
	LocalDate fechaNacimiento;
	String direccionDomicilio;
	String telefonoMovil;
	boolean estadoVacunacion;
	TipoVacunaEnum tipoVacuna;
	LocalDate fechaVacunacion;
	int numeroDosis;
}
