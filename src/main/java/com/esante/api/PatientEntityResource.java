package com.esante.api;

import com.esante.domain.Patient;
import com.esante.domain.PatientEntity;
import com.esante.service.PatientEntityService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Path("/v2/patients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PatientEntityResource {
    @Inject
    PatientEntityService patientService;
    @GET
    public List<PatientEntity> findAll() {
        return patientService.findAll();
    }

    @GET
    @Path("{id}")
    public Response getSingle(@PathParam("id") Long id) {
        Optional<PatientEntity> patient = patientService.findPatientById(id);
        return patient.map(Response::ok)
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(PatientEntity patientEntity) {
        PatientEntity createdPatient = patientService.createPatient(patientEntity);
        return Response.status(Response.Status.CREATED)
                .entity(createdPatient)
                .build();
    }


    @PUT
    @Path("{id}")
    @Transactional
    public PatientEntity updatePatient(@PathParam("id") Long id, PatientEntity updatedPatient) {
        Optional<PatientEntity> optionalPatient = patientService.findPatientById(id);
        if (!optionalPatient.isPresent()) {
            throw new WebApplicationException("Patient not found", Response.Status.NOT_FOUND);
        }
        PatientEntity patient = optionalPatient.get();
        patient.setFirstName(updatedPatient.getFirstName());
        patient.setLastName(updatedPatient.getLastName());
        return patient;
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deletePatient(@PathParam("id") Long id) {
        patientService.deletePatient(id);
        return Response.noContent().build();
    }
}
