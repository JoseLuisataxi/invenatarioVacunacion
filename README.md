Proyecto Inventario de vacunación
	- 	El sistema tiene configurados dos roles: ADMINISTRADOR y EMPLEADO
	Como ADMINISTRADOR 
	-	El sistema permite registrar un empleado y darle de alta como usuario con perfil EMPLEADO
	- 	Consultar información de los empleador registrados:
		-	Listar todos los empleados
		-	Listar empleados en base a estado de vacunación 
		-	Listar empleados en base a tipo de vacuna 
		-	Listar empleador en base a un rango de fechas respecto a la fecha de vacunación
		-	Eliminar un empleado en base a la cédula
		-	Consultar un empleado en base a la cédula
	
	Como EMPLEADO
	-	Podrá relizar las siguientes acciones:
		- Acceder con su usuario(cédula) y clave (cédula) -> clave asiganda por defecto al dar de alta un usuario
		- Una vez que se valide su acceso podrá actualizar su información personal e información referente a la vacunación

 
Información adicional:
	-	Documentación de la API (Swagger-OpenAPI) --> http://localhost:33868/swagger-ui/index.html (el puerto se deberá cambiar por el que se asigne al servicio (servicio-administrador) cuando se levante dicho servicio)
	-	Base de Datos
		-	Postgres (usuario: postgres / clave: admin) -> El puerto de la BDD configurado es el 5433 en los archivos aplication.yml, cambiarlo de requerirlo
		-	Bajo la carpeta BDD se encuentra un respaldo de la base de datos Postgres (inventario-vacunacion)
		-	Bajo la carpeta BDD se encuentra el Diagrama Entidad Relacion DER-inventario-vacunacion.erd
	


Arquitectura:
	- servidor-oauth: Servidor de autenticación
	- servidor-eureka: Servidor de autenticación (puerto 8761)
	- servidor-zuul: Punto de entrada y balanceador (puerto 8443)
	- servicios
		- servicio-empleado (puertos dinámicos)
		- Servicio-administrador (puertos dinámicos)
		
BDD
	- Postgres (puerto 5433)
	- Java 11
	- Spring boot 

		
		