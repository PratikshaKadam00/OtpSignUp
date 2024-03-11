package com.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.app.dto.MobileNoOTPRequestDto;

public interface UserController {

	 ResponseEntity<Object> sendOTP( MobileNoOTPRequestDto otpRequestDTO);
	 ResponseEntity<Object> validateOTP( MobileNoOTPRequestDto otpRequestDto);
}
 