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

public class SeeIncidencesStep {

	private WebDriver driver = new HtmlUnitDriver(); // para usar selenium
	
	@Dado("^el personal de gestión de incidencias en la pantalla informativa de la aplicacion$")
	public void operator_en_pantalla_informativa() throws Throwable {
		// Hay que iniciar sesion, lo hacemos con susana
		driver.get("http://localhost:8092/login");
		driver.findElement(By.name("username")).sendKeys("09847158T");
		driver.findElement(By.name("password")).sendKeys("123456");
		driver.findElement(By.name("login")).click();

		List<WebElement> r = driver.findElements(By.xpath("//*[text() = 'Sistema de gestion de incidencias']"));
		assertEquals(1, r.size());
	}

	@Cuando("^haga click en el boton \"Incidencias actuales\" de la barra de navegacion$")
	public void click_incidencias_actuales() throws Throwable {
		driver.findElement(By.name("currentIncidents")).click();
	}

	@Entonces("^se mostrara una tabla con las incidencias actuales$")
	public void aparece_tabla_con_incidencias_actuales() throws Throwable {
		List<WebElement> r = driver.findElements(By.xpath("//*[text() = 'Incidencias']"));
		assertEquals(1, r.size());
	}
}
