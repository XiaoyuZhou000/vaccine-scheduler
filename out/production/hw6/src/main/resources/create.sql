CREATE TABLE Caregivers (
    Username varchar(255),
    Salt BINARY(16),
    Hash BINARY(16),
    PRIMARY KEY (Username)
);

CREATE TABLE Availabilities (
    date varchar(255),
    Care_username varchar(255) REFERENCES Caregiver,
    PRIMARY KEY (date, Care_username)
);

CREATE TABLE Vaccine (
    Name varchar(255),
    Doses int,
    PRIMARY KEY (Name)
);

CREATE TABLE Patients (
    Username varchar(255),
    Salt BINARY(16),
    Hash BINARY(16),
    PRIMARY KEY (Username)
);

/*
CREATE TABLE Appointment (
    app_id int,
    vaccine_name varchar(255) REFERENCES Vaccine,
    app_date date,
    patient_name varchar(255) REFERENCES Patient,
    caregiver_name varchar(255) REFERENCES Caregiver,
    PRIMARY KEY (app_id, vaccine_name)
);
*/
