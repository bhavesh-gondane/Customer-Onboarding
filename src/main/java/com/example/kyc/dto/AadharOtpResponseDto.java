package com.example.kyc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class AadharOtpResponseDto {
    private int statuscode;
    private boolean status;
    private String message;
    private Data data;

    @lombok.Data
    public static class Data {
        @JsonProperty("client_id")
        private String clientId;
        @JsonProperty("otp_sent")
        private boolean otpSent;
        @JsonProperty("if_number")
        private boolean ifNumber;
        @JsonProperty("valid_aadhaar")
        private boolean validAadhar;
    }
}
