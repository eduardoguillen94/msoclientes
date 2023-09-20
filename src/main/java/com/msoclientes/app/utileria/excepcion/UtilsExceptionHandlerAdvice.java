package com.msoclientes.app.utileria.excepcion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import com.msoclientes.app.common.Constantes;
import com.msoclientes.app.common.Util;
import com.msoclientes.app.utileria.excepcion.model.ErrorDescription;
import com.msoclientes.app.utileria.excepcion.model.ResponseError;

import lombok.RequiredArgsConstructor;

import com.msoclientes.app.utileria.excepcion.model.MsoClientesExceptionAPI;

@RestControllerAdvice
public class UtilsExceptionHandlerAdvice {

	@Autowired
	private Util util;

	@Value("${info.app.response}")
	private String info;

	@ExceptionHandler(MsoClientesExceptionAPI.class)
	protected ResponseEntity<ResponseError> handleSEExceptionAPI(MsoClientesExceptionAPI e, final WebRequest request) {
		return new ResponseEntity<>(
				new ResponseError(util.getFormatoCodex(String.valueOf(e.getStatus().value())), e.getMensaje(),
						e.getFolio(), info.concat(String.valueOf(e.getStatus().value())), e.getDetalles()),
				e.getStatus());

	}

	@ExceptionHandler({ Exception.class, IllegalArgumentException.class, RuntimeException.class })
	protected ResponseEntity<ResponseError> handleAllUncaughtException(Exception exception, final WebRequest request) {
		return new ResponseEntity<>(
				new ResponseError(util.getFormatoCodex(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value())),
						Constantes.OPERACION_500, util.obtenerFoliador(),
						info.concat(util.getFormatoCodex(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))),
						Arrays.asList(Constantes.OPERACION_400_DETAIL)),
				HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(MissingPathVariableException.class)
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex,
			final WebRequest request) {
		return new ResponseEntity<>(
				new ResponseError(util.getFormatoCodex(Constantes.PREFIX_CODE_400), Constantes.OPERACION_400,
						util.obtenerFoliador(), info.concat(util.getFormatoCodex(Constantes.PREFIX_CODE_400)),
						Arrays.asList(Constantes.OPERACION_400_DETAIL)),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			final WebRequest request) {
		return new ResponseEntity<>(
				new ResponseError(util.getFormatoCodex(Constantes.PREFIX_CODE_400), Constantes.OPERACION_400,
						util.obtenerFoliador(), info.concat(util.getFormatoCodex(Constantes.PREFIX_CODE_400)),
						Arrays.asList(Constantes.OPERACION_400_DETAIL)),
				HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(NullPointerException.class)
	protected ResponseEntity<ResponseError> handleNullFormat(NullPointerException ex, final WebRequest request) {
		return new ResponseEntity<>(
				new ResponseError(util.getFormatoCodex(Constantes.PREFIX_CODE_400), Constantes.OPERACION_400,
						util.obtenerFoliador(), info.concat(util.getFormatoCodex(Constantes.PREFIX_CODE_400)),
						Arrays.asList(Constantes.OPERACION_400_DETAIL)),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NumberFormatException.class)
	protected ResponseEntity<ResponseError> handleNumberFormat(NumberFormatException ex, final WebRequest request) {
		return new ResponseEntity<>(
				new ResponseError(util.getFormatoCodex(Constantes.PREFIX_CODE_400), Constantes.OPERACION_400,
						util.obtenerFoliador(), info.concat(util.getFormatoCodex(Constantes.PREFIX_CODE_400)),
						Arrays.asList(Constantes.OPERACION_400_DETAIL)),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MissingRequestHeaderException.class)
	protected ResponseEntity<ResponseError> handleMissingParams(MissingRequestHeaderException ex,
			final WebRequest request) {
		return new ResponseEntity<>(
				new ResponseError(util.getFormatoCodex(Constantes.PREFIX_CODE_400), Constantes.OPERACION_400,
						util.obtenerFoliador(), info.concat(util.getFormatoCodex(Constantes.PREFIX_CODE_400)),
						Arrays.asList(Constantes.OPERACION_400_DETAIL)),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<ResponseError> handleMethodNotValid(MethodArgumentNotValidException ex,
			final WebRequest request) {
		List<FieldError> errors = ex.getBindingResult().getFieldErrors();
		List<ErrorDescription> listaErrores = new ArrayList<>();
		for (FieldError error : errors) {
			listaErrores.add(new ErrorDescription(error.getField(), Arrays.asList(error.getDefaultMessage())));
		}
		listaErrores = listaErrores.stream().distinct().collect(Collectors.toList());
		return new ResponseEntity<>(new ResponseError(util.getFormatoCodex(Constantes.PREFIX_CODE_400),
				Constantes.OPERACION_400, util.obtenerFoliador(),
				info.concat(util.getFormatoCodex(Constantes.PREFIX_CODE_400)), listaErrores), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<ResponseError> handleConstraintValidation(ConstraintViolationException ex,
			final WebRequest request) {
		Set<ConstraintViolation<?>> listaConstraints = ex.getConstraintViolations();
		List<String> listaErrores = new ArrayList<>();
		for (ConstraintViolation<?> constraintViolation : listaConstraints) {
			listaErrores.add(constraintViolation.getMessage());
		}
		return new ResponseEntity<>(new ResponseError(util.getFormatoCodex(Constantes.PREFIX_CODE_400),
				Constantes.OPERACION_400, util.obtenerFoliador(),
				info.concat(util.getFormatoCodex(Constantes.PREFIX_CODE_400)), listaErrores), HttpStatus.BAD_REQUEST);
	}

}