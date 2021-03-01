package com.maxley.jediorder.exceptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.maxley.jediorder.exception.DomainException;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(DomainException.class)
	public ResponseEntity<Object> handleDomainException(DomainException ex, WebRequest request){
		
		ExceptionProperty exceptionProperty = new ExceptionProperty();
		
		var status = HttpStatus.NOT_FOUND;
		exceptionProperty.setStatus(status.value());
		exceptionProperty.setTitle(ex.getMessage());
		exceptionProperty.setDateTime(LocalDateTime.now());
		
		return handleExceptionInternal(ex, exceptionProperty, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ArrayList<ExceptionPropertyField> fields = new ArrayList<ExceptionPropertyField>();
		
		for(ObjectError error: ex.getBindingResult().getAllErrors()) {
			String fieldName = ((FieldError) error).getField();
			String fieldMessage = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			
			ExceptionPropertyField field = new ExceptionPropertyField(fieldName, fieldMessage);
			fields.add(field);
		}
		
		ExceptionProperty exceptionProperty = new ExceptionProperty();
		exceptionProperty.setStatus(status.value());
		exceptionProperty.setTitle("Invalid Fields");
		exceptionProperty.setDateTime(LocalDateTime.now());
		exceptionProperty.setFields(fields);
		
		return handleExceptionInternal(ex, exceptionProperty, headers, status, request);
	}
}