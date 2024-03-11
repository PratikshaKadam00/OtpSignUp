package com.app.service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.app.Exception.ApiException;
import com.app.Exception.InvalidOTPException;
import com.app.Exception.MobileNoNotProvidedException;
import com.app.config.TwilioConfig;
import com.app.dto.MobileNoOTPRequestDto;
import com.app.dto.MobileNoOTPResponseDto;
import com.app.enums.OtpStatus;
import com.app.model.User;
import com.app.repository.UserRepo;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class UserServiceImpl implements UserService{
	
    @Autowired
	private TwilioConfig twilioConfig;
    
    @Autowired
    private UserRepo userRepo;
    
  
    
    Map<String, String> otpMap=new HashMap<>();
	@Override
	public MobileNoOTPResponseDto sendOTPForVerification(MobileNoOTPRequestDto mobileNoOTPRequestDto) throws MobileNoNotProvidedException, ApiException {
		
		if(mobileNoOTPRequestDto.getPhoneNumber()==null) {
			throw new MobileNoNotProvidedException("Please enter valid mobile number");
		}
		
		try {
		PhoneNumber to=new PhoneNumber(mobileNoOTPRequestDto.getPhoneNumber());
		PhoneNumber from=new PhoneNumber(twilioConfig.getTrialNumber());
		
		String otp=generateOTP();
		if(otp!=null) {
			otpMap.put(to.toString(), otp);
		}
		
		String otpMessage="You OTP is "+otp+" use this to complete the verification";
		Message message=Message.creator(to, from, otpMessage ).create();
		
		return new MobileNoOTPResponseDto(OtpStatus.DELIVIRED, otpMessage);
		
		}catch(MobileNoNotProvidedException ex)
		{
			ex.printStackTrace();
			throw new MobileNoNotProvidedException(ex.getMessage());
		}
	}

	@Override
	public String generateOTP() {
		
		return new DecimalFormat("000000").format(new Random().nextInt(999999));
	}

	public String validateOTP(MobileNoOTPRequestDto otpRequestDto) throws InvalidOTPException , MobileNoNotProvidedException
	{
		if(otpRequestDto.getPhoneNumber()==null) {
			throw new MobileNoNotProvidedException("Please enter valid mobile number");
		}
		
		if(otpRequestDto.getOneTimePassword()==null)
			throw new InvalidOTPException("Please enter OTP");
		
		try {
		    if(otpRequestDto.getOneTimePassword().equals(otpMap.get(otpRequestDto.getPhoneNumber()))){
		    	User user=new User();
		    	user.setMobileNo(otpRequestDto.getPhoneNumber().toString());
		    	userRepo.save(user);
			    otpMap.remove(otpRequestDto.getPhoneNumber());	

		    }
		    else{
		  	     throw new InvalidOTPException("Please enter Valid OTP");
		    }
		   }catch(Exception e){
		    	e.printStackTrace();
		    	throw new InvalidOTPException(e.getMessage());
		   }
		
		
		return "Validation Successful proceed further";
			
	}
}
