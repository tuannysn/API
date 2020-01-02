package br.com.RestAPI.rest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

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
}
