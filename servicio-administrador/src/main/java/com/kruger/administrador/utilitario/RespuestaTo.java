package com.kruger.administrador.utilitario;

import java.io.Serializable;

import com.kruger.administrador.enumeradores.EstadoRespuestaEnum;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RespuestaTo<T> implements Serializable {

	private static final long serialVersionUID = 2668006018009654876L;

	private EstadoRespuestaEnum codigo;

	private String mensaje;

	private T objeto;
}
