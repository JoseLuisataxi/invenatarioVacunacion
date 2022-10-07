package com.kruger.administrador.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name = "seg_usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "cuenta_no_expirada")
	private boolean cuentaNoExpirada;
	
	@Column(name = "cuenta_no_bloqueada")
	private boolean cuentaNoBloqueada;
	
	@Column(name = "credencial_no_expirada")
	private boolean credencialNoExpirada;
	
	private boolean habilitado;
	
	private String contrasenia;
	
	@Column(name = "nombre_usuario")
	private String nombreUsuario;
	
}
