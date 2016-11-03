package gui.model;

import ExceptionPackage.AppException;
import ejb.DoctorVisit;
import gui.view.Visits;
import gui.view.SeeVisits;
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
    Visits addDetailsToVisit;
    SeeVisits seeVisits;
    
    public DoctorVisitTableModel(String[] colNames) {
        columnNames = colNames;
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        timeFormat = new SimpleDateFormat("HH:mm");
        today=Calendar.getInstance().getTime();
        /*"Name", "Surname", "Data e Lindjes","Numri Personal","Email","Telefoni","Qyteti"*/
    }
    
    public DoctorVisitTableModel(String[] colNames,Visits addDetailsToVisit) {
        this.addDetailsToVisit=addDetailsToVisit;
        columnNames = colNames;
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        timeFormat = new SimpleDateFormat("HH:mm");
        today=Calendar.getInstance().getTime();
        /*"Name", "Surname", "Data e Lindjes","Numri Personal","Email","Telefoni","Qyteti"*/
    }
    
    public DoctorVisitTableModel(String[] colNames,SeeVisits seeVisits) {
        this.seeVisits=seeVisits;
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
            if(columnIndex==6){
                return JButton.class;
            }
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
        DoctorVisit doctorVisit = (DoctorVisit) data.get(rowIndex);
        switch (columnNames[columnIndex]) {
            /*DoctorVisitID Date SumPrice Remark Finished PatientID DoctorID StaffID */
            case "DoctorVisitID":
                return doctorVisit.getDoctorVisitID();
            case "Date":
                if(dateFormat.format(doctorVisit.getTimeStamp()).equals(dateFormat.format(today)))
                    return "Today";
                return dateFormat.format(doctorVisit.getTimeStamp());
            case "Time":
                return timeFormat.format(doctorVisit.getTimeStamp());
            case "SumPrice":
                return doctorVisit.getSumPrice();
            case "Visit Type":
                    return doctorVisit.getTypeOfVisit();
            case "Remark":
                return doctorVisit.getRemark();
            case "Finished":
                return doctorVisit.getFinished();
            case "PatientID":
                return doctorVisit.getPatientID();
            case "DoctorID":
                return doctorVisit.getDoctorID();
            case "StaffID":
                return doctorVisit.getStaffID();
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
                }else if(seeVisits!=null){
                    jb.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(java.awt.event.ActionEvent ae) {
                            if (seeVisits != null) {try{
                                seeVisits.diagnosisForVisitTableLoad(rowIndex);
                            }catch(AppException appExcep){
                                appExcep.printStackTrace();
                                JOptionPane.showMessageDialog(seeVisits, appExcep.getMessage());
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

    public void clear() {
        if(data!=null)
            data.clear();
    }
}
