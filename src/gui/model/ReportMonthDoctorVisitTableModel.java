package gui.model;

import ejbView.ReportMonthDoctorVisit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ReportMonthDoctorVisitTableModel extends AbstractTableModel {

    static String[] columnNames;
    List<ReportMonthDoctorVisit> data;

    public ReportMonthDoctorVisitTableModel(String[] colNames) {
        columnNames = colNames;

    }

    public void setColumnNames(String[] colNames) {
        columnNames = colNames;
    }

    public static String[] getColumnNames() {
        return columnNames;
    }

    public ReportMonthDoctorVisitTableModel(List<ReportMonthDoctorVisit> list) {
        data = list;
    }

    public void add(List<ReportMonthDoctorVisit> list) {
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
        ReportMonthDoctorVisit reportMonthDoctorVisit = (ReportMonthDoctorVisit) data.get(rowIndex);
        switch (columnNames[columnIndex]) {
            /*AnalysisID Analysis Results*/
            case "Year":
                    return reportMonthDoctorVisit.getViti();
            case "Month":
                    return reportMonthDoctorVisit.getMuaji();
            case "NumberOFVisits":
                    return reportMonthDoctorVisit.getNumriiVizitave();
            case "NumberOfPatients":
                    return reportMonthDoctorVisit.getNumriiPacienteve();
            default:
                return null;
        }
    }

    public ReportMonthDoctorVisit getReportMonthDoctorVisit(int rowIndex) {
        return data.get(rowIndex);
    }

    public void remove(int rowIndex) {
        data.remove(rowIndex);
    }
}


