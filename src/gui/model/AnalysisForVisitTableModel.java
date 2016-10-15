package gui.model;

import ejb.AnalysisForVisit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class AnalysisForVisitTableModel extends AbstractTableModel{
  static String [] columnNames;
    List<AnalysisForVisit> data;
    
    public AnalysisForVisitTableModel(String[] colNames) {
        columnNames=colNames;
        /*"Name", "Surname", "Data e Lindjes","Numri Personal","Email","Telefoni","Qyteti"*/
    }
    
    public void setColumnNames(String[] colNames){
            columnNames=colNames;
    }

    public static String[] getColumnNames() {
        return columnNames;
    }
    
    
    public AnalysisForVisitTableModel(List<AnalysisForVisit> list) {
        data = list;
    }

    public void add(List<AnalysisForVisit> list) {
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
         AnalysisForVisit AnalysisForVisit= (AnalysisForVisit)data.get(rowIndex);
         DateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
         DateFormat timeFormat=new SimpleDateFormat("HH:mm");
        switch(columnNames[columnIndex]){
            /*DoctorVisitID Date SumPrice Remark Finished PatientID DoctorID StaffID */
            case "Price":
                return AnalysisForVisit.getPrice();
            case "AnalysisID":
                return AnalysisForVisit.getAnalysisID(); 
            case "DiagnosisForVisit":
                return AnalysisForVisit.getAnalysisForVisitID();
            default:
                 return null;
        }
    }
    
    public AnalysisForVisit getAnalysisForVisit(int rowIndex){
        return data.get(rowIndex);
    }
    
    public void remove(int rowIndex) {
        data.remove(rowIndex);
    }  
}



