package com.msoclientes.app.utileria.excepcion.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class ResponseError implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String codigo;

	private String mensaje;

	private String folio;

	private String info;

	private List<?> detalles;

	public ResponseError(String codigo, String mensaje, String folio, String info, List<?> detalles) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
		this.folio = folio;
		this.info = info;
		this.detalles = detalles;
	}

	public ResponseError() {
		super();
	}

}