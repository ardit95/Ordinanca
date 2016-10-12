CREATE DATABASE IF NOT EXISTS Ordinanca;
USE Ordinanca;

CREATE TABLE IF NOT EXISTS Staff(
	Username varchar(50) PRIMARY KEY,
    Password BINARY(64)NOT NULL,
	salt varchar(64) NOT NULL,
    Name varchar (50) NOT NULL,
    Surname varchar (50) NOT NULL,
    Gender varchar(1) NOT NULL,
    DateOfBirth date ,
    Education varchar(500),
    Specialization varchar(500),
    Role varchar(50),
    NumberOfLogins int DEFAULT 0,
	Status varchar(50) DEFAULT 'Active'
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS Logs(
	LogsID int PRIMARY KEY AUTO_INCREMENT,
    Date date NOT NULL,
    Time time NOT NULL,
    Type varchar(50) NOT NULL,
    Message varchar(300) NOT NULL,
    Seen varchar(5) DEFAULT 'No',
    Username varchar(50) NOT NULL,
    CONSTRAINT fk_Staff_Logs_Username FOREIGN KEY (Username) REFERENCES Staff(Username)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS Appointment(
	AppointmentID int PRIMARY KEY AUTO_INCREMENT,
    Name varchar(100) NOT NULL,
    Date date NOT NULL,
    Time time NOT NULL,
    Type varchar(100),
    Username varchar(50) NOT NULL,
    DoctorID varchar(50) NOT NULL,
    CONSTRAINT fk_Staff_Appointment_Username FOREIGN KEY (Username) REFERENCES Staff(Username),
    CONSTRAINT fk_Staff_Appointment_Doctor FOREIGN KEY (DoctorID) REFERENCES Staff(Username)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS Message(
	MessageID int PRIMARY KEY AUTO_INCREMENT,
    Date date NOT NULL,
    Time time NOT NULL,
    Message varchar(500) NOT NULL,
    Username varchar(50) NOT NULL,
    DoctorID varchar(50) NOT NULL,
    Seen varchar(5) DEFAULT 'No',
    CONSTRAINT fk_Staff_Message_Username FOREIGN KEY (Username) REFERENCES Staff(Username),
    CONSTRAINT fk_Staff_Message_Doctor FOREIGN KEY (DoctorID) REFERENCES Staff(Username)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS Patient(
	PatientID int PRIMARY KEY AUTO_INCREMENT,
 	Name varchar (50) NOT NULL,
    Surname varchar (50) NOT NULL,
    ParentName varchar (50) NOT NULL,
    Gender varchar(1) NOT NULL,
    DateOfBirth date NOT NULL,
    PlaceOFBirth varchar(50),
    City varchar(50),
    Phone varchar (50),
    Email varchar(100),
    Allergies varchar (500),
    Username varchar (50),
	CONSTRAINT fk_Staff_Patient_Username FOREIGN KEY (Username) REFERENCES Staff(Username)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS AnamnesisExaminationComplaint(
	AnamnesisExaminationComplaintID int PRIMARY KEY AUTO_INCREMENT,
    Date date NOT NULL,
    Time time NOT NULL,
    ComplaintTitle varchar (50) NOT NULL,
    Complaint varchar (300) NOT NULL,
    Anamnesis varchar (300) NOT NULL,
    Examination varchar (300) NOT NULL,
    CONSTRAINT uc_Complaint_Anamnesis_Examination UNIQUE CLUSTERED(Complaint,Anamnesis,Examination)  
)ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS Notification(
	NotificationID int PRIMARY KEY AUTO_INCREMENT,
    Date date NOT NULL,
    Time time NOT NULL,
    TypeOfVisit varchar(100) NOT NULL,
    Seen varchar(5) DEFAULT 'No',
    Username varchar(50) NOT NULL,
    DoctorID varchar(50) NOT NULL,
    PatientID int NOT NULL,
    CONSTRAINT fk_Staff_Notification_Username FOREIGN KEY (Username) REFERENCES Staff(Username),
    CONSTRAINT fk_Staff_Notification_Doctor FOREIGN KEY (DoctorID) REFERENCES Staff(Username),
    CONSTRAINT fk_Patient_Notification_Patient FOREIGN KEY (PatientID) REFERENCES Patient(PatientID)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS DoctorVisit(
	DoctorVisitID int PRIMARY KEY AUTO_INCREMENT,
    Date date NOT NULL,
    Time time NOT NULL,
    Price DECIMAL (10,2) NOT NULL,
    Therapy varchar (300) ,
    Recommendation varchar (300),
    NotificationID int NOT NULL ,
    AnamnesisExaminationComplaintID int NOT NULL,
    CONSTRAINT fk_Notification_DoctorVisit_NotificationID  FOREIGN KEY (NotificationID)  REFERENCES Notification(NotificationID) ,
    CONSTRAINT un_Notification_DoctorVisit_NotificationID UNIQUE (NotificationID), 
    CONSTRAINT fk_AEC_DoctorVisit_AECID FOREIGN KEY (AnamnesisExaminationComplaintID) REFERENCES AnamnesisExaminationComplaint(AnamnesisExaminationComplaintID)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS Analysis(
	AnalysisID int PRIMARY KEY AUTO_INCREMENT,
    Date date NOT NULL,
    Time time NOT NULL,
    Price DECIMAL (10,2) NOT NULL,
    Analysis varchar (1000) ,
    NotificationID int NOT NULL UNIQUE,
    CONSTRAINT fk_Notification_Analysis_NotificationID FOREIGN KEY (NotificationID) REFERENCES Notification(NotificationID)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

	/*Hashing SHA2_512 
		SELECT SHA2('123459421142',512)
    */
    
    /*Date and Time 
		SELECT CURRENT_TIMESTAMP
    */
    
    /*Selektimi i userave ne mysql
		SELECT USER FROM mysql.user;
	*/
    
    /*Sekeltimi i userit aktual
		SELECT CURRENT_USER
    */
    
    /*krijo user ne mysql
		CREATE USER Checker@localhost IDENTIFIED BY '12345';
	*/