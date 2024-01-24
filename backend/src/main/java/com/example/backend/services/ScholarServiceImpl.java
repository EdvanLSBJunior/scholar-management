package com.example.backend.services;

import com.example.backend.DTO.ScholarDTO;
import com.example.backend.entities.Scholar;
import com.example.backend.interfaces.ScholarService;
import com.example.backend.repository.ScholarRepository;
import com.example.backend.services.exception.RequiredFieldMissingException;
import com.example.backend.util.ScholarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend.exceptions.NotFoundException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ScholarServiceImpl implements ScholarService {

    @Autowired
    private ScholarRepository repository;

    @Autowired
    private ScholarMapper mapper;

    @Override
    public List<ScholarDTO> getAllScholars() {
        List<Scholar> scholarList = repository.findAll();
        List<ScholarDTO> scholarDTOList = scholarList.stream()
                .map(scholar -> new ScholarDTO(scholar))
                .collect(Collectors.toList());
        return scholarDTOList;
    }

    @Override
    public Scholar findScholarById(Long id) {
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional
    public void saveScholar(ScholarDTO scholarDTO) {
        validateFields(scholarDTO);
        scholarDTO.setRegistrationDate(LocalDateTime.now().toString());
        Scholar scholar = mapper.convertToScholar(scholarDTO);
        repository.save(scholar);
    }

    @Override
    public Scholar updateScholar(Long id, ScholarDTO scholarDTO) {
        Scholar existingScholar = repository.findById(id).orElseThrow(NotFoundException::new);

        existingScholar.setScholarName(scholarDTO.getScholarName());
        existingScholar.setDocumentType(scholarDTO.getDocumentType());
        existingScholar.setDocumentNumber(scholarDTO.getDocumentNumber());
        existingScholar.setCodePayment(scholarDTO.getCodePayment());
        existingScholar.setAgencyNumber(scholarDTO.getAgencyNumber());
        existingScholar.setAccountNumber(scholarDTO.getAccountNumber());

        return repository.save(existingScholar);
    }

    @Override
    public void removeScholar(Long id) {
        Scholar scholar = repository.findById(id).orElseThrow(NotFoundException::new);

        repository.delete(scholar);
    }

    private void validateFields(ScholarDTO scholarDTO) {

        if (scholarDTO.getScholarName() == null || scholarDTO.getScholarName().trim().equals("")) {
            throw new RequiredFieldMissingException("E necessario informar o nome do bolsista!");
        }
        if (scholarDTO.getDocumentType() == null) {
            throw new RequiredFieldMissingException("E necessario informar o tipo de documento!");
        }
        if (scholarDTO.getDocumentNumber() == null) {
            throw new RequiredFieldMissingException("E necessario informar o numero do documento");
        }
        if (scholarDTO.getCodePayment() <= 0) {
            throw new RequiredFieldMissingException("E necessario informar qual o banco!");
        }
        if (scholarDTO.getAgencyNumber() == null) {
            throw new RequiredFieldMissingException("E necessario informar a agencia!");
        }
        if (scholarDTO.getAccountNumber() == null) {
            throw new RequiredFieldMissingException("E necessario informar a conta!");
        }
    }
}
