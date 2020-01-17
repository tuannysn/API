package br.com.poupexAPI.core;

import io.restassured.http.ContentType;

public interface Constantes {
	String APP_BASE_URL="https://wwwl.montreal.com.br/CPSORIGINACAODEV/v1/";
	ContentType APP_CONTENT_TYPE = ContentType.JSON;
	Long MAX_TIMEOUT = 5000L;
}
