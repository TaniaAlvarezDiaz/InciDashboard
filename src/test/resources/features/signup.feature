#language: es
Característica: Registrarse 
	Como personal de gestión de incidencias
	Quiero registrarme
	Para poder ver las incidencias
	Escenario: Pantalla de Registro de la aplicacion
		Dado el personal de gestión de incidencias con nombre "Fran Gil"
			Y email "fran@gmail.com"
			Y identificador "09820531Z"
			Y password "123456"
			Y contraseña repetida "123456"
		Cuando rellene el formulario de registro y haga click en el boton "Signup"
		Entonces se mostrará la pantalla de login