package com.esante.api;

import com.esante.domain.Patient;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/patients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PatientResource {

    @GET
    public List<Patient> get() {
        return Patient.listAll();
    }

    @GET
    @Path("{id}")
    public Patient getSingle(@PathParam("id") Long id) {
        Patient patient = Patient.findById(id);
        if (patient == null) {
            throw new WebApplicationException("patient with id of " + id + " does not exist.", 404);
        }
        return patient;
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Patient patient) {
        if (patient.id != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }

        patient.persist();
        return Response.ok(patient).status(201).build();
    }


    @PUT
    @Path("{id}")
    @Transactional
    public Patient updatePatient(@PathParam("id") Long id, Patient updatedPatient) {
        Patient patient = Patient.findById(id);
        if (patient == null) {
            throw new WebApplicationException("Patient not found", Response.Status.NOT_FOUND);
        }
        patient.firstName = updatedPatient.firstName;
        patient.lastName = updatedPatient.lastName;
        return patient;
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void deletePatient(@PathParam("id") Long id) {
        Patient patient = Patient.findById(id);
        if (patient == null) {
            throw new WebApplicationException("Patient not found", Response.Status.NOT_FOUND);
        }
        patient.delete();
    }
}
