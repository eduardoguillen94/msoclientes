package com.msoclientes.app.service;

import com.msoclientes.app.model.request.ClienteActualizaReqModel;
import com.msoclientes.app.model.request.ClientesRequestModel;

public interface ClientesService {

	Object setClientes(ClientesRequestModel cliente) throws Exception;

	Object getClientes() throws Exception;

	Object getDetalleCliente(String nombre) throws Exception;

	Object actualizaCliente(ClienteActualizaReqModel cliente) throws Exception;

	Object deleteCliente(String nombre) throws Exception;
}
