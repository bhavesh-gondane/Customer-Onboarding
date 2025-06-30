package com.example.kyc.service;

import com.example.kyc.dto.AadharOtpRequestDTO;
import com.example.kyc.dto.AadharOtpResponseDto;
import com.example.kyc.dto.OtpVerifyRequestDTO;
import com.example.kyc.dto.OtpVerifyResponseDTO;
import com.example.kyc.entity.AadharDetails;
import com.example.kyc.feign.client.AadharOtpClient;
import com.example.kyc.repository.AadharRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class AadharService {
    private AadharRepository aadharRepository;
    private AadharOtpClient aadharOtpClient;

    Map<String,String> aadharClient= new HashMap<>();

    @Transactional
    public AadharOtpResponseDto sendOtp(String aadhar){
        String last4Digits=aadhar.substring(8);
        if(aadharRepository.existsByLast4Aadhar(last4Digits)){
            throw new RuntimeException("Aadhar already exists");
        }
        AadharOtpRequestDTO requestDTO = new AadharOtpRequestDTO();
        requestDTO.setAadharNo(aadhar);
        AadharOtpResponseDto responseDto=aadharOtpClient.generateOtp(requestDTO);
//        log.info(responseDto.getMessage());
        if(responseDto.getData()==null){
            log.info("data is coming null");
//            throw new RuntimeException("response data is null");
        }
        aadharClient.put(responseDto.getData().getClientId(),aadhar);
        return responseDto;
    }

    @Transactional
    public ResponseEntity<?> verifyOtp(String clientId, String otp) {
        String aadhar = aadharClient.get(clientId);
        OtpVerifyRequestDTO requestDTO = new OtpVerifyRequestDTO();
        requestDTO.setOtp(otp);
        requestDTO.setClientId(clientId);

        OtpVerifyResponseDTO responseDTO = aadharOtpClient.verifyOtp(requestDTO);

        AadharDetails saveDetails = new AadharDetails();
        saveDetails.setName(responseDTO.getData().getFullName());
        saveDetails.setDob(responseDTO.getData().getDob());
        saveDetails.setGender(responseDTO.getData().getGender());
        saveDetails.setLast4Aadhar(aadhar.substring(aadhar.length() - 4));

        AadharDetails saved = aadharRepository.save(saveDetails);
        aadharClient.remove(clientId);

        return ResponseEntity.ok(saved);
    }

}
