package gui.model;

import ejb.AnalysisVisit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class AnalysisVisitTableModel extends AbstractTableModel{
  static String [] columnNames;
    List<AnalysisVisit> data;
    DateFormat dateFormat;
    DateFormat timeFormat;
    
    public AnalysisVisitTableModel(String[] colNames) {
        columnNames=colNames;
        dateFormat=new SimpleDateFormat("dd-MM-yyyy");
        timeFormat=new SimpleDateFormat("HH:mm");
    }
    
    public void setColumnNames(String[] colNames){
            columnNames=colNames;
    }

    public static String[] getColumnNames() {
        return columnNames;
    }
    
    
    public AnalysisVisitTableModel(List<AnalysisVisit> list) {
        data = list;
    }

    public void add(List<AnalysisVisit> list) {
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
         AnalysisVisit AnalysisVisit= (AnalysisVisit)data.get(rowIndex);
        switch(columnNames[columnIndex]){
            /*AnalysisVisitID Date PatientID LaboratorTechnicianID SumPrice Remark StaffID Finished */
            case "AnalysisVisitID":
                return AnalysisVisit.getAnalysisVisitID();
            case "Date":
                return dateFormat.format(AnalysisVisit.getDate());
            case "Time":
                return timeFormat.format(AnalysisVisit.getDate());
            case "PaitentID":
                return AnalysisVisit.getPatientID();
            case "LaboratorTechnicianID":
                return AnalysisVisit.getLaboratorTechnicianID();
            case "SumPrice":
                return AnalysisVisit.getSumPrice();
            case "Remark":
                return AnalysisVisit.getRemark();
            case "StaffID":
                return AnalysisVisit.getStaffID();
            case "Finished":
                return AnalysisVisit.getFinished();
            default:
                 return null;
        }
    }
    
    public AnalysisVisit getAnalysisVisit(int rowIndex){
        return data.get(rowIndex);
    }
    
    public void remove(int rowIndex) {
        data.remove(rowIndex);
    }  
}

