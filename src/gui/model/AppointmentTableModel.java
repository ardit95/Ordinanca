package gui.model;

import ejb.Appointment;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class AppointmentTableModel extends AbstractTableModel{
  static String [] columnNames;
    List<Appointment> data;
    
    public AppointmentTableModel(String[] colNames) {
        columnNames=colNames;
        
    }
    
    public void setColumnNames(String[] colNames){
            columnNames=colNames;
    }

    public static String[] getColumnNames() {
        return columnNames;
    }
    
    
    public AppointmentTableModel(List<Appointment> list) {
        data = list;
    }

    public void add(List<Appointment> list) {
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
         Appointment appointment= (Appointment)data.get(rowIndex);
         DateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
         DateFormat timeFormat=new SimpleDateFormat("HH:mm");
        switch(columnNames[columnIndex]){
            /*AppointmentID Name Date Time Type Username DoctorID */
            case "AppointmentID":
                return appointment.getAppointmentID();
            case "Name":
                return appointment.getName();
            case "Date":
                return dateFormat.format(appointment.getDate());
            case "Time":
                return timeFormat.format(appointment.getTime());
            case "Type":
                return appointment.getType();
            case "Username":
                return appointment.getUsername();
            case "DoctorID":
                return appointment.getDoctorID();
            
            default:
                 return null;
        }
    }
    
    public Appointment getAppointment(int rowIndex){
        return data.get(rowIndex);
    }
    
    public void remove(int rowIndex) {
        data.remove(rowIndex);
    }  
}
