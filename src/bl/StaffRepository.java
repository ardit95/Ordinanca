package bl;

import ExceptionPackage.AppException;
import ejb.Staff;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;

public class StaffRepository extends EntMngClass implements StaffInterface {

    public StaffRepository(EntityManager tempEm) {
        super(tempEm);
    }

    @Override
    public Staff create(Staff staff) throws AppException {
        try {
            em.getTransaction().begin();
            em.persist(staff);
            em.getTransaction().commit();
            return staff;
        } catch (Throwable thro) {
            if (thro.getMessage().contains("2627")) {
                if (thro.getMessage().toLowerCase().contains("unique")) {
                    throw new AppException("Ekziston një pjesëmarrës me këto vlera.Secila pjesëmarrës duhet të jetë unike.");
                } else {
                    throw new AppException("Ekziston nje pjesëmarrës me këtë çelës primarë.");
                }
            } else {
                throw new AppException("Create : " + thro.getClass() + " - " + thro.getMessage());
            }
        }
    }

    @Override
    public void edit(Staff staff) throws AppException {
        try {
            em.getTransaction().begin();
            em.merge(staff);
            em.getTransaction().commit();
        } catch (Throwable thro) {
            if (thro.getMessage().contains("2627")) {
                if (thro.getMessage().toLowerCase().contains("unique")) {
                    throw new AppException("Ekziston një pjesëmarrës me këto vlera.Secila pjesëmarrës duhet të jetë unike.");
                } else {
                    throw new AppException("Ekziston nje pjesëmarrës me këtë çelës primarë.");
                }
            } else {
                throw new AppException("Create : " + thro.getClass() + " - " + thro.getMessage());
            }
        }
    }

    @Override
    public void remove(Staff staff) {
        em.getTransaction().begin();
        em.remove(staff);
        em.getTransaction().commit();
    }

    @Override
    public List<Staff> findAll() {
        return em.createNamedQuery("Staff.findAll").getResultList();
    }

    @Override
    public String findSalt(String username) {
        Query query = em.createQuery("SELECT staff.salt FROM Staff staff WHERE staff.username= :usern ");
        query.setParameter("usern", username);
        return (String) query.getSingleResult();
    }

    @Override
    public Staff findByUsernamePassword(String us, byte[] pass) {
        Query query = em.createQuery("SELECT object(staff) FROM Staff staff WHERE staff.username= :usern AND staff.password= :pass   ");
        query.setParameter("usern", us);
        query.setParameter("pass", pass);
        return (Staff) query.getSingleResult();
    }

    @Override
    public byte[] kripto(String pass) {
        Query query
                = em.createNativeQuery("SELECT SHA2('" + pass + "',512)");
        return (byte[]) query.getSingleResult().toString().getBytes();
    }

    @Override
    public void changeLoginPassword(Staff staff, String text) {
        try {
            String serverIp = InetAddress.getLocalHost().getHostAddress();
            Connection conn = DriverManager.getConnection("jdbc:mysql://" + serverIp + ":3306/Ordinanca?zeroDateTimeBehavior=convertToNull", "root", "12345");
            Statement statement = conn.createStatement();
            statement.executeQuery("USE Ordinanca;");
            statement.executeQuery("SET PASSWORD FOR '" + staff.getUsername() + "'@'%' =PASSWORD('" + text + "'); ");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void createMySQLUser(Staff staff, String text) throws SQLException {
        String serverIp;
        try {
            serverIp = InetAddress.getLocalHost().getHostAddress();
        
        Connection conn = DriverManager.getConnection("jdbc:mysql://" + serverIp + ":3306/Ordinanca?zeroDateTimeBehavior=convertToNull", "root", "12345");
        Statement statement = conn.createStatement();
        statement.executeUpdate("USE Ordinanca;");
        statement.executeUpdate("CREATE USER IF NOT EXISTS '" + staff.getUsername() + "'@'192.168.1.104' IDENTIFIED BY '" + text + "'; ");
        statement.executeUpdate("GRANT SELECT,DELETE,UPDATE,INSERT ON Ordinanca.* TO '" + staff.getUsername() + "'@'192.168.1.104';");
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteMySQLUser(Staff staff) throws SQLException {
        String serverIp;
        try {
            serverIp = InetAddress.getLocalHost().getHostAddress();
        
        Connection conn = DriverManager.getConnection("jdbc:mysql://" + serverIp + ":3306/Ordinanca?zeroDateTimeBehavior=convertToNull", "root", "12345");
        Statement statement = conn.createStatement();
        statement.executeUpdate("USE Ordinanca;");
        statement.executeUpdate("DROP USER '" + staff.getUsername() + "'@'192.168.1.104'");
} catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int getNumberOfLogins(Staff staff) {
        Query query = em.createQuery("SELECT staff.numberOfLogins FROM Staff staff WHERE staff.username= :usern");
        query.setParameter("usern", staff.getUsername());
        return Integer.parseInt(query.getSingleResult().toString());
    }

    @Override
    public int CheckAdminExists() {
        Query query = em.createQuery("SELECT numberOfStaff.numri FROM NumberOfStaff numberOfStaff ");
        return Integer.parseInt(query.getSingleResult().toString());
    }

    @Override
    public void setStaffPassword(Staff staff) {
        try {
            String serverIp = InetAddress.getLocalHost().getHostAddress();
            Connection conn = DriverManager.getConnection("jdbc:mysql://" + serverIp + ":3306/Ordinanca?zeroDateTimeBehavior=convertToNull", "root", "12345");
            Statement statement = conn.createStatement();
            statement.executeQuery("USE Ordinanca;");
            statement.executeQuery("SET PASSWORD FOR '" + staff.getUsername() + "'@'%' =PASSWORD('12345678'); ");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Staff> findByAll(String text) {
        Query query = em.createQuery("SELECT object (s) FROM Staff s WHERE s.name LIKE :txt OR s.surname LIKE :txt OR s.username LIKE :txt OR s.gender LIKE :txt OR s.education LIKE :txt OR s.specialization LIKE :txt OR s.role LIKE :txt");
        query.setParameter("txt", "%" + text + "%");
        return (List<Staff>) query.getResultList();
    }

    public List<Staff> findAllWithoutAdministratorAndMyself(Staff currentUser) {
        Query query = em.createQuery("SELECT Object (staff) FROM Staff staff WHERE staff.role NOT LIKE 'Administrator' AND staff.username NOT LIKE :currentU ");
        query.setParameter("currentU", currentUser.getUsername());
        return (List<Staff>) query.getResultList();
    }

    @Override
    public List<Staff> findAllWithoutAdministrator() {
        Query query = em.createQuery("SELECT Object(staff) FROM Staff staff WHERE staff.role NOT LIKE 'Administrator'");
        return (List<Staff>) query.getResultList();
    }

    @Override
    public List<Staff> findByRole(String role) {
        Query query = em.createQuery("SELECT Object(staff) FROM Staff staff WHERE staff.role LIKE :specificRole ");
        query.setParameter("specificRole", role);
        return (List<Staff>)query.getResultList();
    }
}
