package com.example.kyc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AadharOtpRequestDTO {
    @Pattern(regexp = "^[2-9]{1}[0-9]{11}$", message = "ENter valid aadhar number")
    @JsonProperty("id_number")
    private String aadharNo;
}
