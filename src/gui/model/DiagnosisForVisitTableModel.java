package gui.model;

import ejb.DiagnosisForVisit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class DiagnosisForVisitTableModel extends AbstractTableModel {

    static String[] columnNames;
    List<DiagnosisForVisit> data;
    DateFormat dateFormat;
    DateFormat timeFormat;

    public DiagnosisForVisitTableModel(String[] colNames) {
        columnNames = colNames;
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        timeFormat = new SimpleDateFormat("HH:mm");
    }

    public void setColumnNames(String[] colNames) {
        columnNames = colNames;
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
    
    public boolean isEmpty(){
        if(data==null)
            return true;
        return (data.isEmpty());
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DiagnosisForVisit DiagnosisForVisit = (DiagnosisForVisit) data.get(rowIndex);
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
        switch (columnNames[columnIndex]) {
            /*"DiagnosisForVisitID","CurrentPrice","DiagnosisID","DoctorVisitID","Patient","Complaint","Examination","Therapy","Date","Price"*/
            
            case "DiagnosisForVisitID":
                return DiagnosisForVisit.getDiagnosisForVisitID();
            case "CurrentPrice":
                return DiagnosisForVisit.getPrice();
            case "DiagnosisID":
                return DiagnosisForVisit.getDiagnosisID();
            case "DoctorVisitID":
                return DiagnosisForVisit.getDoctorVisitID();
            case "Patient":
                return DiagnosisForVisit.getDoctorVisitID().getPatientID();
            case "Complaint":
                return DiagnosisForVisit.getDiagnosisID().getComplaint();
            case "Examination":
                return DiagnosisForVisit.getDiagnosisID().getExamination();
            case "Therapy":
                return DiagnosisForVisit.getDiagnosisID().getTherapy();
            case "Date":
                return sdf.format(DiagnosisForVisit.getDoctorVisitID().getTimeStamp());
            case "Price":
                return DiagnosisForVisit.getDoctorVisitID().getSumPrice();
            default:
                return null;
        }
    }

    public DiagnosisForVisit getDiagnosisForVisit(int rowIndex) {
        return data.get(rowIndex);
    }

    public void remove(int rowIndex) {
        data.remove(rowIndex);
    }
}
