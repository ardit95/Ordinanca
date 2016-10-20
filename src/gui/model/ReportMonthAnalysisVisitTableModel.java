package gui.model;

import ejbView.ReportMonthAnalysisVisit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ReportMonthAnalysisVisitTableModel extends AbstractTableModel {

    static String[] columnNames;
    List<ReportMonthAnalysisVisit> data;

    public ReportMonthAnalysisVisitTableModel(String[] colNames) {
        columnNames = colNames;

    }

    public void setColumnNames(String[] colNames) {
        columnNames = colNames;
    }

    public static String[] getColumnNames() {
        return columnNames;
    }

    public ReportMonthAnalysisVisitTableModel(List<ReportMonthAnalysisVisit> list) {
        data = list;
    }

    public void add(List<ReportMonthAnalysisVisit> list) {
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
        ReportMonthAnalysisVisit reportMonthAnalysisVisit = (ReportMonthAnalysisVisit) data.get(rowIndex);
        switch (columnNames[columnIndex]) {
            /*AnalysisID Analysis Results*/
            case "Year":
                return reportMonthAnalysisVisit.getViti();
            case "Month":
                return reportMonthAnalysisVisit.getMuaji();
            case "NumberOFAnalysis":
                return reportMonthAnalysisVisit.getNumriiAnalizave(); 
            case "NumberOfPatients":
                return reportMonthAnalysisVisit.getNumriiPacienteve(); 
            default:
                return null;
        }
    }

    public ReportMonthAnalysisVisit getReportMonthAnalysisVisit(int rowIndex) {
        return data.get(rowIndex);
    }

    public void remove(int rowIndex) {
        data.remove(rowIndex);
    }
}


