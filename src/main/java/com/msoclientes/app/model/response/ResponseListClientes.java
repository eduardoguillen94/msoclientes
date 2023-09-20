package com.msoclientes.app.model.response;

import java.io.Serializable;
import java.util.List;

import com.msoclientes.app.model.ClientesModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseListClientes implements Serializable {

	private List<ClientesModel> clientes;

}
