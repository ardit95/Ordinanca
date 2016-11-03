package gui.model;

import ejb.Staff;
import ejbView.ReportMonthAnalysisSales;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ReportMonthAnalysisSalesTableModel extends AbstractTableModel {

    static String[] columnNames;
    List<ReportMonthAnalysisSales> data;
    Staff currentUser;

    public ReportMonthAnalysisSalesTableModel(Staff currentUser,String[] colNames) {
        columnNames = colNames;
        this.currentUser=currentUser;
    }

    public void setColumnNames(String[] colNames) {
        columnNames = colNames;
    }

    public static String[] getColumnNames() {
        return columnNames;
    }

    public ReportMonthAnalysisSalesTableModel(List<ReportMonthAnalysisSales> list) {
        data = list;
    }

    public void add(List<ReportMonthAnalysisSales> list) {
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
        ReportMonthAnalysisSales reportMonthAnalysisSales = (ReportMonthAnalysisSales) data.get(rowIndex);
        if(currentUser.getName().equals(reportMonthAnalysisSales.getEmriiLaborantit())&&currentUser.getSurname().equals(reportMonthAnalysisSales.getMbiemriiLaborantit())){
            switch (columnNames[columnIndex]) {
                /*AnalysisID Analysis Results*/
                case "Year":
                        return reportMonthAnalysisSales.getViti();
                case "Month":
                        return reportMonthAnalysisSales.getMuaji();
                case "NumberOFAnalysis":
                        return reportMonthAnalysisSales.getNumriiAnalizave();
                case "LaboratorTechnicianName":
                    return reportMonthAnalysisSales.getEmriiLaborantit();
                case "LaboratorTechnicianSurname":
                    return reportMonthAnalysisSales.getMbiemriiLaborantit();
                case "Sales":
                    return reportMonthAnalysisSales.getShitje();
                default:
                    return null;
            }
        }else{
            return null;
        }
    }

    public ReportMonthAnalysisSales getReportMonthAnalysisSales(int rowIndex) {
        return data.get(rowIndex);
    }

    public void remove(int rowIndex) {
        data.remove(rowIndex);
    }
}



