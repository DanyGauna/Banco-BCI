package com.bbci.createuser.errors;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ErrorResponse {
	
	private LocalDateTime fecha;
	private Integer codigo;
	private String detail;
	
	public ErrorResponse(Integer codigo, String detail) {
		super();
		this.fecha = LocalDateTime.now();
		this.codigo = codigo;
		this.detail = detail;
	}

}
