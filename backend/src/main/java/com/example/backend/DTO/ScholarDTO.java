package com.example.backend.DTO;

import com.example.backend.entities.Scholar;
import com.example.backend.enums.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScholarDTO {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScholarDTO.class);

    private Long id;

    @NotBlank(message = "O nome do bolsista não pode ser vazio.")
    private String scholarName;

    @NotNull(message = "O tipo de documento do bolsista é obrigatório.")
    private DocumentType documentType;

    @NotNull(message = "O numero do documento do bolsista é obrigatório.")
    private BigInteger documentNumber;

    private String registrationDate;

    @NotNull(message = "O codigo do banco é obrigatório.")
    private int codePayment;

    @NotNull(message = "O numero da agencia não pode ser vazio.")
    private String agencyNumber;
    @NotNull(message = "O nome do bolsista não pode ser vazio.")
    private String accountNumber;

    public ScholarDTO(Scholar scholar) {
        this.id = scholar.getId();
        this.scholarName =  scholar.getScholarName();
        this.accountNumber = scholar.getAccountNumber();
        this.agencyNumber = scholar.getAgencyNumber();
        this.codePayment = scholar.getCodePayment();
        this.documentNumber = scholar.getDocumentNumber();
        this.documentType = scholar.getDocumentType();
        this.registrationDate = scholar.getRegistrationDate().toString();
    }
}
