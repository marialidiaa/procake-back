package com.procake.exceptions.handler;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.procake.exceptions.DadosInvalidosException;
import com.procake.exceptions.EmailException;
import com.procake.exceptions.OperacaoInvalidaException;
import com.procake.exceptions.RecursoNaoEncontradoException;
import com.procake.exceptions.ErroPadrao;
import com.procake.exceptions.ErroPadraoValidations;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@ControllerAdvice
public class ResponseEntityCustomizadaExcpetionHandler extends ResponseEntityExceptionHandler
		implements AccessDeniedHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		String error = "Dados inválidos.";
		List<String> errorList = ex.getBindingResult().getFieldErrors().stream()
				.map(fieldError -> fieldError.getDefaultMessage()).collect(Collectors.toList());
		ErroPadraoValidations err = new ErroPadraoValidations(Instant.now(), status.value(), error, errorList,
				((ServletWebRequest) request).getRequest().getRequestURI().toString());

		return ResponseEntity.status(status).body(err);
	}

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {

		String route = "/acess-denied" + request.getRequestURI();
		System.out.println(route);
		response.sendRedirect(route);
	}

//	@ExceptionHandler(Exception.class)
//	public final ResponseEntity<StandardError> handleAllExceptions(Exception e, HttpServletRequest request) {
//
//		String error = "General error, see log for more details";
//		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
//		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
//				request.getRequestURI());
//
//		return ResponseEntity.status(status).body(err);
//	}

	@ExceptionHandler(RecursoNaoEncontradoException.class)
	public ResponseEntity<ErroPadrao> recursoNaoEncontrado(RecursoNaoEncontradoException e, HttpServletRequest request) {

		String error = "Recurso não encontrado.";
		HttpStatus status = HttpStatus.NOT_FOUND;
		ErroPadrao err = new ErroPadrao(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());

		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErroPadrao> sqlIntegridadeViolada(DataIntegrityViolationException e,
			HttpServletRequest request) {

		String error = "Erro nos dados inseridos, valor já cadastrado no banco.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String errors = e.getMostSpecificCause().getLocalizedMessage().substring(
				e.getMostSpecificCause().getLocalizedMessage().indexOf("Detail") + 8,
				e.getMostSpecificCause().getLocalizedMessage().length());

		ErroPadrao err = new ErroPadrao(Instant.now(), status.value(), error, errors, request.getRequestURI());

		return ResponseEntity.status(status).body(err);

	}

	@ExceptionHandler(OperacaoInvalidaException.class)
	public ResponseEntity<ErroPadrao> operacaoInvalida(OperacaoInvalidaException e, HttpServletRequest request) {

		String error = "Operação invalida";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ErroPadrao err = new ErroPadrao(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());

		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(DisabledException.class)
	public ResponseEntity<ErroPadrao> usuarioDesativado(DisabledException e, HttpServletRequest request) {

		String error = "Usuário ou senha inválidos.";
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		ErroPadrao err = new ErroPadrao(Instant.now(), status.value(), error, "Username or password incorrect",
				request.getRequestURI());

		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ErroPadrao> badCredentials(BadCredentialsException e, HttpServletRequest request) {

		String error = "Usuário ou senha inválidos.";
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		ErroPadrao err = new ErroPadrao(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());

		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(DadosInvalidosException.class)
	public ResponseEntity<ErroPadrao> invalidData(DadosInvalidosException e, HttpServletRequest request) {

		String error = "Operação invalida, o usuário não é o mesmo da alteração.";
		HttpStatus status = HttpStatus.FORBIDDEN;
		ErroPadrao err = new ErroPadrao(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());

		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(EmailException.class)
	public ResponseEntity<ErroPadrao> invalidData(EmailException e, HttpServletRequest request) {

		String error = "Erro ao enviar e-mail.";
		HttpStatus status = HttpStatus.FORBIDDEN;
		ErroPadrao err = new ErroPadrao(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());

		return ResponseEntity.status(status).body(err);
	}
}