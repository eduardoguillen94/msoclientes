package com.msoclientes.app.utileria.excepcion.model;

import java.util.List;

import lombok.Data;

@Data
public class ErrorDescription {
	private String propiedad;

	private List<String> error;

	public ErrorDescription(String propiedad, List<String> error) {
		super();
		this.propiedad = propiedad;
		this.error = error;
	}
}