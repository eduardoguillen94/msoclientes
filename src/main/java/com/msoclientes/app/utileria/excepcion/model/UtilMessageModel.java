package com.msoclientes.app.utileria.excepcion.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class UtilMessageModel implements Serializable {
	
	private String propiedad;
	
	private String mensaje;
}