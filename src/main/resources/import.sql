INSERT INTO PATIENT (id, firstName, lastName) VALUES (1, 'John', 'Doe');
INSERT INTO PATIENT (id, firstName, lastName) VALUES (2, 'Jane', 'Smith');

ALTER SEQUENCE Patient_SEQ RESTART WITH 3;

INSERT INTO PatientEntity (id, firstName, lastName) VALUES (1, 'James', 'Doe');
INSERT INTO PatientEntity (id, firstName, lastName) VALUES (2, 'Jane', 'White');
ALTER SEQUENCE PatientEntity_SEQ RESTART WITH 3;

