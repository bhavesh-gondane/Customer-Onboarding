package com.example.kyc.repository;

import com.example.kyc.entity.AadharDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AadharRepository extends JpaRepository<AadharDetails,Long> {
    boolean existsByLast4Aadhar(String last4);
}