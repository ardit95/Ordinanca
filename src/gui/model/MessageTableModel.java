package gui.model;

import ejb.Message;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class MessageTableModel extends AbstractTableModel{
  static String [] columnNames;
    List<Message> data;
    
    public MessageTableModel(String[] colNames) {
        columnNames=colNames;
        /*"Name", "Surname", "Data e Lindjes","Numri Personal","Email","Telefoni","Qyteti"*/
    }
    
    public void setColumnNames(String[] colNames){
            columnNames=colNames;
    }

    public static String[] getColumnNames() {
        return columnNames;
    }
    
    
    public MessageTableModel(List<Message> list) {
        data = list;
    }

    public void add(List<Message> list) {
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
         Message message= (Message)data.get(rowIndex);
         DateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
         DateFormat timeFormat=new SimpleDateFormat("HH:mm");
        switch(columnNames[columnIndex]){
            
            case "MessageID":
                return message.getMessageID();
            case "Date":
                return dateFormat.format(message.getDate()); 
            case "Time":
                return timeFormat.format(message.getDate());
            case "Message":
                return message.getMessage();
            case "Username":
                return message.getUsername().getUsername();   
            case "DoctorID":
                return message.getDoctorID().getUsername();    
            
            default:
                 return null;
        }
    }
    
    public Message getMessage(int rowIndex){
        return data.get(rowIndex);
    }
    
    public void remove(int rowIndex) {
        data.remove(rowIndex);
    }  
}



