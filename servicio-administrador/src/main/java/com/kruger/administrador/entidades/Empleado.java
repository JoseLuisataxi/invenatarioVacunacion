package com.kruger.administrador.entidades;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import com.kruger.administrador.enumeradores.TipoVacunaEnum;
import com.kruger.administrador.validaciones.CedulaEcuador;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name = "empleado")
public class Empleado {
	
	private static final String SOLO_LETRAS_ESPACIOS = "^[a-zA-Z ]+$";
	private static final String SOLO_NUMEROS = "/^[0-9]$/";

	@Id
	@NotBlank
	@CedulaEcuador
	private String cedula;

	@NotBlank(message = "El campo nombre es obligatorio")
	@Pattern(regexp = SOLO_LETRAS_ESPACIOS, message = "Debe ingresar solo letras")
	private String nombres;
	
	@NotBlank(message = "El campo apellidos es obligatorio")
	@Pattern(regexp = SOLO_LETRAS_ESPACIOS, message = "Debe ingresar solo letras")
	private String apellidos;
	
	@NotBlank(message = "El campo apellidos es obligatorio")
	@Email(message = "Formato de correo invalido")
	private String correoElectronico;
	
	@Past(message = "La fecha debe ser menor a la fecha actual")
	private LocalDate fechaNacimiento;
	
	private String direccionDomicilio;
	
	@Pattern(regexp = SOLO_NUMEROS, message = "Debe ingresar solo números")
	private String telefonoMovil;
	
	private boolean estadoVacunacion;
	
	@Enumerated(EnumType.STRING)
	private TipoVacunaEnum tipoVacuna;

	@Past(message = "La fecha debe ser menor a la fecha actual")
	private LocalDate fechaVacunacion;
	
	private int numeroDosis;
	
	
}
