package com.kruger.administrador.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class UsuarioAutoridadId  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "usuario_id")
	private Long usuarioId;

	@Column(name = "autoridad_id")
	private String autoridad;
}
