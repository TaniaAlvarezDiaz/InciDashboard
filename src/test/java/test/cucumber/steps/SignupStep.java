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
import cucumber.api.java.es.Y;

public class SignupStep {

private WebDriver driver = new HtmlUnitDriver(); //para usar selenium
	
	private String nombre;
	private String email;
	private String identificador;
	private String password;
	private String repeatPassword;
	
	@Dado("^el personal de gestión de incidencias con nombre \"([^\"]*)\"$")
	public void el_operador_con_nombre(String nombre) throws Throwable {
		this.nombre = nombre;
	}
	
	@Y("^email \"([^\"]*)\"$")
	public void y_email(String email) {
		this.email = email;
	}
	
	@Y("^identificador \"([^\"]*)\"$")
	public void y_identificador(String identificador) {
		this.identificador = identificador;
	}
	
	@Y("^password \"([^\\\"]*)\"$")
	public void y_password(String contrasena) {
		this.password = contrasena;
	}

	@Y("^contraseña repetida \"([^\\\"]*)\"$")
	public void y_repeat_password(String repetida) {
		this.repeatPassword = repetida;
	}
	
	@Cuando("^rellene el formulario de registro y haga click en el boton \"Signup\"$")
	public void el_operador_rellena_formulario_y_pulsa_signup() throws Throwable {
		driver.get("http://localhost:8092/signup");
		driver.findElement(By.name("nombre")).sendKeys(nombre);
		driver.findElement(By.name("email")).sendKeys(email);
		driver.findElement(By.name("identificador")).sendKeys(identificador);
	    driver.findElement(By.name("password")).sendKeys(password);
	    driver.findElement(By.name("passwordConfirm")).sendKeys(repeatPassword);
	    driver.findElement(By.name("signup")).click();
	}

	@Entonces("^se mostrará la pantalla de login$")
	public void aparece_login() throws Throwable {
		List<WebElement> r = driver.findElements(By.xpath("//*[text() = 'Login']"));
		assertEquals(1, r.size());
	}
}
