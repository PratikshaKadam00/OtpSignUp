package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Exception.ApiException;
import com.app.Exception.InvalidOTPException;
import com.app.Exception.MobileNoNotProvidedException;
import com.app.dto.MobileNoOTPRequestDto;
import com.app.dto.MobileNoOTPResponseDto;
import com.app.dto.ResponseStatus;
import com.app.enums.OtpStatus;
import com.app.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserControllerImpl {
	
	@Autowired
	public UserService userService;
	
	@PostMapping("/sendOTP")
	private ResponseEntity<Object> sendOTP(@RequestBody MobileNoOTPRequestDto otpRequestDTO)
	{
		int status=200;
	    try {
		MobileNoOTPResponseDto responseDto=userService.sendOTPForVerification(otpRequestDTO);
		return ResponseEntity.status(ResponseStatus.OK).body(responseDto);
		
		}catch (MobileNoNotProvidedException ex){
			status=ResponseStatus.MISSING_DATA;
			ex.printStackTrace();
			return ResponseEntity.status(status).body(ex.getMessage());
			
		}catch(ApiException ex)
	    {
			status=ResponseStatus.BAD_REQUEST;
			ex.printStackTrace();
			return ResponseEntity.status(status).body(OtpStatus.FAILED+ex.getMessage());
	    }
	    catch(Exception ex)
	    {
			status=ResponseStatus.BAD_REQUEST;
			ex.printStackTrace();
			return ResponseEntity.status(status).body(ex.getMessage());
	    }
	}
	
	@PostMapping("/validateOTP")
	private ResponseEntity<Object> validateOTP(@RequestBody MobileNoOTPRequestDto otpRequestDto)
	{
		int status=200;
		try {
			return ResponseEntity.status(ResponseStatus.OK).body(userService.validateOTP(otpRequestDto));
		}catch(InvalidOTPException ex) {
			status=ResponseStatus.UNAUTHORISED;
			ex.printStackTrace();
			return ResponseEntity.status(status).body(ex.getMessage());
		}catch(MobileNoNotProvidedException ex) {
			status=ResponseStatus.MISSING_DATA;
			ex.printStackTrace();
			return ResponseEntity.status(status).body(ex.getMessage());
		}catch(Exception ex)
	    {
			status=ResponseStatus.BAD_REQUEST;
			ex.printStackTrace();
			return ResponseEntity.status(status).body(ex.getMessage());
	    }
		
	}

}
