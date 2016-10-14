package gui.model;

import ejb.DoctorVisitDetails;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class DoctorVisitDetailsTableModel extends AbstractTableModel{
  static String [] columnNames;
    List<DoctorVisitDetails> data;
    
    public DoctorVisitDetailsTableModel(String[] colNames) {
        columnNames=colNames;
        /*"Name", "Surname", "Data e Lindjes","Numri Personal","Email","Telefoni","Qyteti"*/
    }
    
    public void setColumnNames(String[] colNames){
            columnNames=colNames;
    }

    public static String[] getColumnNames() {
        return columnNames;
    }
    
    
    public DoctorVisitDetailsTableModel(List<DoctorVisitDetails> list) {
        data = list;
    }

    public void add(List<DoctorVisitDetails> list) {
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
         DoctorVisitDetails doctorVisitDetails= (DoctorVisitDetails)data.get(rowIndex);
         DateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
         DateFormat timeFormat=new SimpleDateFormat("HH:mm");
        switch(columnNames[columnIndex]){
            /*DoctorVisitDetailID Price Complaint Anamnesis Examination Therapy Recommendation DoctorVisitID */
            case "DoctorVisitDetailsID":
                return doctorVisitDetails.getDoctorVisitDetailID();
            case "Price":
                return doctorVisitDetails.getPrice();
            case "Complaint":
                return doctorVisitDetails.getComplaint();
            case "Anamnesis":
                return doctorVisitDetails.getAnamnesis();
            case "Examination":
                return doctorVisitDetails.getExamination();
                case "Therapy":
                return doctorVisitDetails.getTherapy();
                case "Recommendation":
                return doctorVisitDetails.getRecommendation();
                case "DoctorVisitID":
                return doctorVisitDetails.getDoctorVisitID();
            default:
                 return null;
        }
    }
    
    public DoctorVisitDetails getDoctorVisitDetails(int rowIndex){
        return data.get(rowIndex);
    }
    
    public void remove(int rowIndex) {
        data.remove(rowIndex);
    }  
}