package com.example.kyc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OtpVerifyRequestDTO {
//    private String aadharNo;
    private String otp;
    @JsonProperty("client_id")
    private String clientId;
    private String refid="748374637";
}