package com.example.kyc.controller;

import com.example.kyc.dto.AadharOtpRequestDTO;
import com.example.kyc.dto.VerifyOtoRequestDto;
import com.example.kyc.service.AadharService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aadhar/check")
@AllArgsConstructor
public class AadharController {
    private AadharService aadharService;

    @PostMapping("/sendOtp")
    public ResponseEntity<String> sendOtp(@RequestBody AadharOtpRequestDTO requestDTO){
        aadharService.sendOtp(requestDTO.getId_number());
        return ResponseEntity.ok("Otp sent");
    }

    @PostMapping("/verifyOtp")
    public ResponseEntity<String> verifyOtp(@RequestBody VerifyOtoRequestDto verifyOtoRequestDto){
        aadharService.verifyOtp(verifyOtoRequestDto.getAadharNo(), verifyOtoRequestDto.getOtp());
        return ResponseEntity.ok("Otp Validated and Data saved");
    }
}
