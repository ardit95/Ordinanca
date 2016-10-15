package gui.model;

import ejb.Patient;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class PatientTableModel extends AbstractTableModel{
  static String [] columnNames;
    List<Patient> data;
    DateFormat dateFormat;
    
    public PatientTableModel(String[] colNames) {
        columnNames=colNames;
        dateFormat=new SimpleDateFormat("dd-MM-yyyy");
    }
    
    public void setColumnNames(String[] colNames){
            columnNames=colNames;
    }

    public static String[] getColumnNames() {
        return columnNames;
    }
    
    
    public PatientTableModel(List<Patient> list) {
        data = list;
    }

    public void add(List<Patient> list) {
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
         Patient patient= (Patient)data.get(rowIndex);
        switch(columnNames[columnIndex]){
            /*PatientID Name Surname ParentName Gender DateOfBirth PlaceOFBirth City Phone Email Allergies Allergies Username */
            
            case "PatientID":
                return patient.getPatientID();
            case "Name":
                return patient.getName();
            case "Surname":
                return patient.getSurname();
            case "ParentName":
                return patient.getParentName();
            case "Gender":
                return patient.getGender();
            case "DateOfBirth":
                return dateFormat.format(patient.getDateOfBirth());
            case "PlaceOfBirth":
                return patient.getPlaceOFBirth();
            case "City":
                return patient.getCity();
            case "Phone":
                return patient.getPhone();
            case "Email":
                return patient.getEmail();
            case "Allergies":
                return patient.getAllergies();
            case "Username":
                return patient.getUsername().getUsername();
            default:
                 return null;
        }
    }
    
    public Patient getPatient(int rowIndex){
        return data.get(rowIndex);
    }
    
    public void remove(int rowIndex) {
        data.remove(rowIndex);
    }  
}

