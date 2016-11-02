package gui.model;

import ExceptionPackage.AppException;
import ejb.AnalysisVisit;
import ejb.DoctorVisit;
import ejb.Visit;
import static gui.model.DoctorVisitTableModel.columnNames;
import static gui.model.DoctorVisitTableModel.today;
import gui.view.Visits;
import gui.view.SeeVisits;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class VisitTableModel extends AbstractTableModel {

    static String[] columnNames;
    List<Visit> data;
    DateFormat dateFormat;
    DateFormat timeFormat;
    static Date today;
    SeeVisits seeVisits;

    public VisitTableModel(String[] colNames,SeeVisits seeVisits) {
        columnNames = colNames;
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        timeFormat = new SimpleDateFormat("HH:mm");
        this.seeVisits=seeVisits;
        today=Calendar.getInstance().getTime();
    }
    
    public void clear(){
        if(data!=null)
            data.clear();
    }
    
    public VisitTableModel(List<Visit> list) {
        data = list;
        today=Calendar.getInstance().getTime();
    }

    public void setColumnNames(String[] colNames) {
        columnNames = colNames;
    }

    public static String[] getColumnNames() {
        return columnNames;
    }

    

    public void add(List<Visit> list) {
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
        Visit visit = (Visit) data.get(rowIndex);
        if(visit instanceof AnalysisVisit){
            AnalysisVisit analysisVisit=(AnalysisVisit)visit;
        switch (columnNames[columnIndex]) {
            /*VisitID Date PatientID LaboratorTechnicianID SumPrice Remark StaffID Finished */
            case "VisitID":
                return analysisVisit.getAnalysisVisitID();
            case "Date":
                return dateFormat.format(analysisVisit.getTimeStamp());
            case "Time":
                return timeFormat.format(analysisVisit.getTimeStamp());
            case "PatientID":
                return analysisVisit.getPatientID();
            case "Visit Type":
                return analysisVisit.getTypeOfVisit();
            case "LaboratorTechnicianID":
                return analysisVisit.getLaboratorTechnicianID();
            case "SumPrice":
                return analysisVisit.getSumPrice();
            case "Remark":
                return analysisVisit.getRemark();
            case "StaffID":
                return analysisVisit.getStaffID();
            case "Finished":
                return analysisVisit.getFinished();
            default:
                return null;
        }
        
        }else{
            DoctorVisit doctorVisit = (DoctorVisit)visit;
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
                if(seeVisits!=null){
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
    }

    public Visit getVisit(int rowIndex) {
        return data.get(rowIndex);
    }

    public void remove(int rowIndex) {
        data.remove(rowIndex);
    }
}
