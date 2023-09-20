package com.msoclientes.app.controller;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msoclientes.app.common.Constantes;
import com.msoclientes.app.common.Util;
import com.msoclientes.app.model.request.ClienteActualizaReqModel;
import com.msoclientes.app.model.request.ClientesRequestModel;
import com.msoclientes.app.service.ClientesService;
import com.msoclientes.app.utileria.excepcion.model.MsoClientesExceptionAPI;
import com.msoclientes.app.utileria.excepcion.model.ResponseMicroservice;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClientesRestController {

	private final Util util;

	private final ClientesService clientesService;

	@PostMapping("/notificaciones-altas")
	public Object setCliente(@RequestBody ClientesRequestModel request) {

		try {
			Object o = clientesService.setClientes(request);
			return new ResponseMicroservice(HttpStatus.OK, util.obtenerCodigoResponse(), Constantes.OPERACION_201,
					util.obtenerFoliador(), o);
		} catch (MsoClientesExceptionAPI e) {
			throw e;
		} catch (Exception exc) {
			MsoClientesExceptionAPI ex = new MsoClientesExceptionAPI(HttpStatus.INTERNAL_SERVER_ERROR,
					util.obtenerCodigoResponse(), Constantes.OPERACION_500, util.obtenerFoliador(),
					Arrays.asList(exc.getMessage()));
			throw ex;
		}
	}

	@GetMapping("/generales/busquedas")
	public Object getClientes() {

		try {
			Object o = clientesService.getClientes();
			return new ResponseMicroservice(HttpStatus.OK, util.obtenerCodigoResponse(), Constantes.OPERACION_200,
					util.obtenerFoliador(), o);
		} catch (MsoClientesExceptionAPI e) {
			throw e;
		} catch (Exception exc) {
			MsoClientesExceptionAPI ex = new MsoClientesExceptionAPI(HttpStatus.INTERNAL_SERVER_ERROR,
					util.obtenerCodigoResponse(), Constantes.OPERACION_500, util.obtenerFoliador(),
					Arrays.asList(exc.getMessage()));
			throw ex;
		}
	}

	@GetMapping("/detalles/busquedas/{nombre}")
	public Object getDetalleCliente(@PathVariable String nombre) {

		try {
			Object o = clientesService.getDetalleCliente(nombre);
			return new ResponseMicroservice(HttpStatus.OK, util.obtenerCodigoResponse(), Constantes.OPERACION_200,
					util.obtenerFoliador(), o);
		} catch (MsoClientesExceptionAPI e) {
			throw e;
		} catch (Exception exc) {
			MsoClientesExceptionAPI ex = new MsoClientesExceptionAPI(HttpStatus.INTERNAL_SERVER_ERROR,
					util.obtenerCodigoResponse(), Constantes.OPERACION_500, util.obtenerFoliador(),
					Arrays.asList(exc.getMessage()));
			throw ex;
		}
	}
	
	
	
	@PutMapping("/notificaciones-actualiza")
	public Object actualizaCliente(@RequestBody ClienteActualizaReqModel request) {

		try {
			Object o = clientesService.actualizaCliente(request);
			return new ResponseMicroservice(HttpStatus.OK, util.obtenerCodigoResponse(), Constantes.OPERACION_200,
					util.obtenerFoliador(), o);
		} catch (MsoClientesExceptionAPI e) {
			throw e;
		} catch (Exception exc) {
			MsoClientesExceptionAPI ex = new MsoClientesExceptionAPI(HttpStatus.INTERNAL_SERVER_ERROR,
					util.obtenerCodigoResponse(), Constantes.OPERACION_500, util.obtenerFoliador(),
					Arrays.asList(exc.getMessage()));
			throw ex;
		}
	}
	
	
	@DeleteMapping("/bajas/{nombre}")
	public Object deleteClienteByNombre(@PathVariable String nombre) {

		try {
			Object o = clientesService.deleteCliente(nombre);
			return new ResponseMicroservice(HttpStatus.OK, util.obtenerCodigoResponse(), Constantes.OPERACION_200,
					util.obtenerFoliador(), o);
		} catch (MsoClientesExceptionAPI e) {
			throw e;
		} catch (Exception exc) {
			MsoClientesExceptionAPI ex = new MsoClientesExceptionAPI(HttpStatus.INTERNAL_SERVER_ERROR,
					util.obtenerCodigoResponse(), Constantes.OPERACION_500, util.obtenerFoliador(),
					Arrays.asList(exc.getMessage()));
			throw ex;
		}
	}
	
}
