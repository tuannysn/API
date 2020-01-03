package br.com.RestAPI.rest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.XmlPath.CompatibilityMode;

public class AuthTest {
	
	@Test
	public void deveAcessarSWAPI() {
		given()
			.log().all()
		.when()
			.get("https://swapi.co/api/people/1/")
		.then()
			.log().all()
			.statusCode(200)
			.body("name", is("Luke Skywalker"))
		;
	}
	
	//http://api.openweathermap.org/data/2.5/weather?q=Salvador,BR&appid=8d3345ae968b0ed2dd765b12909b1bc9&&units=metric
	
	//8d3345ae968b0ed2dd765b12909b1bc9
	
	@Test
	public void deveObterClima() {
	given()
		.log().all()
		.queryParam("q", "Salvador,BR")
		.queryParam("appid", "8d3345ae968b0ed2dd765b12909b1bc9")
		.queryParam("units", "metric")
	.when()
		.get("http://api.openweathermap.org/data/2.5/weather")
	.then()
		.log().all()
		.statusCode(200)
		.body("name", is("Salvador"))
		.body("coord.lon", is(-38.48f))
		.body("main.temp", greaterThan(25))
		;
	}

//	http://restapi.wcaquino.me/basicauth
	
	@Test
	public void naoDeveAcessarSemSenha() {
	given()
		.log().all()
	.when()
		.get("http://restapi.wcaquino.me/basicauth")
	.then()
		.log().all()
		.statusCode(401)
		;
	}
	
	@Test
	public void deveFazerAutenticacaoBasica() {
	given()
		.log().all()
	.when()
		.get("http://admin:senha@restapi.wcaquino.me/basicauth")
	.then()
		.log().all()
		.statusCode(200)
		.body("status",is("logado"))
		;
	}
	
	@Test
	public void deveFazerAutenticacaoBasica2() {
	given()
		.log().all()
		.auth().basic("admin", "senha")
	.when()
		.get("http://restapi.wcaquino.me/basicauth")
	.then()
		.log().all()
		.statusCode(200)
		.body("status",is("logado"))
		;
	}
	
	@Test
	public void deveFazerAutenticacaoBasicaChallenge() {
	given()
		.log().all()
		.auth().preemptive().basic("admin", "senha")
	.when()
		.get("http://restapi.wcaquino.me/basicauth2")
	.then()
		.log().all()
		.statusCode(200)
		.body("status",is("logado"))
		;
	}
	
	@Test
	public void deveFazerAutenticacaoComToken() {
		Map<String, String> login = new HashMap<String, String>();
		login.put("email", "tuannynazareth@hotmail.com");
		login.put("senha", "123456");
		
		//login API
		//Receber o token
		String token = given()
			.log().all()
			.body(login)
			.contentType(ContentType.JSON)
		.when()
			.post("http://barrigarest.wcaquino.me/signin")
		.then()
			.log().all()
			.statusCode(200)
			.extract().path("token")
		;
		//Obter as contas
		given()
			.log().all()
			.header("Authorization", "JWT "+ token)
		.when()
			.get("http://barrigarest.wcaquino.me/contas")
		.then()
			.log().all()
			.statusCode(200)
			.body("nome", hasItem("Conta Teste 1"))
		;
	}

	@Test
	public void deveAcessarAplicacaoWeb(){
		//login
		String cookie = given()
			.log().all()
			.formParam("email", "tuannynazareth@hotmail.com")
			.formParam("senha", "123456")
			.contentType(ContentType.URLENC.withCharset("UTF-8"))
		.when()
			.post("https://seubarriga.wcaquino.me/logar")
		.then()
			.log().all()
			.statusCode(200)
			.extract().header("set-cookie")
		;
		cookie = cookie.split("=")[1].split(";")[0];
		System.out.println(cookie);
		//obter conta
		String body = given()
			.log().all()
			.cookie("connect.sid", cookie)
		.when()
			.get("https://seubarriga.wcaquino.me/contas")
		.then()
			.log().all()
			.statusCode(200)
			.body("html.body.table.tbody.tr[0].td[0]", is("Conta Teste 1"))
			.extract().body().asString()
		;
		System.out.println("------------------------------------------");
		XmlPath xmlPath = new XmlPath(CompatibilityMode.HTML, body);
		System.out.println(xmlPath.getString("html.body.table.tbody.tr[0].td[0]"));
	}
}
