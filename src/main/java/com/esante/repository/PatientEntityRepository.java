package com.esante.repository;


import com.esante.domain.Patient;
import com.esante.domain.PatientEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class PatientEntityRepository implements PanacheRepository<PatientEntity> {
}

