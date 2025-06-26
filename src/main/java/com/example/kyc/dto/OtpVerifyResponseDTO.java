package com.example.kyc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OtpVerifyResponseDTO {
    @JsonProperty("data")
    private AadhaarUserData data;

    @Data
    public static class AadhaarUserData {
        @JsonProperty("full_name")
        private String fullName;
        @JsonProperty("aadhaar_number")
        private String aadhaarNumber;
        private String dob;
        private String gender;
    }
}
