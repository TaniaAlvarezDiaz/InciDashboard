#language: es
Característica: Iniciar sesion 
	Como personal de gestión de incidencias
	Quiero iniciar sesion
	Para ver las incidencias
	Escenario: Pantalla de Login de la aplicacion
		Dado el personal de gestión de incidencias con username "09847158T"
			Y contraseña "123456"
		Cuando rellene el formulario de login y haga click en el boton "Enviar"
		Entonces se iniciara sesion y se mostrará una pantalla informativa