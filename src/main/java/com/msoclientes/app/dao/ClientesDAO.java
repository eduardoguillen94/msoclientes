package com.msoclientes.app.dao;

import com.msoclientes.app.model.ClientesModel;

public interface ClientesDAO {

	Object setClientes(ClientesModel cliente) throws Exception;

	Object getClientes() throws Exception;

	Object getDetalleCliente(String nombre) throws Exception;

	Object actualizaCliente(ClientesModel clienteModel) throws Exception;

	Object deleteCliente(String nombre) throws Exception;

}
