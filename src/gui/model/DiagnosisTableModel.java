package gui.model;

import ejb.Diagnosis;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class DiagnosisTableModel extends AbstractTableModel{
  static String [] columnNames;
    List<Diagnosis> data;
    
    public DiagnosisTableModel(String[] colNames) {
        columnNames=colNames;
        /*"Name", "Surname", "Data e Lindjes","Numri Personal","Email","Telefoni","Qyteti"*/
    }
    
    public void setColumnNames(String[] colNames){
            columnNames=colNames;
    }

    public static String[] getColumnNames() {
        return columnNames;
    }
    
    
    public DiagnosisTableModel(List<Diagnosis> list) {
        data = list;
    }

    public void add(List<Diagnosis> list) {
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
         Diagnosis Diagnosis= (Diagnosis)data.get(rowIndex);
        switch(columnNames[columnIndex]){
            /*DoctorVisitDetailID Price Complaint Anamnesis Examination Therapy Recommendation DoctorVisitID */
            case "DiagnosisID":
                return Diagnosis.getDiagnosisID();
            case "Complaint":
                return Diagnosis.getComplaint();
            case "Anamnesis":
                return Diagnosis.getAnamnesis();
            case "Examination":
                return Diagnosis.getExamination();
                case "Therapy":
                return Diagnosis.getTherapy();
                case "Recommendation":
                return Diagnosis.getRecommendation();
                case "DoctorVisitID":
                return Diagnosis.getDoctorVisitID();
            default:
                 return null;
        }
    }
    
    public Diagnosis getDiagnosis(int rowIndex){
        return data.get(rowIndex);
    }
    
    public void remove(int rowIndex) {
        data.remove(rowIndex);
    }  
}