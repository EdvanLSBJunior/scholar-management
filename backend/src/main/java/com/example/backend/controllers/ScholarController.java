package com.example.backend.controllers;

import com.example.backend.DTO.ScholarDTO;
import com.example.backend.entities.Scholar;
import com.example.backend.services.ScholarServiceImpl;
import com.example.backend.util.ScholarMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/scholars")
@CrossOrigin(origins = "*")
@Api(value = "API REST Scholars")
public class ScholarController {

    @Autowired
    private ScholarServiceImpl service;

    @Autowired
    private ScholarMapper scholarMapper;

    @GetMapping("/list")
    @ApiOperation(value = "list scholars")
    public ResponseEntity<List<ScholarDTO>> getAllScholars() {
        try {
            List<ScholarDTO> scholarList = service.getAllScholars();
            return ResponseEntity.status(HttpStatus.OK).body(scholarList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "get an scholar by id")
    public ResponseEntity<Scholar> findScholarById(@PathVariable Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("O ID do Scholar deve ser um número inteiro positivo." + id);
        }
        try {
            Scholar scholar = service.findScholarById(id);
            return ResponseEntity.status(HttpStatus.OK).body(scholar);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    @ApiOperation(value = "create a new scholar")
    public ResponseEntity<String> saveScholar(@Valid @RequestBody ScholarDTO scholarDTO) {
        try {
            service.saveScholar(scholarDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Criado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "update scholar by id")
    public ResponseEntity<String> updateScholar(@PathVariable Long id, @Valid @RequestBody ScholarDTO scholarDTO) {
        try {
            service.updateScholar(id, scholarDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Atualizado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "delete an scholar by id")
    public ResponseEntity<String> removeScholar(@PathVariable Long id) {
        try {
            service.removeScholar(id);
            return ResponseEntity.status(HttpStatus.OK).body("Removido com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
