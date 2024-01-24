package com.example.backend.util;

import com.example.backend.DTO.ScholarDTO;
import com.example.backend.entities.Scholar;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ScholarMapper {

    public Scholar convertToScholar(ScholarDTO scholarDTO) {
        Scholar scholar = new Scholar();

        scholar.setScholarName(scholarDTO.getScholarName());
        scholar.setDocumentType(scholarDTO.getDocumentType());
        scholar.setDocumentNumber(scholarDTO.getDocumentNumber());
        scholar.setRegistrationDate(LocalDateTime.parse(scholarDTO.getRegistrationDate()));
        scholar.setCodePayment(scholarDTO.getCodePayment());
        scholar.setAgencyNumber(scholarDTO.getAgencyNumber());
        scholar.setAccountNumber(scholarDTO.getAccountNumber());

        return scholar;
    }
}
