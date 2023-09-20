package com.msoclientes.app.model.request;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ClientesRequestModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nombre;
	private String correo;
}
