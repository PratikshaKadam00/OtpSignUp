package com.app.dto;

import lombok.Data;

@Data
public class MobileNoOTPRequestDto {
	private String phoneNumber; //destination
	private String oneTimePassword;
}
