package com.example.backend.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import com.example.backend.DTO.ScholarDTO;
import com.example.backend.controllers.ScholarController;
import com.example.backend.entities.Scholar;
import com.example.backend.enums.DocumentType;
import com.example.backend.exceptions.NotFoundException;
import com.example.backend.services.ScholarServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ScholarControllerTest {

    @InjectMocks
    private ScholarController scholarControllerMock;

    @Mock
    private ScholarServiceImpl scholarService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testReturnAllScholars_Success() {
        ScholarDTO dto = new ScholarDTO(1L, "Edvanced", DocumentType.RG, BigInteger.valueOf(322223322), "2024-01-17 11:00:59", 260, "0001", "006502");
        when(this.scholarService.getAllScholars()).thenReturn(Arrays.asList(dto));

        ResponseEntity response = scholarControllerMock.getAllScholars();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, ((List<ScholarDTO>) response.getBody()).size());
    }

    @Test
    public void testGetAllScholars_Exception() {
        when(scholarService.getAllScholars()).thenThrow(new RuntimeException("Test exception"));

        ResponseEntity<List<ScholarDTO>> responseEntity = scholarControllerMock.getAllScholars();

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    public void testFindScholarById_Success() {
        Long scholarId = 1L;
        Scholar mockScholar = new Scholar();
        when(scholarService.findScholarById(scholarId)).thenReturn(mockScholar);

        ResponseEntity<Scholar> responseEntity = scholarControllerMock.findScholarById(scholarId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockScholar, responseEntity.getBody());
    }

    @Test
    public void testFindScholarById_NotFound() {
        Long scholarId = 1L;
        when(scholarService.findScholarById(scholarId)).thenThrow(NotFoundException.class);

        ResponseEntity<Scholar> responseEntity = scholarControllerMock.findScholarById(scholarId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    public void testFindScholarById_InvalidId() {
        Long invalidScholarId = -1L;
        when(scholarService.findScholarById(invalidScholarId))
                .thenThrow(IllegalArgumentException.class);
        try {
            scholarControllerMock.findScholarById(invalidScholarId);
            fail("Deveria lançar IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("O ID do Scholar deve ser um número inteiro positivo." + invalidScholarId, e.getMessage());
        }
    }

    @Test
    public void testSaveScholar_Success() {
        ScholarDTO scholarDTO = new ScholarDTO();

        ResponseEntity<String> responseEntity = scholarControllerMock.saveScholar(scholarDTO);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Criado com sucesso", responseEntity.getBody());
    }

    @Test
    public void testSaveScholar_Failure() {
        ScholarDTO scholarDTO = new ScholarDTO();
        doThrow(new RuntimeException("Test exception")).when(scholarService).saveScholar(any(ScholarDTO.class));

        ResponseEntity<String> responseEntity = scholarControllerMock.saveScholar(scholarDTO);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    public void testRemoveScholar_Success() {
        Long validScholarId = 1L;
        doNothing().when(scholarService).removeScholar(validScholarId);

        ResponseEntity<String> responseEntity = scholarControllerMock.removeScholar(validScholarId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Removido com sucesso", responseEntity.getBody());
        verify(scholarService, times(1)).removeScholar(validScholarId);
    }

    @Test
    public void testRemoveScholar_NotFound() {
        Long invalidScholarId = -1L;
        doThrow(NotFoundException.class).when(scholarService).removeScholar(invalidScholarId);

        ResponseEntity<String> responseEntity = scholarControllerMock.removeScholar(invalidScholarId);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Nao encontrado", responseEntity.getBody());
        verify(scholarService, times(1)).removeScholar(invalidScholarId);
    }
}
