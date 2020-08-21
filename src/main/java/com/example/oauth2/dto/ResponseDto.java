package com.example.oauth2.dto;

import org.springframework.http.HttpStatus;

public class ResponseDto {

	private String action;
	private String result;
	private String message;
	private Object values;
	private HttpStatus status;
	
	public ResponseDto(){
		
	}
		
	public ResponseDto(String action, String result, String message, Object values, HttpStatus status) {
		super();
		this.action = action;
		this.result = result;
		this.message = message;
		this.values = values;
		this.status = status;
	}

	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getValues() {
		return values;
	}
	public void setValues(Object values) {
		this.values = values;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "[action=" + action + ","+System.lineSeparator() +"result=" + result + ","+System.lineSeparator() +" message=" + message + ","+System.lineSeparator() +" values=" + values.toString()
				+ ","+System.lineSeparator() +" status=" + status + "]";
	}
	
	
	
}
