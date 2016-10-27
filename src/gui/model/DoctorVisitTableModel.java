package gui.model;

import ExceptionPackage.AppException;
import ejb.DoctorVisit;
import gui.view.AddDetailsToVisit;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javafx.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class DoctorVisitTableModel extends AbstractTableModel {

    static String[] columnNames;
    List<DoctorVisit> data;
    static Date today;
    DateFormat dateFormat;
    DateFormat timeFormat;
    AddDetailsToVisit addDetailsToVisit;
    
    public DoctorVisitTableModel(String[] colNames) {
        columnNames = colNames;
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        timeFormat = new SimpleDateFormat("HH:mm");
        today=Calendar.getInstance().getTime();
        /*"Name", "Surname", "Data e Lindjes","Numri Personal","Email","Telefoni","Qyteti"*/
    }
    
    public DoctorVisitTableModel(String[] colNames,AddDetailsToVisit addDetailsToVisit) {
        this.addDetailsToVisit=addDetailsToVisit;
        columnNames = colNames;
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        timeFormat = new SimpleDateFormat("HH:mm");
        today=Calendar.getInstance().getTime();
        /*"Name", "Surname", "Data e Lindjes","Numri Personal","Email","Telefoni","Qyteti"*/
    }

    public void setColumnNames(String[] colNames) {
        columnNames = colNames;
    }

    public static String[] getColumnNames() {
        return columnNames;
    }
    
    @Override 
    public Class<?> getColumnClass(int columnIndex) {
            if(columnIndex==6)
                return JButton.class;
            return String.class;
        }

    public DoctorVisitTableModel(List<DoctorVisit> list) {
        data = list;
    }

    public void add(List<DoctorVisit> list) {
        data = list;
    }

    @Override
    public int getRowCount() {
        if(data==null)
            return 0;
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
        DoctorVisit DoctorVisit = (DoctorVisit) data.get(rowIndex);
        switch (columnNames[columnIndex]) {
            /*DoctorVisitID Date SumPrice Remark Finished PatientID DoctorID StaffID */
            case "DoctorVisitID":
                return DoctorVisit.getDoctorVisitID();
            case "Date":
                if(dateFormat.format(DoctorVisit.getTimeStamp()).equals(dateFormat.format(today)))
                    return "Today";
                return dateFormat.format(DoctorVisit.getTimeStamp());
            case "Time":
                return timeFormat.format(DoctorVisit.getTimeStamp());
            case "SumPrice":
                return DoctorVisit.getSumPrice();
            case "Visit Type":
                    return DoctorVisit.getTypeOfVisit();
            case "Remark":
                return DoctorVisit.getRemark();
            case "Finished":
                return DoctorVisit.getFinished();
            case "PatientID":
                return DoctorVisit.getPatientID();
            case "DoctorID":
                return DoctorVisit.getDoctorID();
            case "StaffID":
                return DoctorVisit.getStaffID();
            case "Button":
                JButton jb=new JButton("Details");
                if(addDetailsToVisit!=null){
                    jb.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(java.awt.event.ActionEvent ae) {
                            if (addDetailsToVisit != null) {try{
                                addDetailsToVisit.diagnosisForVisitTableLoad(rowIndex);
                            }catch(AppException appExcep){
                                appExcep.printStackTrace();
                                JOptionPane.showMessageDialog(addDetailsToVisit, appExcep.getMessage());
                            }
                            }
                        }
                    });
                }
                return jb;
            default:
                return null;
        }
    }

    public DoctorVisit getDoctorVisit(int rowIndex) {
        return data.get(rowIndex);
    }

    public void remove(int rowIndex) {
        data.remove(rowIndex);
    }
}
