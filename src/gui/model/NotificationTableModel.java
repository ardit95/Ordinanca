package gui.model;

import ejb.Notification;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class NotificationTableModel extends AbstractTableModel{
  static String [] columnNames;
    List<Notification> data;
    
    public NotificationTableModel(String[] colNames) {
        columnNames=colNames;
        /*"Name", "Surname", "Data e Lindjes","Numri Personal","Email","Telefoni","Qyteti"*/
    }
    
    public void setColumnNames(String[] colNames){
            columnNames=colNames;
    }

    public static String[] getColumnNames() {
        return columnNames;
    }
    
    
    public NotificationTableModel(List<Notification> list) {
        data = list;
    }

    public void add(List<Notification> list) {
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
         Notification notification= (Notification)data.get(rowIndex);
         DateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
         DateFormat timeFormat=new SimpleDateFormat("HH:mm");
        switch(columnNames[columnIndex]){
            
            case "NotificationID":
                return notification.getNotificationID();
            case "Date":
                return dateFormat.format(notification.getDate()); 
            case "Time":
                return dateFormat.format(notification.getTime());
            case "TypeOfVisit":
                return notification.getTypeOfVisit();
            case "Seen":
                return notification.getSeen();   
            case "Username":
                return notification.getUsername().getUsername();    
            case "DoctorID":
                return notification.getDoctorID().getUsername();     
            case "PatientID":
                return notification.getPatientID().getPatientID();
            
            default:
                 return null;
        }
    }
    
    public Notification getNotification(int rowIndex){
        return data.get(rowIndex);
    }
    
    public void remove(int rowIndex) {
        data.remove(rowIndex);
    }  
}



