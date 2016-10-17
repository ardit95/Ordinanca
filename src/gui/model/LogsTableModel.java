package gui.model;

import ejb.Logs;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class LogsTableModel extends AbstractTableModel {

    static String[] columnNames;
    List<Logs> data;
    DateFormat dateFormat;
    DateFormat timeFormat;

    public LogsTableModel(String[] colNames) {
        columnNames = colNames;
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        timeFormat = new SimpleDateFormat("HH:mm");
        /*"Name", "Surname", "Data e Lindjes","Numri Personal","Email","Telefoni","Qyteti"*/
    }

    public void setColumnNames(String[] colNames) {
        columnNames = colNames;
    }

    public static String[] getColumnNames() {
        return columnNames;
    }

    public LogsTableModel(List<Logs> list) {
        data = list;
    }

    public void add(List<Logs> list) {
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
        Logs logs = (Logs) data.get(rowIndex);
        switch (columnNames[columnIndex]) {

            case "Logs":
                return logs.getLogsID();
            case "Date":
                return dateFormat.format(logs.getTimeStamp());
            case "Time":
                return timeFormat.format(logs.getTimeStamp());
            case "Message":
                return logs.getMessage();
            case "Type":
                return logs.getType();
            case "Username":
                return logs.getUsername();

            default:
                return null;
        }
    }

    public Logs getLogs(int rowIndex) {
        return data.get(rowIndex);
    }

    public void remove(int rowIndex) {
        data.remove(rowIndex);
    }
}
