package com.example.kyc.controller;

import com.example.kyc.dto.AadharOtpRequestDTO;
import com.example.kyc.dto.AadharOtpResponseDto;
import com.example.kyc.dto.VerifyOtoRequestDto;
import com.example.kyc.service.AadharService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aadhar/check")
@AllArgsConstructor
//@Validated
public class AadharController {
    private AadharService aadharService;

    @PostMapping("/sendOtp")
    public ResponseEntity<AadharOtpResponseDto> sendOtp(@RequestBody @Valid AadharOtpRequestDTO requestDTO){
        AadharOtpResponseDto responseDto= aadharService.sendOtp(requestDTO.getAadharNo());
        return ResponseEntity.ok(responseDto);
    }
    @PostMapping("/verifyOtp")
    public ResponseEntity<String> verifyOtp(@RequestBody VerifyOtoRequestDto verifyOtoRequestDto){
        aadharService.verifyOtp(verifyOtoRequestDto.getClientId(),verifyOtoRequestDto.getOtp());
        return ResponseEntity.ok("Otp Validated and Data saved");
    }
}
