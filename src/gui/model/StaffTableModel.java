package gui.model;

import ejb.Staff;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class StaffTableModel extends AbstractTableModel{
  static String [] columnNames;
    List<Staff> data;
    DateFormat dateFormat;
    
    public StaffTableModel(String[] colNames) {
        columnNames=colNames;
        dateFormat=new SimpleDateFormat("dd-MM-yyyy");
        /*"Name", "Surname", "Data e Lindjes","Numri Personal","Email","Telefoni","Qyteti"*/
    }
    
    public void setColumnNames(String[] colNames){
            columnNames=colNames;
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
         Staff staff= (Staff)data.get(rowIndex);
        switch(columnNames[columnIndex]){
            
            case "Username":
                return staff.getUsername();
            case "Name":
                return staff.getName();
            case "Surname":
                return staff.getSurname();
            case "Gender":
                return staff.getGender(); 
            case "DateOfBirth":
                return dateFormat.format(staff.getDateOfBirth());   
            case "Education":
                return staff.getEducation();    
            case "Specialization":
                return staff.getSpecialization();     
            case "Role":
                return staff.getRole();
            
            default:
                 return null;
        }
    }
    
    public Staff getStaff(int rowIndex){
        return data.get(rowIndex);
    }
    
    public void remove(int rowIndex) {
        data.remove(rowIndex);
    }  
}

