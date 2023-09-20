package com.msoclientes.app.utileria.excepcion.model;

import java.util.List;

import lombok.Data;

@Data
public class RespuestaErrorModel {
	private String codigo;
	private String mensaje;
	private String folio;
	private String info;
	private List<String> detalles;
}