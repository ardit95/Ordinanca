package gui.model;

import ejb.Staff;
import ejbView.ReportMonthVisitSales;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ReportMonthVisitSalesTableModel extends AbstractTableModel {

    static String[] columnNames;
    List<ReportMonthVisitSales> data;
    Staff currentUser;

    public ReportMonthVisitSalesTableModel(Staff currentUser,String[] colNames) {
        columnNames = colNames;
        this.currentUser=currentUser;
    }

    public void setColumnNames(String[] colNames) {
        columnNames = colNames;
    }

    public static String[] getColumnNames() {
        return columnNames;
    }

    public ReportMonthVisitSalesTableModel(List<ReportMonthVisitSales> list) {
        data = list;
    }

    public void add(List<ReportMonthVisitSales> list) {
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
        ReportMonthVisitSales reportMonthVisitSales = (ReportMonthVisitSales) data.get(rowIndex);
            if(currentUser.getName().equals(reportMonthVisitSales.getEmriiDoktorit())&&currentUser.getSurname().equals(reportMonthVisitSales.getMbiemriiDoktorit())){
                switch (columnNames[columnIndex]) {
                /*AnalysisID Analysis Results*/
                case "Year":
                        return reportMonthVisitSales.getViti();
                case "Month":
                        return reportMonthVisitSales.getMuaji();
                case "NumberOFVisits":
                        return reportMonthVisitSales.getNumriiVizitave();
                case "DoctorName":
                    return reportMonthVisitSales.getEmriiDoktorit();
                case "DoctorSurname":
                    return reportMonthVisitSales.getMbiemriiDoktorit();
                case "Sales":
                    return reportMonthVisitSales.getShitje();
                default:
                    return null;
            }
            }else{
                return null;
            }
    }

    public ReportMonthVisitSales getReportMonthVisitSales(int rowIndex) {
        return data.get(rowIndex);
    }

    public void remove(int rowIndex) {
        data.remove(rowIndex);
    }
}



