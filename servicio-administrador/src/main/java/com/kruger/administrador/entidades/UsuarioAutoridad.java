package com.kruger.administrador.entidades;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name = "seg_usuario_autoridad")
public class UsuarioAutoridad implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3044968879930462721L;
	@EmbeddedId
	private UsuarioAutoridadId autoridadId;
}
