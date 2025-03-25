package com.hms.service;

import com.hms.model.Patient;
import com.hms.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    private Patient testPatient;

    @BeforeEach
    void setUp() {
        testPatient = new Patient();
        testPatient.setId(1L);
        testPatient.setFirstName("John");
        testPatient.setLastName("Doe");
        testPatient.setEmail("john.doe@example.com");
        testPatient.setPhoneNumber("1234567890");
        testPatient.setDateOfBirth(LocalDate.of(1990, 1, 1));
        testPatient.setAddress("123 Main St");
        testPatient.setBloodGroup("A+");
        testPatient.setActive(true);
    }

    @Test
    void createPatient_Success() {
        when(patientRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(patientRepository.save(any(Patient.class))).thenReturn(testPatient);

        Patient created = patientService.createPatient(testPatient);

        assertNotNull(created);
        assertEquals(testPatient.getFirstName(), created.getFirstName());
        assertEquals(testPatient.getLastName(), created.getLastName());
        verify(patientRepository).save(any(Patient.class));
    }

    @Test
    void getPatientById_Success() {
        when(patientRepository.findById(1L)).thenReturn(Optional.of(testPatient));

        Patient found = patientService.getPatientById(1L);

        assertNotNull(found);
        assertEquals(testPatient.getId(), found.getId());
    }

    @Test
    void getAllPatients_Success() {
        List<Patient> patients = Arrays.asList(testPatient);
        when(patientRepository.findAll()).thenReturn(patients);

        List<Patient> found = patientService.getAllPatients();

        assertNotNull(found);
        assertEquals(1, found.size());
    }

    @Test
    void updatePatient_Success() {
        when(patientRepository.findById(1L)).thenReturn(Optional.of(testPatient));
        when(patientRepository.save(any(Patient.class))).thenReturn(testPatient);

        Patient updated = patientService.updatePatient(1L, testPatient);

        assertNotNull(updated);
        verify(patientRepository).save(any(Patient.class));
    }

    @Test
    void deletePatient_Success() {
        when(patientRepository.findById(1L)).thenReturn(Optional.of(testPatient));
        when(patientRepository.save(any(Patient.class))).thenReturn(testPatient);

        patientService.deletePatient(1L);

        verify(patientRepository).save(any(Patient.class));
        assertFalse(testPatient.isActive());
    }
} 