package com.app.service;

import java.text.DecimalFormat;
import java.util.Random;

import com.app.Exception.ApiException;
import com.app.Exception.InvalidOTPException;
import com.app.Exception.MobileNoNotProvidedException;
import com.app.dto.MobileNoOTPRequestDto;
import com.app.dto.MobileNoOTPResponseDto;

public interface UserService {
	
	public MobileNoOTPResponseDto sendOTPForVerification(MobileNoOTPRequestDto mobileNpOTPRequestDto) throws MobileNoNotProvidedException,
	ApiException;

	public String generateOTP();
	
	public String validateOTP(MobileNoOTPRequestDto otpRequestDto) throws InvalidOTPException , MobileNoNotProvidedException;
}
