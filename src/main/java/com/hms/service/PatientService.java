package com.hms.service;

import com.hms.model.Patient;
import com.hms.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Transactional
    public Patient createPatient(Patient patient) {
        if (patientRepository.findByEmail(patient.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
        return patientRepository.save(patient);
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + id));
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public List<Patient> getActivePatients() {
        return patientRepository.findByIsActive(true);
    }

    @Transactional
    public Patient updatePatient(Long id, Patient patientDetails) {
        Patient patient = getPatientById(id);
        
        patient.setFirstName(patientDetails.getFirstName());
        patient.setLastName(patientDetails.getLastName());
        patient.setEmail(patientDetails.getEmail());
        patient.setPhoneNumber(patientDetails.getPhoneNumber());
        patient.setDateOfBirth(patientDetails.getDateOfBirth());
        patient.setAddress(patientDetails.getAddress());
        patient.setMedicalHistory(patientDetails.getMedicalHistory());
        patient.setBloodGroup(patientDetails.getBloodGroup());
        
        return patientRepository.save(patient);
    }

    @Transactional
    public void deletePatient(Long id) {
        Patient patient = getPatientById(id);
        patient.setActive(false);
        patientRepository.save(patient);
    }

    public List<Patient> searchPatients(String query) {
        return patientRepository.findByFirstNameContainingOrLastNameContaining(query, query);
    }
} 