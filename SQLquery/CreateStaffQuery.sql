START TRANSACTION;
/*krijon Checkerin user*/
	USE Ordinanca;
	CREATE USER IF NOT EXISTS Checker@localhost IDENTIFIED BY '12345';
	USE Ordinanca;
	GRANT SELECT ON Ordinanca.NumberOfStaff  TO Checker@localhost;
	
	
    CREATE USER IF NOT EXISTS Bajra@localhost IDENTIFIED BY '12345';
    GRANT SELECT,DELETE,UPDATE,INSERT ON Ordinanca.* TO Bajra@localhost;
    INSERT INTO Staff (Username,Password,Salt,Name,Surname,Gender,DateOfBirth,Education,Specialization,Role) 
	VALUES ('Bajra',SHA2('salti12345',512),'salti','Bajra','Kadriu','M','1995-02-18','Minister','Vrasje Serike','Administrator');
    
    CREATE USER IF NOT EXISTS Edon@localhost IDENTIFIED BY '12345';
    GRANT SELECT,DELETE,UPDATE,INSERT ON Ordinanca.* TO Edon@localhost;
    INSERT INTO Staff (Username,Password,Salt,Name,Surname,Gender,DateOfBirth,Education,Specialization,Role) 
	VALUES ('Edon',SHA2('salti12345',512),'salti','Edon','Bytyqi','M','1993-02-18','Web-Developer','Thitje Serike','Doctor');
    
    /*
		DROP USER Bajra@localhost;
        USE Ordinanca;
		DELETE FROM Staff WHERE Username='Bajra';
    */

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


