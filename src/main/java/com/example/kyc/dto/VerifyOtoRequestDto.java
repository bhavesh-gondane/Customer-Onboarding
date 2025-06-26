package com.example.kyc.dto;

import lombok.Data;

@Data
public class VerifyOtoRequestDto {
    private String aadharNo;
    private String otp;
}
