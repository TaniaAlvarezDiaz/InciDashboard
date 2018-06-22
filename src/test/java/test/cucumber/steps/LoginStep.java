package test.cucumber.steps;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import cucumber.api.java.es.Y;
	
public class LoginStep {

	private WebDriver driver = new HtmlUnitDriver(); //para usar selenium
	
	private String username;
	private String password;
	
	@Dado("^el agente con username \"([^\"]*)\"$")
	public void el_agente_con_username(String username) throws Throwable {
		this.username = username;
	}
	
	@Y("^contraseña \"([^\\\"]*)\"$")
	public void y_password(String contrasena) {
		this.password = contrasena;
	}

	@Cuando("^rellene el formulario de login y haga click en el boton \"Enviar\"$")
	public void el_agente_rellena_formulario_y_pulsa_enviar() throws Throwable {
		driver.get("http://localhost:8091/login");
		driver.findElement(By.name("username")).sendKeys(username);					
	    driver.findElement(By.name("password")).sendKeys(password);
	    driver.findElement(By.name("login")).click();
	}

	@Entonces("^se iniciara sesion y se mostrará una pantalla informativa$")
	public void el_agente_inicia_sesion_correctamente() throws Throwable {
		List<WebElement> r = driver.findElements(By.xpath("//*[text() = 'Sistema de gestion de incidencias']"));
		assertEquals(1, r.size());
	}

}
