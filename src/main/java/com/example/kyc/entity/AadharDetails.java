package com.example.kyc.entity;

import com.example.kyc.domain.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AadharDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String last4Aadhar;
    private String name;
//    @Temporal(TemporalType.DATE)
    private String dob;
//    @Enumerated(EnumType.STRING)
    private String gender;
}
