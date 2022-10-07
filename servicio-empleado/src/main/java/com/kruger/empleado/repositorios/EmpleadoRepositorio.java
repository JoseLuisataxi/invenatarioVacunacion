package com.kruger.empleado.repositorios;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kruger.empleado.entidades.Empleado;
import com.kruger.empleado.enumeradores.TipoVacunaEnum;

@Repository
public interface EmpleadoRepositorio extends CrudRepository<Empleado, String> {
	List<Empleado> findByNombres(String nombres);

	@Modifying
	@Query("UPDATE Empleado e SET e.fechaNacimiento = :fechaNacimiento,  e.direccionDomicilio = :direccionDomicilio, e.telefonoMovil = :telefonoMovil,"
			+ " e.estadoVacunacion = :estadoVacunacion, e.tipoVacuna = :tipoVacuna, e.fechaVacunacion = :fechaVacunacion, e.numeroDosis = :numeroDosis"
			+ " WHERE e.cedula = :cedula")
	int actualizar(String cedula, LocalDate fechaNacimiento, String direccionDomicilio, String telefonoMovil,
			boolean estadoVacunacion, TipoVacunaEnum tipoVacuna, LocalDate fechaVacunacion, int numeroDosis);

}
