#language: es
Característica: Modificar una incidencia
	Como personal de gestión de incidencias
	Quiero modificar una incidencia
	Para actualizar su estado y añadir comentarios
	Escenario: Pantalla "Incidencias asignadas" de la aplicacion
		Dado el personal de gestión de incidencias en la pantalla "Incidencias asignadas" de la aplicacion
		Cuando haga click en el boton "Modificar" de la primera incidencia de la tabla
		Entonces se mostrara la informacion de la incidencia y las opciones necesarias para modificarla
	Escenario: Pantalla "Modificar" 
		Dado el personal de gestión de incidencias en la pantalla "Modificar"
		Cuando seleccione el estado "CERRADA"
			Y escriba el comentario "Resuelta"
			Y pulse el botón "Modificar"
		Entonces se actualizan dichos valores y se muestra la pantalla "Detalles" de la incidencia
	