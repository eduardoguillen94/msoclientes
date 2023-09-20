package com.msoclientes.app.common;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class Util {

	@Value("${info.app.name}")
	private String nombreAplicacion;
	
	@Value("${info.app.id}")
	private Integer idMicroservicio;
	
	private static final Pattern PATTERN = Pattern.compile("[-:T.]");
	private static final int MAXSIZE = 16;

	public String obtenerFoliador() {
		LocalDateTime localDateTime = LocalDateTime.now();
		Matcher m = PATTERN.matcher(localDateTime.toString());
		return idMicroservicio + "-" + m.replaceAll("").substring(0, MAXSIZE) + "00";
	}

	public String obtenerCodigoResponse() {
		return ".".concat(nombreAplicacion).concat(".").concat(String.valueOf(idMicroservicio));
	}

	public String getFormatoCodex(String codigoHtttp) {
		return codigoHtttp.concat(".").concat(nombreAplicacion).concat(".")
				.concat(String.valueOf(idMicroservicio)).concat(codigoHtttp);
	}
}