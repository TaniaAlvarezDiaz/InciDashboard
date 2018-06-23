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

public class SeeAssignedIncidencesStep {

	private WebDriver driver = new HtmlUnitDriver(); // para usar selenium
	
	@Dado("^el personal de gestión de incidencias en la pantalla informativa de la aplicacion$")
	public void operador_en_pantalla_informativa() throws Throwable {
		// Hay que iniciar sesion, lo hacemos con susana
		driver.get("http://localhost:8092/login");
		driver.findElement(By.name("username")).sendKeys("09847158T");
		driver.findElement(By.name("password")).sendKeys("123456");
		driver.findElement(By.name("login")).click();

		List<WebElement> r = driver.findElements(By.xpath("//*[text() = 'Sistema de gestion de incidencias']"));
		assertEquals(1, r.size());
	}

	@Cuando("^haga click en el boton \"Incidencias asignadas\" de la barra de navegacion$")
	public void pulse_incidencias_asignadas() throws Throwable {
		driver.findElement(By.name("assignedIncidents")).click();
	}

	@Entonces("^se mostrara una tabla con las incidencias asignadas$")
	public void aparece_tabla_con_incidencias() throws Throwable {
		List<WebElement> r = driver.findElements(By.xpath("//*[text() = 'Incidencias asignadas']"));
		assertEquals(1, r.size());
	}
}
