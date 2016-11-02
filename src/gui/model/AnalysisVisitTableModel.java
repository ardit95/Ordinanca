package gui.model;

import ejb.AnalysisVisit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class AnalysisVisitTableModel extends AbstractTableModel {

    static String[] columnNames;
    List<AnalysisVisit> data;
    DateFormat dateFormat;
    DateFormat timeFormat;

    public AnalysisVisitTableModel(String[] colNames) {
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

    public AnalysisVisitTableModel(List<AnalysisVisit> list) {
        data = list;
    }

    public void add(List<AnalysisVisit> list) {
        data = list;
    }

    @Override
    public int getRowCount() {
        if(data==null)
            return 0;
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
        AnalysisVisit analysisVisit = (AnalysisVisit) data.get(rowIndex);
        switch (columnNames[columnIndex]) {
            /*AnalysisVisitID Date PatientID LaboratorTechnicianID SumPrice Remark StaffID Finished */
            case "AnalysisVisitID":
                return analysisVisit.getAnalysisVisitID();
            case "Date":
                return dateFormat.format(analysisVisit.getTimeStamp());
            case "Time":
                return timeFormat.format(analysisVisit.getTimeStamp());
            case "PatientID":
                return analysisVisit.getPatientID();
            case "Visit Type":
                return analysisVisit.getTypeOfVisit();
            case "LaboratorTechnicianID":
                return analysisVisit.getLaboratorTechnicianID();
            case "SumPrice":
                return analysisVisit.getSumPrice();
            case "Remark":
                return analysisVisit.getRemark();
            case "StaffID":
                return analysisVisit.getStaffID();
            case "Finished":
                return analysisVisit.getFinished();
            default:
                return null;
        }
    }

    public AnalysisVisit getAnalysisVisit(int rowIndex) {
        return data.get(rowIndex);
    }

    public void remove(int rowIndex) {
        data.remove(rowIndex);
    }

    public void clear() {
        if(data!=null)
            data.clear();
    }
}
