package com.esante.service;

import com.esante.domain.PatientEntity;
import com.esante.repository.PatientEntityRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PatientEntityService {

    @Inject
    PatientEntityRepository patientRepository;

    @Transactional
    public PatientEntity createPatient(PatientEntity patientEntity) {
        patientRepository.persist(patientEntity);
        return patientEntity;
    }

    public Optional<PatientEntity> findPatientById(Long id) {
        return patientRepository.findByIdOptional(id);
    }

    @Transactional
    public boolean deletePatient(Long id) {
        return patientRepository.deleteById(id);
    }

    public List<PatientEntity> findAll() {
        return patientRepository.listAll();
    }
}
