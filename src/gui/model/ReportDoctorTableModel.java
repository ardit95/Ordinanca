package gui.model;

import ejbView.ReportDoctor;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class ReportDoctorTableModel extends AbstractTableModel {

    static String[] columnNames;
    List<ReportDoctor> data;

    public ReportDoctorTableModel(String[] colNames) {
        columnNames = colNames;

    }

    public void setColumnNames(String[] colNames) {
        columnNames = colNames;
    }

    public static String[] getColumnNames() {
        return columnNames;
    }

    public ReportDoctorTableModel(List<ReportDoctor> list) {
        data = list;
    }

    public void add(List<ReportDoctor> list) {
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
        ReportDoctor reportDoctor = (ReportDoctor) data.get(rowIndex);
        switch (columnNames[columnIndex]) {
            /*AnalysisID Analysis Results*/
            case "Year":
                return reportDoctor.getViti();
            case "Month":
                return reportDoctor.getMuaji();
            case "DoctorName":
                return reportDoctor.getEmriiDoktorit();
            case "DoctorSurname":
                return reportDoctor.getMbiemriiDoktorit();
            case "NumberOfVisits":
                return reportDoctor.getNumriiVizitave();
            default:
                return null;
        }
    }

    public ReportDoctor getReportDoctor(int rowIndex) {
        return data.get(rowIndex);
    }

    public void remove(int rowIndex) {
        data.remove(rowIndex);
    }
}

