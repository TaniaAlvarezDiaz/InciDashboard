package test.cucumber.steps;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;

public class SeeInfoIncidenceStep {

private WebDriver driver = new HtmlUnitDriver(); // para usar selenium
	
	@Dado("^el personal de gestión de incidencias en la pantalla \"Incidencias asignadas\" de la aplicacion$")
	public void operador_en_pantalla_incidencias_asignadas() throws Throwable {
		// Hay que iniciar sesion, lo hacemos con susana
		driver.get("http://localhost:8092/login");
		driver.findElement(By.name("username")).sendKeys("09847158T");
		driver.findElement(By.name("password")).sendKeys("123456");
		driver.findElement(By.name("login")).click();

		List<WebElement> r = driver.findElements(By.xpath("//*[text() = 'Sistema de gestion de incidencias']"));
		assertEquals(1, r.size());
		
		//Vamos a Incidencias asignadas
		driver.findElement(By.name("assignedIncidents")).click();
		
		r = driver.findElements(By.xpath("//*[text() = 'Incidencias asignadas']"));
		assertEquals(1, r.size());
	}

	@Cuando("^haga click en el boton \"Detalles\" de la primera incidencia de la tabla$")
	public void pulse_detalles() throws Throwable {
		//Incidencia 1: incendio en el bosque
		driver.findElement(By.id("detalles1")).click();
	}

	@Entonces("^se mostrara la informacion de la incidencia$")
	public void aparece_tabla_con_incidencias() throws Throwable {
		List<WebElement> r = driver.findElements(By.xpath("//*[text() = 'Detalles de la incidencia']"));
		assertEquals(1, r.size());
	}
}
