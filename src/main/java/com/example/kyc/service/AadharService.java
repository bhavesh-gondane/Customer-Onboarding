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
    public void sendOtp(String aadhar){
        String last4Digits=aadhar.substring(8);
        if(aadharRepository.existsByLast4Aadhar(last4Digits)){
            throw new RuntimeException("Aadhar already exists");
        }
        AadharOtpRequestDTO requestDTO = new AadharOtpRequestDTO();
        requestDTO.setId_number(aadhar);
        AadharOtpResponseDto responseDto=aadharOtpClient.generateOtp(requestDTO);
        log.info(responseDto.getMessage());
        if(responseDto.getData()==null){
            log.info("data is coming null");
//            throw new RuntimeException("response data is null");
        }
        aadharClient.put(aadhar,responseDto.getData().getClientId());
    }

    @Transactional
    public void verifyOtp(String aadhar,String otp){
        String clientId=aadharClient.get(aadhar);
        if(clientId==null)
            throw new RuntimeException("clientId is null");
        OtpVerifyRequestDTO requestDTO=new OtpVerifyRequestDTO();
        requestDTO.setOtp(otp);
        requestDTO.setClientId(clientId);

        OtpVerifyResponseDTO responseDTO=aadharOtpClient.verifyOtp(requestDTO);

        AadharDetails saveDetails = new AadharDetails();
        saveDetails.setName(responseDTO.getData().getFullName());
        saveDetails.setDob(responseDTO.getData().getDob());
        saveDetails.setGender(responseDTO.getData().getGender());
        saveDetails.setLast4Aadhar(responseDTO.getData().getAadhaarNumber().substring(8));
        aadharRepository.save(saveDetails);
    }
}
