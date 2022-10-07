package com.kruger.administrador.repositorios;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kruger.administrador.entidades.Empleado;
import com.kruger.administrador.enumeradores.TipoVacunaEnum;

@Repository
public interface EmpleadoRepositorio extends CrudRepository<Empleado, String> {
	List<Empleado> findByNombres(String nombres);
	
	List<Empleado> findByEstadoVacunacion(boolean estadoVacuna);
	List<Empleado> findByTipoVacuna(TipoVacunaEnum tipoVacuna);
	List<Empleado> findByFechaVacunacionBetween(LocalDate fechaInicio, LocalDate fechaFin);

	@Modifying
	@Query("UPDATE Empleado e SET e.fechaNacimiento = :fechaNacimiento,  e.direccionDomicilio = :direccionDomicilio, e.telefonoMovil = :telefonoMovil,"
			+ " e.estadoVacunacion = :estadoVacunacion, e.tipoVacuna = :tipoVacuna, e.fechaVacunacion = :fechaVacunacion, e.numeroDosis = :numeroDosis"
			+ " WHERE e.cedula = :cedula")
	int actualizar(String cedula, LocalDate fechaNacimiento, String direccionDomicilio, String telefonoMovil,
			boolean estadoVacunacion, TipoVacunaEnum tipoVacuna, LocalDate fechaVacunacion, int numeroDosis);
	
	/*@Query(value = "select fecha_programada\\:\\:date, count(1) from asignacion_vacuna av "
			+ "where fecha_programada\\:\\:date = (select max(fecha_programada\\:\\:date) from asignacion_vacuna av) "
			+ "group by fecha_programada\\:\\:date;", nativeQuery = true)
	Optional<Object[]> buscarAsignacion();*/
	


}
