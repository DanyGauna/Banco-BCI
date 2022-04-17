package com.bbci.createuser.dtos;

import lombok.Data;

@Data
public class PhoneRequest {
	
	private Long number;
	private Integer cityCode;
	private String countryCode;

}
