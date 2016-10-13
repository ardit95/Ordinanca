

START TRANSACTION;
/*krijon Checkerin user*/
	USE Ordinanca;
	CREATE USER IF NOT EXISTS Checker@localhost IDENTIFIED BY '12345';
	USE Ordinanca;
	GRANT SELECT ON Ordinanca.NumberOfStaff  TO Checker@localhost;
    
    CREATE USER IF NOT EXISTS Bajra@localhost IDENTIFIED BY '12345';
    GRANT SELECT,DELETE,UPDATE,INSERT ON Ordinanca.* TO Bajra@localhost;
    INSERT INTO Staff (Username,Password,Salt,Name,Surname,Gender,DateOfBirth,Education,Specialization,Role,NumberOfLogins) 
	VALUES ('Bajra',SHA2('salti12345',512),'salti','Bajra','Kadriu','M','1995-02-18','Minister','Vrasje Serike','Administrator','1');
	
    DELETE FROM STAFF WHERE Username='Bajra';
    DROP USER Bajra@localhost;
    
    SELECT * FROM Staff
/*
USE Ordinanca;
GRANT SELECT,INSERT,UPDATE ON SCHEMA::dbo TO Administrator;
USE Ordinanca;
GRANT DELETE ON [dbo].[Participant_Team] TO Administrator;
USE Ordinanca;
GRANT DELETE ON [dbo].[Users] TO Administrator;
USE Ordinanca;
GRANT DELETE ON [dbo].[Logs] TO Administrator;
*/

/*
USE Ordinanca;
CREATE ROLE Stafi AUTHORIZATION Administrator;

USE Ordinanca
GRANT SELECT,INSERT,UPDATE ON SCHEMA::dbo TO stafi;
USE Ordinanca
GRANT DELETE ON [dbo].[Participant_Team] TO stafi;
*/
COMMIT;


