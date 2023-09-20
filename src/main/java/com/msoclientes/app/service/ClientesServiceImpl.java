package com.msoclientes.app.service;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.msoclientes.app.dao.ClientesDAO;
import com.msoclientes.app.model.ClientesModel;
import com.msoclientes.app.model.request.ClienteActualizaReqModel;
import com.msoclientes.app.model.request.ClientesRequestModel;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientesServiceImpl implements ClientesService {

	private final ClientesDAO clientesDAO;

	@Override
	public Object setClientes(ClientesRequestModel cliente) throws Exception {

		ClientesModel clienteModel = new ClientesModel();
		AtomicInteger atomicInteger = new AtomicInteger(1);
		clienteModel.setId(atomicInteger.get());
		clienteModel.setNombre(cliente.getNombre());
		clienteModel.setCorreo(cliente.getCorreo());

		return clientesDAO.setClientes(clienteModel);
	}

	@Override
	public Object getClientes() throws Exception {
		return clientesDAO.getClientes();
	}

	@Override
	public Object getDetalleCliente(String nombre) throws Exception {
		// TODO Auto-generated method stub
		return clientesDAO.getDetalleCliente(nombre);
	}

	@Override
	public Object actualizaCliente(ClienteActualizaReqModel cliente) throws Exception {

		ClientesModel clienteModel = new ClientesModel();

		clienteModel.setId(cliente.getId());
		clienteModel.setNombre(cliente.getNombre());
		clienteModel.setCorreo(cliente.getCorreo());

		return clientesDAO.actualizaCliente(clienteModel);

	}

	@Override
	public Object deleteCliente(String nombre) throws Exception {

		return clientesDAO.deleteCliente(nombre);
	}
}
