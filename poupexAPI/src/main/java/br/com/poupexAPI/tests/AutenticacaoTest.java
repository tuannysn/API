package br.com.poupexAPI.tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.*;
import org.junit.runners.MethodSorters;

import br.com.poupexAPI.consultaBD.ConsultarBanco;
import br.com.poupexAPI.utils.BaseTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AutenticacaoTest extends BaseTest{
	
	public static String cpfLogin;
	ConsultarBanco consultaLogin = new ConsultarBanco();
	
	@Test
	public void t01_validarUsuarioSenhaInvalidos() {
			Map<String, String> login = new HashMap<String, String>();
			login.put("Email", "");
			login.put("Senha", "");
		given()	
			.log().all()
			.body(login)
		.when()
			.post("autenticacao/autenticarUsuario")
		.then()
			.log().all()
			.statusCode(200)
			.body("Erros", hasItem("Usuário ou senha inválido."))
		;
	}
	
	@Test
	public void t02_validarLoginComSucesso() throws SQLException {
			Map<String, String> login = new HashMap<String, String>();
			login.put("Email", "tdsn07@gmail.com");
			login.put("Senha", "Teste1234");
		cpfLogin = given()	
			.log().all()
			.body(login)
		.when()
			.post("autenticacao/autenticarUsuario")
		.then()
			.log().all()
			.statusCode(200)
			.extract().path("Dados")
		;
		return;
	}
}
