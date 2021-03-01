package com.maxley.jediorder.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ExceptionProperty {

	private String title;
	private Integer status;
	private LocalDateTime dateTime;
	private List<ExceptionPropertyField> fields;
	
	public ExceptionProperty() { }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public List<ExceptionPropertyField> getFields() {
		return fields;
	}

	public void setFields(List<ExceptionPropertyField> fields) {
		this.fields = fields;
	}	
	
}
