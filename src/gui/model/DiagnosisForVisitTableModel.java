package gui.model;

import ejb.DiagnosisForVisit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class DiagnosisForVisitTableModel extends AbstractTableModel{
  static String [] columnNames;
    List<DiagnosisForVisit> data;
    DateFormat dateFormat;
    DateFormat timeFormat;
    
    public DiagnosisForVisitTableModel(String[] colNames) {
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
    
    
    public DiagnosisForVisitTableModel(List<DiagnosisForVisit> list) {
        data = list;
    }

    public void add(List<DiagnosisForVisit> list) {
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
    public Object getValueAt(int rowIndex, int columnIndex){
         DiagnosisForVisit DiagnosisForVisit= (DiagnosisForVisit)data.get(rowIndex);
        switch(columnNames[columnIndex]){
            /*DiagnosisForVisitID Price DiagnosisID DoctorVisitID */
            case "DiagnosisForVisitID":
                return DiagnosisForVisit.getDiagnosisForVisitID();
            case "Price":
                return DiagnosisForVisit.getPrice();
            case "DiagnosisID":
                return DiagnosisForVisit.getDiagnosisID();
            case "DoctorVisitID":
                return DiagnosisForVisit.getDoctorVisitID();
            default:
                 return null;
        }
    }
    
    public DiagnosisForVisit getDiagnosisForVisit(int rowIndex){
        return data.get(rowIndex);
    }
    
    public void remove(int rowIndex) {
        data.remove(rowIndex);
    }  
}