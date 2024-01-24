package com.example.backend.interfaces;

import com.example.backend.DTO.ScholarDTO;
import com.example.backend.entities.Scholar;

import java.util.List;


public interface ScholarService {

    List<ScholarDTO> getAllScholars();

    Scholar findScholarById(Long id);

    void saveScholar(ScholarDTO scholarDTO);

    Scholar updateScholar(Long id, ScholarDTO scholarDTO);

    void removeScholar(Long id);
}
