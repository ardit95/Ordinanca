CREATE DATABASE IF NOT EXISTS Ordinanca;
USE Ordinanca;

CREATE TABLE IF NOT EXISTS Staff(
	Username varchar(50) PRIMARY KEY,
    Password BINARY(128)NOT NULL,
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
    timeStamp Timestamp NOT NULL,
    Type varchar(50) NOT NULL,
    Message varchar(300) NOT NULL,
    Seen varchar(5) DEFAULT 'No',
    Username varchar(50) NOT NULL,
    CONSTRAINT fk_Staff_Logs_Username FOREIGN KEY (Username) REFERENCES Staff(Username)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS Message(
	MessageID int PRIMARY KEY AUTO_INCREMENT,
    timeStamp Timestamp NOT NULL,
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

CREATE TABLE IF NOT EXISTS Analysis(
	AnalysisID int PRIMARY KEY AUTO_INCREMENT,
    Analysis varchar(200) NOT NULL, 
    Results varchar (1000) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE AnalysisVisit(
	AnalysisVisitID int PRIMARY KEY AUTO_INCREMENT,
    timeStamp Timestamp NOT NULL,
    
    PatientID int ,
    LaboratorTechnicianID varchar(50) NOT NULL,
    SumPrice decimal(10,2),
    Remark varchar (500),
    StaffID varchar (50) NOT NULL,
    Finished varchar (5) default 'No',
    CONSTRAINT fk_Patient_AV FOREIGN KEY (PatientID) REFERENCES Patient(PatientID),
    CONSTRAINT fk_LTechnician_AV FOREIGN KEY (LaboratorTechnicianID) REFERENCES Staff(Username),
    CONSTRAINT fk_StaffID_AV FOREIGN KEY (StaffID) REFERENCES Staff(Username)
);

CREATE TABLE AnalysisForVisit(
	AnalysisForVisitID int PRIMARY KEY AUTO_INCREMENT,
    Price decimal(10,2),
    AnalysisID int NOT NULL,
    AnalysisVisitID int NOT NULL,
    CONSTRAINT fk_AD_AnalysisID FOREIGN KEY (AnalysisID) REFERENCES Analysis(AnalysisID),
    CONSTRAINT fk_AD_AnalysisVisitID FOREIGN KEY (AnalysisVisitID) REFERENCES AnalysisVisit(AnalysisVisitID)
);

CREATE TABLE IF NOT EXISTS DoctorVisit(
	DoctorVisitID int PRIMARY KEY AUTO_INCREMENT,
    /*DoctorVisitID Date SumPrice Remark Finished PatientID DoctorID StaffID */
    timeStamp Timestamp NOT NULL,
    SumPrice decimal(10,2),
    Remark varchar (500),
    Finished varchar (5) default 'No',
    PatientID int ,
    DoctorID varchar(50)NOT NULL,
    StaffID varchar (50)NOT NULL,
    CONSTRAINT fk_DV_PatientID FOREIGN KEY (PatientID) REFERENCES Patient(PatientID),
    CONSTRAINT fk_DV_DoctorID FOREIGN KEY (DoctorID) REFERENCES Staff(Username),
    CONSTRAINT fk_DV_StaffID FOREIGN KEY (StaffID) REFERENCES Staff(Username)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS Diagnosis(
	DiagnosisID int PRIMARY KEY AUTO_INCREMENT,
    Complaint varchar(300) NOT NULL,
    Anamnesis varchar (300),
    Examination varchar (300) NOT NULL,
    Therapy varchar (300),
    Recommendation varchar (300)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS DiagnosisForVisit(
    DiagnosisForVisitID int PRIMARY KEY AUTO_INCREMENT,
    Price decimal(10,2) NOT NULL,
    DiagnosisID int NOT NULL,
	DoctorVisitID int NOT NULL,
    CONSTRAINT fk_DFV_DiagnosisID FOREIGN KEY (DiagnosisID) REFERENCES Diagnosis(DiagnosisID),
    CONSTRAINT fk_DFV_DoctorVisitID FOREIGN KEY (DoctorVisitID) REFERENCES DoctorVisit(DoctorVisitID)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;


USE Ordinanca;
SELECT * FROM AnalysisVisit;


	USE Ordinanca;
	CREATE VIEW NumberOfStaff AS
	SELECT COUNT(*) AS 'Numri' FROM Ordinanca.Staff;

    SELECT message FROM Message message WHERE message.username.username = :currentU AND message.seen='No';
	
    
    CREATE VIEW Report_Month_DoctorVisit
	AS
	SELECT YEAR(dv.Timestamp) as 'Viti' ,MONTH(dv.Timestamp) as 'Muaji' , COUNT(DISTINCT dv.DoctorVisitID) as 'Numri_i_Vizitave',COUNT(DISTINCT p.PatientID) as 'Numri_i_Pacienteve'
	FROM DoctorVisit dv
	LEFT JOIN Patient p ON dv.PatientID=p.PatientID;
    
    DROP VIEW Report_Month;
    
    CREATE VIEW Report_Month_AnalysisVisit
	AS
	SELECT 
    (CASE WHEN av.Timestamp IS NULL then 'NULL' else YEAR(av.Timestamp) END) as 'Viti' ,
	(CASE WHEN av.Timestamp IS NULL then 'NULL' else MONTH(av.Timestamp) END) as 'Muaji' , 
    COUNT(DISTINCT av.AnalysisVisitID) as 'Numri_i_Analizave',
    COUNT(DISTINCT p.PatientID) as 'Numri_i_Pacienteve'
	FROM AnalysisVisit av
	LEFT JOIN Patient p ON av.PatientID=p.PatientID;
	
    
    
    CREATE VIEW Report_Doctor
	AS
	SELECT YEAR(dv.Timestamp) as 'Viti' ,MONTH(dv.Timestamp) as 'Muaji',(s.Name ) as 'Emri_i_Doktorit',(s.Surname ) as 'Mbiemri_i_Doktorit' , COUNT(DISTINCT dv.DoctorVisitID) as 'Numri_i_Vizitave'
	FROM DoctorVisit dv
	LEFT JOIN Staff s ON dv.DoctorID=s.Username 
    WHERE s.Role='Doctor';
	



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
    
    /*Jep te drejta :D
		 GRANT SELECT ON Ordinanca.Staff TO Checker@localhost
    */
    
    /*Merr te drejta :(
		DENY SELECT ON Ordinanca.Staff FROM Checker@localhost
    */