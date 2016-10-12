package gui.model;

import ejb.DoctorVisit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class DoctorVisitTableModel extends AbstractTableModel{
  static String [] columnNames;
    List<DoctorVisit> data;
    
    public DoctorVisitTableModel(String[] colNames) {
        columnNames=colNames;
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
         DoctorVisit doctorVisit= (DoctorVisit)data.get(rowIndex);
         DateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
         DateFormat timeFormat=new SimpleDateFormat("HH:mm");
        switch(columnNames[columnIndex]){
            
            case "DoctorVisitID":
                return doctorVisit.getDoctorVisitID();
            case "Date":
                return dateFormat.format(doctorVisit.getDate()); 
            case "Time":
                return dateFormat.format(doctorVisit.getTime());
            case "Price":
                return doctorVisit.getPrice();
            case "Therapy":
                return doctorVisit.getTherapy();   
            case "Recommendation":
                return doctorVisit.getRecommendation();    
            case "NotificationID":
                return doctorVisit.getNotificationID().getNotificationID();   
            case "AnamnesisExamiantionComplaintID":
                return doctorVisit.getAnamnesisExaminationComplaintID().getAnamnesisExaminationComplaintID(); 
            case "Anamnesis":
                return doctorVisit.getAnamnesisExaminationComplaintID().getAnamnesis();
            case "Examiantion":
                return doctorVisit.getAnamnesisExaminationComplaintID().getExamination();
            case "Complaint":
                return doctorVisit.getAnamnesisExaminationComplaintID().getComplaint();
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



