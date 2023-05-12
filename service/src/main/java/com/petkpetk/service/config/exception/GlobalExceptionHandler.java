package com.petkpetk.service.config.exception;

import static com.petkpetk.service.common.StatusCode.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(PetkpetkServerException.class)
	public String handleException(PetkpetkServerException petkpetkServerException, HttpServletRequest request, Model model) {
		log.error("STATUS CODE: {}, url: {}, message: {}", petkpetkServerException.getStatusCode(),
			request.getRequestURI(), petkpetkServerException.getDetailMessage(), petkpetkServerException);

		model.addAttribute("error",
			StatusResponse.of(petkpetkServerException.getStatusCode(), petkpetkServerException.getDetailMessage()));

		return "/error/error-page";
	}

	@ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class, MethodArgumentNotValidException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleBadRequest(Exception exception, HttpServletRequest request, Model model) {
		log.error("url: {}, message: {}", request.getRequestURI(), exception.getMessage(), exception);

		model.addAttribute("error", StatusResponse.of(INVALID_REQUEST, INVALID_REQUEST.getMessage()));

		return "/error/error-page";
	}

	@ExceptionHandler(Exception.class)
	public String handleException(Exception exception, HttpServletRequest request, Model model) {
		log.error("url: {}, message: {}", request.getRequestURI(), exception.getMessage(), exception);

		model.addAttribute("error", StatusResponse.of(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR.getMessage()));

		return "/error/error-page";
	}
}
