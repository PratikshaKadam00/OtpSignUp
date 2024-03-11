package com.app.dto;

import com.app.enums.OtpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MobileNoOTPResponseDto {
	private OtpStatus status;
	private String message;

}
