package com.example.backend.entities;

import com.example.backend.enums.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_scholars")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Scholar {

    private static final Logger LOGGER = LoggerFactory.getLogger(Scholar.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String scholarName;

    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    private BigInteger documentNumber;

    private LocalDateTime registrationDate;

    private int codePayment;

    private String agencyNumber;

    private String accountNumber;

}