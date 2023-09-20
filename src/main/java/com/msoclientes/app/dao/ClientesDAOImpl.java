package com.msoclientes.app.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.msoclientes.app.common.Constantes;
import com.msoclientes.app.common.Util;
import com.msoclientes.app.model.ClientesModel;
import com.msoclientes.app.model.response.ResponseListClientes;
import com.msoclientes.app.utileria.excepcion.model.MsoClientesExceptionAPI;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ClientesDAOImpl implements ClientesDAO {

	private final Util util;

	@Value("${data.clientes}")
	private String clientesData;

	@Override
	public Object setClientes(ClientesModel cliente) throws Exception {

		boolean banderaOk = false;
		ObjectMapper objectMapper = new ObjectMapper();

		objectMapper.configure(Feature.AUTO_CLOSE_SOURCE, true);
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {

			File myObj = new File(clientesData);

			if (!myObj.exists()) {
				if (myObj.createNewFile()) {
					System.out.println("File created: " + myObj.getName());
					objectMapper.writeValue(myObj, new ArrayList<>());
				}
			}

			List<ClientesModel> listaClientes = new ArrayList<>();

			List<ClientesModel> clientes = Arrays.asList(objectMapper.readValue(myObj, ClientesModel[].class));

			if (!clientes.isEmpty()) {
				Integer idConsecutivo = clientes.get(clientes.size() - 1).getId();

				cliente.setId(idConsecutivo + 1);
			}
			ClientesModel clienteEncontrado = clientes.parallelStream()
					.filter(cf -> cf.getCorreo().trim().equals(cliente.getCorreo().trim())
							&& cf.getNombre().trim().equals(cliente.getNombre()))
					.findFirst().orElse(null);

			if (clienteEncontrado != null) {
				throw new MsoClientesExceptionAPI(HttpStatus.BAD_REQUEST, util.obtenerCodigoResponse(),
						Constantes.OPERACION_400, util.obtenerFoliador(),
						Arrays.asList(Constantes.DETAIL_CLIENT_FOUND));
			}

			listaClientes.add(cliente);

			List<ClientesModel> clientesFinal = Stream.of(clientes, listaClientes).flatMap(Collection::stream)
					.distinct().collect(Collectors.toList());

			if (!clientesFinal.isEmpty()) {
				objectMapper.writeValue(myObj, clientesFinal);
				banderaOk = true;
			}

		} catch (IOException e) {
			throw new MsoClientesExceptionAPI(HttpStatus.INTERNAL_SERVER_ERROR, util.obtenerCodigoResponse(),
					Constantes.OPERACION_500, util.obtenerFoliador(), Arrays.asList(Constantes.OPERACION_500));
		}

		return banderaOk;
	}

	@Override
	public Object getClientes() throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();

		objectMapper.configure(Feature.AUTO_CLOSE_SOURCE, true);
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {

			File myObj = new File(clientesData);

			if (!myObj.exists()) {
				if (myObj.createNewFile()) {
					System.out.println("File created: " + myObj.getName());
					objectMapper.writeValue(myObj, new ArrayList<>());
				}
			}

			List<ClientesModel> clientes = Arrays.asList(objectMapper.readValue(myObj, ClientesModel[].class));

			if (clientes.isEmpty()) {
				throw new MsoClientesExceptionAPI(HttpStatus.NOT_FOUND, util.obtenerCodigoResponse(),
						Constantes.OPERACION_404, util.obtenerFoliador(), Arrays.asList(Constantes.OPERACION_404));
			}

			ResponseListClientes responseCliente = new ResponseListClientes();
			responseCliente.setClientes(clientes);
			return responseCliente;
		} catch (Exception ex) {
			throw new MsoClientesExceptionAPI(HttpStatus.INTERNAL_SERVER_ERROR, util.obtenerCodigoResponse(),
					Constantes.OPERACION_500, util.obtenerFoliador(), Arrays.asList(Constantes.OPERACION_500));
		}

	}

	@Override
	public Object getDetalleCliente(String nombre) throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();

		objectMapper.configure(Feature.AUTO_CLOSE_SOURCE, true);
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {

			File myObj = new File(clientesData);

			if (!myObj.exists()) {
				if (myObj.createNewFile()) {
					System.out.println("File created: " + myObj.getName());
					objectMapper.writeValue(myObj, new ArrayList<>());
				}
			}

			List<ClientesModel> clientes = Arrays.asList(objectMapper.readValue(myObj, ClientesModel[].class));

			if (clientes.isEmpty()) {
				throw new MsoClientesExceptionAPI(HttpStatus.NOT_FOUND, util.obtenerCodigoResponse(),
						Constantes.OPERACION_404, util.obtenerFoliador(), Arrays.asList(Constantes.OPERACION_404));
			}

			ClientesModel clienteEncontrado = clientes.stream()
					.filter(cf -> cf.getNombre().toLowerCase().contains(nombre.toLowerCase())).findFirst().orElse(null);

			if (clienteEncontrado == null) {
				throw new MsoClientesExceptionAPI(HttpStatus.NOT_FOUND, util.obtenerCodigoResponse(),
						Constantes.OPERACION_404, util.obtenerFoliador(), Arrays.asList(Constantes.OPERACION_404));
			}

			ResponseListClientes responseCliente = new ResponseListClientes();
			responseCliente.setClientes(Arrays.asList(clienteEncontrado));
			return responseCliente;

		} catch (Exception ex) {
			throw new MsoClientesExceptionAPI(HttpStatus.INTERNAL_SERVER_ERROR, util.obtenerCodigoResponse(),
					Constantes.OPERACION_500, util.obtenerFoliador(), Arrays.asList(Constantes.OPERACION_500));
		}

	}

	@Override
	public Object actualizaCliente(ClientesModel clienteModel) throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();

		objectMapper.configure(Feature.AUTO_CLOSE_SOURCE, true);
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {

			File myObj = new File(clientesData);

			if (!myObj.exists()) {
				if (myObj.createNewFile()) {
					System.out.println("File created: " + myObj.getName());
					objectMapper.writeValue(myObj, new ArrayList<>());
				}
			}

			List<ClientesModel> clientes = Arrays.asList(objectMapper.readValue(myObj, ClientesModel[].class));

			if (clientes.isEmpty()) {
				throw new MsoClientesExceptionAPI(HttpStatus.NOT_FOUND, util.obtenerCodigoResponse(),
						Constantes.OPERACION_404, util.obtenerFoliador(), Arrays.asList(Constantes.OPERACION_404));
			}

			ClientesModel clienteEncontrado = clientes.stream()
					.filter(cf -> cf.getId().intValue() == clienteModel.getId().intValue()).findFirst().orElse(null);

			if (clienteEncontrado == null) {
				throw new MsoClientesExceptionAPI(HttpStatus.NOT_FOUND, util.obtenerCodigoResponse(),
						Constantes.OPERACION_404, util.obtenerFoliador(), Arrays.asList(Constantes.OPERACION_404));
			}

			ClientesModel clienteBean = new ClientesModel();
			clienteBean.setId(clienteModel.getId());
			clienteBean.setNombre(clienteModel.getNombre());
			clienteBean.setCorreo(clienteModel.getCorreo());

			for (int i = 0; i < clientes.size(); i++) {
				if (clientes.get(i).getId() == clienteModel.getId()) {
					clientes.get(i).setNombre(clienteModel.getNombre());
					clientes.get(i).setCorreo(clienteModel.getCorreo());
					continue;
				}
			}

			if (!clientes.isEmpty()) {
				objectMapper.writeValue(myObj, clientes);
			}

			ResponseListClientes responseCliente = new ResponseListClientes();
			responseCliente.setClientes(Arrays.asList(clienteBean));
			return responseCliente;

		} catch (Exception ex) {
			throw new MsoClientesExceptionAPI(HttpStatus.INTERNAL_SERVER_ERROR, util.obtenerCodigoResponse(),
					Constantes.OPERACION_500, util.obtenerFoliador(), Arrays.asList(Constantes.OPERACION_500));
		}

	}

	@Override
	public Object deleteCliente(String nombre) throws Exception {

		boolean respOk = false;
		ObjectMapper objectMapper = new ObjectMapper();

		objectMapper.configure(Feature.AUTO_CLOSE_SOURCE, true);
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {

			File myObj = new File(clientesData);

			if (!myObj.exists()) {
				if (myObj.createNewFile()) {
					System.out.println("File created: " + myObj.getName());
					objectMapper.writeValue(myObj, new ArrayList<>());
				}
			}

			List<ClientesModel> clientes = Arrays.asList(objectMapper.readValue(myObj, ClientesModel[].class));

			List<ClientesModel> clientesSecundario = new ArrayList<>();

			clientes.forEach(c -> {
				if (!clientesSecundario.contains(c)) {
					clientesSecundario.add(c);
				}
			});

			if (clientes.isEmpty()) {
				throw new MsoClientesExceptionAPI(HttpStatus.NOT_FOUND, util.obtenerCodigoResponse(),
						Constantes.OPERACION_404, util.obtenerFoliador(), Arrays.asList(Constantes.OPERACION_404));
			}

			for (int i = 0; i < clientesSecundario.size(); i++) {
				if (clientesSecundario.get(i).getNombre().trim().contains(nombre.trim())) {
					clientesSecundario.remove(i);
					respOk = true;
				}
			}

			if (!clientesSecundario.isEmpty()) {
				objectMapper.writeValue(myObj, clientesSecundario);
			}
			return respOk;

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new MsoClientesExceptionAPI(HttpStatus.INTERNAL_SERVER_ERROR, util.obtenerCodigoResponse(),
					Constantes.OPERACION_500, util.obtenerFoliador(), Arrays.asList(Constantes.OPERACION_500));
		}

	}

}
