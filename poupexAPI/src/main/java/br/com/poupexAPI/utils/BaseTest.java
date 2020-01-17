package br.com.poupexAPI.utils;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.BeforeClass;

import br.com.poupexAPI.core.Constantes;
import io.restassured.builder.*;

public class BaseTest implements Constantes {

	@BeforeClass
	public static void setup() {
		baseURI = APP_BASE_URL;
		
		RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
		reqBuilder.setContentType(APP_CONTENT_TYPE);
		requestSpecification = reqBuilder.build();

		ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
		resBuilder.expectResponseTime(lessThan(MAX_TIMEOUT));
		responseSpecification = resBuilder.build();
	}

}
