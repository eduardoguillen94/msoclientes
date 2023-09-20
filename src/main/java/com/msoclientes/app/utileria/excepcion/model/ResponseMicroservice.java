package com.msoclientes.app.utileria.excepcion.model;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ResponseMicroservice implements Serializable {

	private String codigo;

	private String mensaje;

	private String folio;

	private Object resultado;

	public ResponseMicroservice(HttpStatus status, String codigo, String mensaje, String folio, Object resultado) {
		super();
		this.codigo = String.valueOf(status.value()).concat(codigo).concat(String.valueOf(status.value()));
		this.mensaje = mensaje;
		this.folio = folio;
		this.resultado = resultado;
	}

}