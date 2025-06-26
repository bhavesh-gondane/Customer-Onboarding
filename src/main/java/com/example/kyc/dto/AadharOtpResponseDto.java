package com.example.kyc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class AadharOtpResponseDto {
    private Data data;

    @lombok.Data
    public static class Data {
        @JsonProperty("client_id")
        private String clientId;
    }
}
