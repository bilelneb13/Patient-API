package com.esante;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@QuarkusTest
public class PatientEntityResourceTest {

    @Test
    public void testCreatePatient() {
        given()
                .contentType(ContentType.JSON)
                .body("{\"firstName\":\"John\",\"lastName\":\"Doe\"}")
                .when()
                .post("/patients")
                .then()
                .statusCode(201)
                .body("firstName", equalTo("John"))
                .body("lastName", equalTo("Doe"));
    }

    @Test
    public void testGetPatientById() {
        // First, create a patient
        Response response = given()
                .contentType(ContentType.JSON)
                .body("{\"firstName\":\"John\",\"lastName\":\"Doe\"}")
                .when()
                .post("/patients");

        Long patientId = response.jsonPath().getLong("id");

        // Now retrieve the patient
        given()
                .when()
                .get("/patients/" + patientId)
                .then()
                .statusCode(200)
                .body("firstName", equalTo("John"))
                .body("lastName", equalTo("Doe"));
    }

    @Test
    public void testDeletePatient() {
        // First, create a patient
        Response response = given()
                .contentType(ContentType.JSON)
                .body("{\"firstName\":\"John\",\"lastName\":\"Doe\"}")
                .when()
                .post("/patients");

        Long patientId = response.jsonPath().getLong("id");

        // Now delete the patient
        given()
                .when()
                .delete("/patients/" + patientId)
                .then()
                .statusCode(204);

        // Try to retrieve the deleted patient
        given()
                .when()
                .get("/patients/" + patientId)
                .then()
                .statusCode(404);
    }
}
