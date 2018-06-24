package test.cucumber.steps;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;

import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import cucumber.api.java.es.Y;
import model.Incidence;
import model.Incidence.Estado;
import repositories.IncidencesRepository;

public class ModifyIncidenceStep {

	private WebDriver driver = new HtmlUnitDriver(); // para usar selenium

	private Incidence i;
	Integer id = 1;

	@Autowired
	private IncidencesRepository incidencesRepository;

	@Dado("^el personal de gestión de incidencias en la pantalla \"Incidencias asignadas\" de la aplicacion$")
	public void ims_en_pantalla_incidencias_asignadas() throws Throwable {
		// Hay que iniciar sesion, lo hacemos con susana
		driver.get("http://localhost:8092/login");
		driver.findElement(By.name("username")).sendKeys("09847158T");
		driver.findElement(By.name("password")).sendKeys("123456");
		driver.findElement(By.name("login")).click();

		List<WebElement> r = driver.findElements(By.xpath("//*[text() = 'Sistema de gestion de incidencias']"));
		assertEquals(1, r.size());

		// Vamos a Incidencias asignadas
		driver.findElement(By.name("assignedIncidents")).click();

		r = driver.findElements(By.xpath("//*[text() = 'Incidencias asignadas']"));
		assertEquals(1, r.size());
	}

	@Cuando("^haga click en el boton \"Modificar\" de la primera incidencia de la tabla$")
	public void pulse_modificar_primera_incidencia() throws Throwable {
		i = incidencesRepository.findById(id.longValue());
		// Incidencia 1: incendio en el bosque
		driver.findElement(By.id("modificar1")).click();
	}

	@Entonces("^se mostrara la informacion de la incidencia y las opciones necesarias para modificarla$")
	public void aparece_pantalla_modificar_incidencia() throws Throwable {
		List<WebElement> r = driver.findElements(By.xpath("//*[text() = 'Modificar incidencia']"));
		assertEquals(1, r.size());
	}

	@Dado("^el personal de gestión de incidencias en la pantalla \"Modificar\"$")
	public void operador_en_pantalla_modificar_incidencia() throws Throwable {
		// Hay que iniciar sesion, lo hacemos con susana
		driver.get("http://localhost:8092/login");
		driver.findElement(By.name("username")).sendKeys("09847158T");
		driver.findElement(By.name("password")).sendKeys("123456");
		driver.findElement(By.name("login")).click();

		List<WebElement> r = driver.findElements(By.xpath("//*[text() = 'Sistema de gestion de incidencias']"));
		assertEquals(1, r.size());

		// Vamos a Incidencias asignadas
		driver.findElement(By.name("assignedIncidents")).click();

		r = driver.findElements(By.xpath("//*[text() = 'Incidencias asignadas']"));
		assertEquals(1, r.size());

		// Pulsamos en la primera incidencia para modificar
		driver.findElement(By.id("modificar1")).click();

		r = driver.findElements(By.xpath("//*[text() = 'Modificar incidencia']"));
		assertEquals(1, r.size());
	}

	@Cuando("^seleccione el estado \"([^\"])\"$")
	public void seleccione_estado(Estado estado) throws Throwable {
		i.setEstado(estado);
	}

	@Y("^escriba el comentario \"([^\"])\"$")
	public void escriba_comentario(String comentario) throws Throwable {
		i.setComments(comentario);
	}

	@Y("^pulse el botón \"Modificar\"$")
	public void pulse_modificar_button() throws Throwable {
		driver.findElement(By.id("modificarButton")).click();
	}

	@Entonces("^se actualizan dichos valores y se muestra la pantalla \"Detalles\" de la incidencia")
	public void se_muestra_info_actualizada() throws Throwable {
		// Se comprueba que se actualizaron los valores
		Incidence iModificada = incidencesRepository.findById(id.longValue());
		assertEquals(i.toString(), iModificada.toString());

		List<WebElement> r = driver.findElements(By.xpath("//*[text() = 'Detalles de la incidencia']"));
		assertEquals(1, r.size());

	}
}
