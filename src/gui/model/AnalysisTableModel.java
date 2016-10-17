package gui.model;

import ejb.Analysis;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class AnalysisTableModel extends AbstractTableModel {

    static String[] columnNames;
    List<Analysis> data;

    public AnalysisTableModel(String[] colNames) {
        columnNames = colNames;

    }

    public void setColumnNames(String[] colNames) {
        columnNames = colNames;
    }

    public static String[] getColumnNames() {
        return columnNames;
    }

    public AnalysisTableModel(List<Analysis> list) {
        data = list;
    }

    public void add(List<Analysis> list) {
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
        Analysis analysis = (Analysis) data.get(rowIndex);
        switch (columnNames[columnIndex]) {
            /*AnalysisID Analysis Results*/
            case "AnalysisID":
                return analysis.getAnalysisID();
            case "Analysis":
                return analysis.getAnalysis();
            case "Results":
                analysis.getResults();
            default:
                return null;
        }
    }

    public Analysis getAnalysis(int rowIndex) {
        return data.get(rowIndex);
    }

    public void remove(int rowIndex) {
        data.remove(rowIndex);
    }
}
