package gui.model;

import ejb.Message;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class MessageTableModel extends AbstractTableModel{
  static String [] columnNames;
    List<Message> data;
    DateFormat dateFormat;
    DateFormat timeFormat;
    
    public MessageTableModel(String[] colNames) {
        columnNames=colNames;
        dateFormat=new SimpleDateFormat("dd-MM-yyyy");
        timeFormat=new SimpleDateFormat("HH:mm");
        /*"Name", "Surname", "Data e Lindjes","Numri Personal","Email","Telefoni","Qyteti"*/
    }
    
    public boolean isEmpty(){
        if(data!=null)
            return (data.isEmpty());
        return true;
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
        String nameAndSurname;
         switch(columnNames[columnIndex]){
             
            case "MessageID":
                return message.getMessageID();
            case "Date":
                return dateFormat.format(message.getTimeStamp()); 
            case "Time":
                return timeFormat.format(message.getTimeStamp());
            case "Message":
                return message.getMessage();
            case "Reciever":
                nameAndSurname=message.getUsername().getName()+" "+message.getUsername().getSurname();
                return nameAndSurname;   
            case "Sender":
                nameAndSurname=message.getDoctorID().getName()+" "+message.getDoctorID().getSurname();
                return nameAndSurname;
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



