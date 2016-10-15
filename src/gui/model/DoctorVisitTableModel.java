package gui.model;

import ejb.DoctorVisit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class DoctorVisitTableModel extends AbstractTableModel{
  static String [] columnNames;
    List<DoctorVisit> data;
    DateFormat dateFormat;
    DateFormat timeFormat;
    
    public DoctorVisitTableModel(String[] colNames) {
        columnNames=colNames;
        dateFormat=new SimpleDateFormat("dd-MM-yyyy");
        timeFormat=new SimpleDateFormat("HH:mm");
        /*"Name", "Surname", "Data e Lindjes","Numri Personal","Email","Telefoni","Qyteti"*/
    }
    
    public void setColumnNames(String[] colNames){
            columnNames=colNames;
    }

    public static String[] getColumnNames() {
        return columnNames;
    }
    
    
    public DoctorVisitTableModel(List<DoctorVisit> list) {
        data = list;
    }

    public void add(List<DoctorVisit> list) {
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
         DoctorVisit DoctorVisit= (DoctorVisit)data.get(rowIndex);
        switch(columnNames[columnIndex]){
            /*DoctorVisitID Date SumPrice Remark Finished PatientID DoctorID StaffID */
            case "DoctorVisitID":
                return DoctorVisit.getDoctorVisitID();
            case "Date":
                return dateFormat.format(DoctorVisit.getDate()); 
            case "Time":
                return timeFormat.format(DoctorVisit.getDate());
            case "SumPrice":
                return DoctorVisit.getSumPrice();
            case "Remark":
                return DoctorVisit.getRemark();   
            case "Finished":
                return DoctorVisit.getFinished();    
            case "PatientID":
                return DoctorVisit.getPatientID();   
            case "DoctorID":
                return DoctorVisit.getDoctorID(); 
            case "StaffID":
                return DoctorVisit.getStaffID();
            default:
                 return null;
        }
    }
    
    public DoctorVisit getDoctorVisit(int rowIndex){
        return data.get(rowIndex);
    }
    
    public void remove(int rowIndex) {
        data.remove(rowIndex);
    }  
}



