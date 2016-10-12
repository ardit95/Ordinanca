package gui.model;

import ejb.AnamnesisExaminationComplaint;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class AnamnesisExaminationComplaintTableModel extends AbstractTableModel{
  static String [] columnNames;
    List<AnamnesisExaminationComplaint> data;
    
    public AnamnesisExaminationComplaintTableModel(String[] colNames) {
        columnNames=colNames;
        
    }
    
    public void setColumnNames(String[] colNames){
            columnNames=colNames;
    }

    public static String[] getColumnNames() {
        return columnNames;
    }
    
    
    public AnamnesisExaminationComplaintTableModel(List<AnamnesisExaminationComplaint> list) {
        data = list;
    }

    public void add(List<AnamnesisExaminationComplaint> list) {
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
         AnamnesisExaminationComplaint aec= (AnamnesisExaminationComplaint)data.get(rowIndex);
         DateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
         DateFormat timeFormat=new SimpleDateFormat("HH:mm");
        switch(columnNames[columnIndex]){
            /*AnamnesisExaminationComplaintID  ComplaintTitle Complaint Anamnesis Examiantion */
            
            case "AnamnesisExaminationComplaintID":
                return aec.getAnamnesisExaminationComplaintID();
            case "ComplaintTitle":
                return aec.getComplaintTitle();
            case "Complaint":
                return aec.getComplaint();
            case "Anamnesis":
                return aec.getAnamnesis();
            case "Examination":
                return aec.getExamination();
            
            default:
                 return null;
        }
    }
    
    public AnamnesisExaminationComplaint getAnamnesisExaminationComplaint(int rowIndex){
        return data.get(rowIndex);
    }
    
    public void remove(int rowIndex) {
        data.remove(rowIndex);
    }  
}
