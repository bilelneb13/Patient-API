# Patient Application

# Running the Patient Application

## Instructions

### Pull and Run the Docker Image

To ensure you are running the latest version of the Docker image, follow these steps:
  Pull the image from Docker hub with this command:
  
        docker pull bilelneb/patient-app:latest
  Run the image:
  
        docker run -p 8080:8080 bilelneb/patient-app:latest
### Verify the Application is Running
Open a web browser and navigate to:

      http://localhost:8080

## API Endpoints

The Patient API provides the following endpoints:

### Get All Patients

- **URL**: `/api/patients`
- **Method**: `GET`
- **Description**: Retrieves a list of all patients.
- **Response**:
  - `200 OK`: A list of patients.

### Create a New Patient

- **URL**: `/api/patients`
- **Method**: `POST`
- **Description**: Creates a new patient.
- **Request Body**:
  - `firstName` (string): The patient's first name.
  - `lastName` (string): The patient's last name.
- **Response**:
  - `201 Created`: The created patient.

### Get Patient by ID

- **URL**: `/api/patients/{id}`
- **Method**: `GET`
- **Description**: Retrieves a patient by ID.
- **Path Parameters**:
  - `id` (integer): The patient ID.
- **Response**:
  - `200 OK`: The patient with the specified ID.
  - `404 Not Found`: Patient not found.

### Update Patient by ID

- **URL**: `/api/patients/{id}`
- **Method**: `PUT`
- **Description**: Updates a patient by ID.
- **Path Parameters**:
  - `id` (integer): The patient ID.
- **Request Body**:
  - `firstName` (string): The patient's first name.
  - `lastName` (string): The patient's last name.
- **Response**:
  - `200 OK`: The updated patient.
  - `404 Not Found`: Patient not found.

### Delete Patient by ID

- **URL**: `/api/patients/{id}`
- **Method**: `DELETE`
- **Description**: Deletes a patient by ID.
- **Path Parameters**:
  - `id` (integer): The patient ID.
- **Response**:
  - `204 No Content`: Patient deleted.
  - `404 Not Found`: Patient not found.
