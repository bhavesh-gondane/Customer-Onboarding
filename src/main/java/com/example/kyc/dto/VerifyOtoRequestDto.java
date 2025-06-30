package com.example.kyc.dto;

import lombok.Data;

@Data
public class VerifyOtoRequestDto {
    private String clientId;
    private String otp;
}
