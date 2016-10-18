package gui.model;

import bl.MessageInterface;
import bl.MessageRepository;
import ejb.Staff;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.table.AbstractTableModel;

public class StaffTableModel extends AbstractTableModel {

    static String[] columnNames;
    List<Staff> data;
    DateFormat dateFormat;
    EntityManager entityManager;
    MessageInterface messageIr;
    Staff currentUser;

    public StaffTableModel(String[] colNames,EntityManager entityManager,Staff currentUser) {
        this.entityManager=entityManager;
        this.currentUser=currentUser;
        this.messageIr=new MessageRepository(this.entityManager);
        columnNames = colNames;
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        /*"Name", "Surname", "Data e Lindjes","Numri Personal","Email","Telefoni","Qyteti"*/
    }

    public void setColumnNames(String[] colNames) {
        columnNames = colNames;
    }

    public static String[] getColumnNames() {
        return columnNames;
    }

    public StaffTableModel(List<Staff> list) {
        data = list;
    }

    public void add(List<Staff> list) {
        data = list;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Staff staff = (Staff) data.get(rowIndex);
        switch (columnNames[columnIndex]) {
            case "Username":
                return staff.getUsername();
            case "Name":
                return staff.getName();
            case "Surname":
                return staff.getSurname();
            case "Gender":
                return staff.getGender();
            case "DateOfBirth":
                if (staff.getDateOfBirth() != null) {
                    return dateFormat.format(staff.getDateOfBirth());
                }
            case "Education":
                return staff.getEducation();
            case "Specialization":
                return staff.getSpecialization();
            case "Role":
                return staff.getRole();
            case "Unseen":
                return messageIr.countUnseenMessagesForSpecificUser(currentUser,staff);
            default:
                return null;
        }
    }

    public Staff getStaff(int rowIndex) {
        return data.get(rowIndex);
    }

    public void remove(int rowIndex) {
        data.remove(rowIndex);
    }

    public boolean isEmpty() {
        if (data != null) {
            return (data.isEmpty());
        }
        return false;
    }
}
