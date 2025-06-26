package com.example.kyc.feign.client;

import com.example.kyc.dto.AadharOtpRequestDTO;
import com.example.kyc.dto.AadharOtpResponseDto;
import com.example.kyc.dto.OtpVerifyRequestDTO;
import com.example.kyc.dto.OtpVerifyResponseDTO;
import com.example.kyc.feign.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "aadharOtpClient", url = "https://uat.paysprint.in/sprintverify-uat/api", configuration = FeignClientConfig.class)
public interface AadharOtpClient {
    @PostMapping("/v1/verification/aadhaar_sendotp")
    AadharOtpResponseDto generateOtp(@RequestBody AadharOtpRequestDTO requestDTO);

    @PostMapping("/v1/verification/aadhaar_verifyotp")
    OtpVerifyResponseDTO verifyOtp(@RequestBody OtpVerifyRequestDTO verifyRequestDTO);
}