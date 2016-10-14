package gui.model;

import ejb.AnalysisDetail;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class AnalysisDetailTableModel extends AbstractTableModel{
  static String [] columnNames;
    List<AnalysisDetail> data;
    
    public AnalysisDetailTableModel(String[] colNames) {
        columnNames=colNames;
        /*"Name", "Surname", "Data e Lindjes","Numri Personal","Email","Telefoni","Qyteti"*/
    }
    
    public void setColumnNames(String[] colNames){
            columnNames=colNames;
    }

    public static String[] getColumnNames() {
        return columnNames;
    }
    
    
    public AnalysisDetailTableModel(List<AnalysisDetail> list) {
        data = list;
    }

    public void add(List<AnalysisDetail> list) {
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
         AnalysisDetail analysisDetail= (AnalysisDetail)data.get(rowIndex);
         DateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
         DateFormat timeFormat=new SimpleDateFormat("HH:mm");
        switch(columnNames[columnIndex]){
            /*DoctorVisitID Date SumPrice Remark Finished PatientID DoctorID StaffID */
            case "Price":
                return analysisDetail.getPrice();
            case "AnalysisID":
                return analysisDetail.getAnalysisID(); 
            case "AnalysisVisit":
                return analysisDetail.getAnalysisVisitID();
            default:
                 return null;
        }
    }
    
    public AnalysisDetail getAnalysisDetail(int rowIndex){
        return data.get(rowIndex);
    }
    
    public void remove(int rowIndex) {
        data.remove(rowIndex);
    }  
}



