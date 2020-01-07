package br.com.APIBarriga.rest.core;

import io.restassured.http.ContentType;

public interface Constantes {
	String APP_BASE_URL = "https://barrigarest.wcaquino.me/";
	Integer APP_PORT = 443; // http -> 80
	String APP_BASE_PATH = "";
//	String APP_EMAIL = "tuannynazareth@hotmail.com";
//	String APP_SENHA = "123456";
	
	ContentType APP_CONTENT_TYPE = ContentType.JSON;
	
	Long MAX_TIMEOUT = 5000L;
}
