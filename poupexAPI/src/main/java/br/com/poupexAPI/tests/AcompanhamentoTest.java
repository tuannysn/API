package br.com.poupexAPI.tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.sql.SQLException;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.poupexAPI.consultaBD.ConsultarBanco;
import br.com.poupexAPI.utils.BaseTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AcompanhamentoTest extends BaseTest{
	
	public static String codProposta;
	public static String propostaInv = "19121700001902";
	ConsultarBanco consulta = new ConsultarBanco();
	
	@Test
	public void t01_deveValidarStringParaCampoCPF() {
		given()
			.log().all()
			.pathParam("id", "Teste_01")
		.when()
			.get("acompanhamento/obterProposta/{id}")
		.then()
			.log().all()
			.statusCode(400)
			.body("Message", is("String reference not set to an instance of a String.\r\nParameter name: s"))
			;
	}
	
	@Test
	public void t02_deveValidarCPFSemProposta() {
		given()
			.log().all()
			.pathParam("id", "21374399990")
		.when()
			.get("acompanhamento/obterProposta/{id}")
		.then()
			.log().all()
			.statusCode(200)
			.body("Erros", hasItem("Nenhum processo aberto para o CPF 21374399990"))
			;
	}
	
	@Test
	public void t03_naoDeveListarPropostaInvalida() {
		given()
		.pathParam("id", propostaInv)
		.log().all()
	.when()
		.get("acompanhamento/listarFasesProposta/{id}")
	.then()
		.log().all()
		.statusCode(200)
		.body("Erros", hasItem("Processo "+propostaInv+" não localizado"))
		;
	}
	
	@Test
	public void t04_deveObterPropostaComSucesso() throws SQLException {
		codProposta = given()
			.log().all()
			.pathParam("id", AutenticacaoTest.cpfLogin)
		.when()
			.get("acompanhamento/obterProposta/{id}")
		.then()
			.assertThat()
			.log().all()
			.statusCode(200)
			.extract().path("Dados.CodigoProposta")
		;
		System.out.println(codProposta);
	}
	
	@Test
	public void t05_deveListarFasesDaPropostaComSucesso() {
		given()
			.pathParam("id", codProposta)
			.log().all()
		.when()
			.get("acompanhamento/listarFasesProposta/{id}")
		.then()
			.log().all()
			.statusCode(200)
			;
	}	
	
}
