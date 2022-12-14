package com.kruger.empleado.utilitario;

import java.io.Serializable;

import com.kruger.empleado.enumeradores.EstadoRespuestaEnum;

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
