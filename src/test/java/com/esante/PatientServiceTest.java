package com.esante;

import com.esante.domain.PatientEntity;
import com.esante.repository.PatientEntityRepository;
import com.esante.service.PatientEntityService;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import javax.inject.Inject;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@QuarkusTest
public class PatientServiceTest {

    @Mock
    PatientEntityRepository patientRepository;

    @InjectMocks
    PatientEntityService patientService;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testCreatePatient() {
        PatientEntity patient = PatientEntity.builder().firstName("foo").lastName("bar").build();
        doNothing().when(patientRepository).persist(patient);

        PatientEntity createdPatient = patientService.createPatient(patient);
        assertNotNull(createdPatient);
        assertEquals("foo", createdPatient.getFirstName());
        assertEquals("bar", createdPatient.getLastName());
    }

    @Test
    public void testFindPatientById() {
        PatientEntity patient = PatientEntity.builder().firstName("foo").lastName("bar").build();
        when(patientRepository.findByIdOptional(1L)).thenReturn(Optional.of(patient));

        Optional<PatientEntity> foundPatient = patientService.findPatientById(1L);
        assertTrue(foundPatient.isPresent());
        assertEquals("foo", foundPatient.get().getFirstName());
        assertEquals("bar", foundPatient.get().getLastName());
    }

    @Test
    public void testDeletePatient_Success() {
        // Mock the boolean return value
        when(patientRepository.deleteById(1L)).thenReturn(true);

        boolean result = patientService.deletePatient(1L);
        assertTrue(result);
        verify(patientRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeletePatient_Failure() {
        // Mock the boolean return value
        when(patientRepository.deleteById(1L)).thenReturn(false);

        boolean result = patientService.deletePatient(1L);
        assertFalse(result);
        verify(patientRepository, times(1)).deleteById(1L);
    }
}

