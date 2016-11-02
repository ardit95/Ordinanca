package gui.view;

import ExceptionPackage.AppException;
import ExceptionPackage.StopException;
import bl.AnalysisForVisitInterface;
import bl.AnalysisForVisitRepository;
import bl.AnalysisInterface;
import bl.AnalysisRepository;
import bl.AnalysisVisitInterface;
import bl.AnalysisVisitRepository;
import bl.DiagnosisForVisitInterface;
import bl.DiagnosisForVisitRepository;
import bl.DiagnosisInterface;
import bl.DiagnosisRepository;
import bl.DoctorVisitInterface;
import bl.DoctorVisitRepository;
import bl.PatientInterface;
import bl.PatientRepository;
import ejb.AnalysisVisit;
import ejb.Diagnosis;
import ejb.DiagnosisForVisit;
import ejb.DoctorVisit;
import ejb.Patient;
import ejb.Staff;
import ejb.Visit;
import gui.model.AnalysisVisitTableModel;
import gui.model.DiagnosisForVisitTableModel;
import gui.model.DoctorVisitTableModel;
import gui.model.VisitTableModel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.CheckBox;
import javax.persistence.EntityManager;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;

public class SeeVisits extends javax.swing.JInternalFrame {

    EntityManager entityManager;
    Staff currentUser;
    MainFrame mainFrame;
    DoctorVisitTableModel doctorVisitTM;
    DiagnosisForVisitTableModel diagnosisForVisitTM;
    VisitTableModel visitTM;
    AnalysisVisitTableModel analysisVisitTM;
    DoctorVisitInterface doctorVisitIr;
    DiagnosisInterface diagnosisIr=new DiagnosisRepository(entityManager);
    DiagnosisForVisitInterface diagnosisForVisitIr;
    AnalysisVisitInterface analysisVisitIr;
    PatientInterface patientIr;
    AnalysisInterface analysisIr;
    AnalysisForVisitInterface analysisForVisitIr;
    Visit mainVisit;

    public SeeVisits(EntityManager entityManager,Staff currentUser,MainFrame mainFrame) {
        this.currentUser=currentUser;
        this.entityManager=entityManager;
        this.mainFrame=mainFrame;
        initComponents();
        initInterfaces();
        initTableModels();
        this.setLocation(220, 10);
        setPreferredSize(new Dimension(1100, 654));
        setMaximumSize(new Dimension(1100, 654));
        setMinimumSize(new Dimension(1100, 654));
        setMaximizable(true);
        fillTimeCombos();
        seeVisitFormListeners();
        setPriority();
        visitTableLoad();
    }
    
    public void setPriority(){
        if(currentUser.getRole().equals("Recepsion")){
            doctorVisitCBox.setSelected(true);
            analysisVisitCBox.setSelected(true);
        }else{
            doctorVisitCBox.setVisible(false);
            analysisVisitCBox.setVisible(false);
        }
    }
    
    private void clearObject() {
        infoLbl.setText("");
        infoLbl.setForeground(Color.WHITE);
        patientNameLbl.setText("");
        nameLbl.setForeground(Color.WHITE);
        patientGenderLbl.setText("");
        genderLbl.setForeground(Color.WHITE);
        patientDateOfBirthLbl.setText("");
        dateOfBirthLbl.setForeground(Color.WHITE);
        patientPlaceOfBirthLbl.setText("");
        placeOfBirthLbl.setForeground(Color.WHITE);
        patientCityLbl.setText("");
        cityLbl.setForeground(Color.WHITE);
        patientEmailLbl.setText("");
        emailLbl.setForeground(Color.WHITE);
        patientPhoneLbl.setText("");
        phoneLbl.setForeground(Color.WHITE);
        allergiesTxtf.setText("");
        allergiesLbl.setForeground(Color.WHITE);
        remarkTxtf.setText("");
        remarkLbl.setForeground(Color.WHITE);
        mainVisit=null;
        visitTbl.clearSelection();
    }
    
    public void visitTblListeners(){
        tableMoveKey();
        clickMoveKey();
    }
    
    private void initInterfaces() {
        doctorVisitIr=new DoctorVisitRepository(entityManager);
        diagnosisIr=new DiagnosisRepository(entityManager);
        diagnosisForVisitIr= new DiagnosisForVisitRepository(entityManager);
        analysisVisitIr=new AnalysisVisitRepository(entityManager);
        patientIr= new PatientRepository(entityManager);
        analysisIr=new AnalysisRepository(entityManager);
        analysisForVisitIr=new AnalysisForVisitRepository(entityManager);
    }
    
    private void initTableModels(){
        String[] doctorVisitColumns={"PatientID","Visit Type","Date","Time","SumPrice","Finished","Button"};
        doctorVisitTM=new DoctorVisitTableModel(doctorVisitColumns,this);
        String[] diagnosisForVisitColumns={"Complaint","Examination","Therapy","CurrentPrice"};
        diagnosisForVisitTM=new DiagnosisForVisitTableModel(diagnosisForVisitColumns);
        String[] analysisVisitColumns={"PatientID","Visit Type","Date","Time","SumPrice","Finished"};
        analysisVisitTM= new AnalysisVisitTableModel(analysisVisitColumns);
        String[] visitColumns={"PatientID","Visit Type","Date","Time","SumPrice","Finished"};
        visitTM= new VisitTableModel(visitColumns,this);
    }
    
    public void diagnosisForVisitTableLoad(int row)throws AppException{
        List<DiagnosisForVisit> diagnosisForVisitList=diagnosisForVisitIr.findByVisit(doctorVisitTM.getDoctorVisit(row));
        if(diagnosisForVisitList==null || diagnosisForVisitList.isEmpty())
            throw new AppException("There's no diagnosis for this patient.");
        diagnosisForVisitTM.add(diagnosisForVisitList);
        visitTbl.setModel(diagnosisForVisitTM);
        diagnosisForVisitTM.fireTableDataChanged();
    }

    public void tableMoveKey() {

        ListSelectionModel visitTblLSM = visitTbl.getSelectionModel();
        visitTblLSM.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (lse.getValueIsAdjusting()) {
                    return;
                }
                ListSelectionModel rowSM = (ListSelectionModel) lse.getSource();
                int selectedIndex = rowSM.getMinSelectionIndex();
                if (selectedIndex > -1) {
                    Patient patient = null;
                    if(currentUser.getRole().equals("Recepsion")){
                        if(visitTbl.getModel()==visitTM){
                            try{
                            mainVisit=visitTM.getVisit(selectedIndex);
                            if(mainVisit instanceof DoctorVisit){
                                patient=((DoctorVisit)mainVisit).getPatientID();
                            }else 
                                patient=((AnalysisVisit)mainVisit).getPatientID();
                            
                            if (patient == null) {
                                    String[] opcionet = {"Yes", "No"};
                                    int response = JOptionPane.showOptionDialog(null,
                                            "You have to assign a pacient to a visit before giving a diagnosis to it. Press yes to add the patient and no to stop.", "Warning",
                                            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                                            null, opcionet, opcionet[0]);
                                    if (response == 0) {
                                        SetPatientToVisit setPatientToVisit = new SetPatientToVisit(mainVisit, entityManager, currentUser,mainFrame,SeeVisits.this);
                                        setPatientToVisit.setVisible(true);
                                        patientNameLbl.setText("");
                                        nameLbl.setForeground(Color.WHITE);
                                        patientGenderLbl.setText("");
                                        genderLbl.setForeground(Color.WHITE);
                                        patientDateOfBirthLbl.setText("");
                                        dateOfBirthLbl.setForeground(Color.WHITE);
                                        patientPlaceOfBirthLbl.setText("");
                                        placeOfBirthLbl.setForeground(Color.WHITE);
                                        patientCityLbl.setText("");
                                        cityLbl.setForeground(Color.WHITE);
                                        patientEmailLbl.setText("");
                                        emailLbl.setForeground(Color.WHITE);
                                        patientPhoneLbl.setText("");
                                        phoneLbl.setForeground(Color.WHITE);
                                        allergiesTxtf.setText("");
                                        allergiesLbl.setForeground(Color.WHITE);
                                        remarkTxtf.setText("");
                                        remarkLbl.setForeground(Color.WHITE);
                                        visitTbl.clearSelection();
                                        throw new AppException("The user choose to assign a pacient");
                                    } else {
                                        visitTbl.clearSelection();
                                        throw new StopException("The user doesn't want to continue");
                                    }
                                }
                            } catch (AppException ex) {
                                ex.printStackTrace();
                            } catch (StopException se) {
                                se.printStackTrace();
                            }
                            
                        }
                        
                    }else if (currentUser.getRole().equals("Doctor")) {
                        if (visitTbl.getModel() == doctorVisitTM) {
                            try {
                                mainVisit = doctorVisitTM.getDoctorVisit(selectedIndex);
                                patient = ((DoctorVisit)mainVisit).getPatientID();

                                if (patient == null) {
                                    String[] opcionet = {"Yes", "No"};
                                    int response = JOptionPane.showOptionDialog(null,
                                            "You have to assign a pacient to a visit before giving a diagnosis to it. Press yes to add the patient and no to stop.", "Warning",
                                            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                                            null, opcionet, opcionet[0]);
                                    if (response == 0) {
                                        SetPatientToVisit setPatientToVisit = new SetPatientToVisit(mainVisit, entityManager, currentUser,mainFrame,SeeVisits.this);
                                        setPatientToVisit.setVisible(true);
                                        patientNameLbl.setText("");
                                        nameLbl.setForeground(Color.WHITE);
                                        patientGenderLbl.setText("");
                                        genderLbl.setForeground(Color.WHITE);
                                        patientDateOfBirthLbl.setText("");
                                        dateOfBirthLbl.setForeground(Color.WHITE);
                                        patientPlaceOfBirthLbl.setText("");
                                        placeOfBirthLbl.setForeground(Color.WHITE);
                                        patientCityLbl.setText("");
                                        cityLbl.setForeground(Color.WHITE);
                                        patientEmailLbl.setText("");
                                        emailLbl.setForeground(Color.WHITE);
                                        patientPhoneLbl.setText("");
                                        phoneLbl.setForeground(Color.WHITE);
                                        allergiesTxtf.setText("");
                                        allergiesLbl.setForeground(Color.WHITE);
                                        remarkTxtf.setText("");
                                        remarkLbl.setForeground(Color.WHITE);
                                        visitTbl.clearSelection();
                                        throw new AppException("The user choose to assign a pacient");
                                    } else {
                                        visitTbl.clearSelection();
                                        throw new StopException("The user doesn't want to continue");
                                    }
                                }
                            } catch (AppException ex) {
                                ex.printStackTrace();
                            } catch (StopException se) {
                                se.printStackTrace();
                            }

                        } else if (visitTbl.getModel() == diagnosisForVisitTM) {
                            DiagnosisForVisit diagnosisForVisit = diagnosisForVisitTM.getDiagnosisForVisit(visitTbl.getSelectedRow());
                            Diagnosis diagnosis = diagnosisForVisitTM.getDiagnosisForVisit(visitTbl.getSelectedRow()).getDiagnosisID();
                            patient = diagnosisForVisit.getDoctorVisitID().getPatientID();
                        }
                    } else if (currentUser.getRole().equals("LaboratorTechnician")) {
                        try {
                            if (visitTbl.getModel() == analysisVisitTM) {
                                mainVisit = analysisVisitTM.getAnalysisVisit(selectedIndex);
                                patient = ((AnalysisVisit)mainVisit).getPatientID();
                                if (patient == null) {
                                    String[] opcionet = {"Yes", "No"};
                                    int response = JOptionPane.showOptionDialog(null,
                                            "You have to assign a pacient to a visit before doing an analysis to it. Press yes to add the patient and no to stop.", "Warning",
                                            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                                            null, opcionet, opcionet[0]);
                                    if (response == 0) {
                                        SetPatientToVisit setPatientToVisit = new SetPatientToVisit(mainVisit, entityManager, currentUser,mainFrame,SeeVisits.this);
                                        setPatientToVisit.setVisible(true);
                                        patientNameLbl.setText("");
                                        nameLbl.setForeground(Color.WHITE);
                                        patientGenderLbl.setText("");
                                        genderLbl.setForeground(Color.WHITE);
                                        patientDateOfBirthLbl.setText("");
                                        dateOfBirthLbl.setForeground(Color.WHITE);
                                        patientPlaceOfBirthLbl.setText("");
                                        placeOfBirthLbl.setForeground(Color.WHITE);
                                        patientCityLbl.setText("");
                                        cityLbl.setForeground(Color.WHITE);
                                        patientEmailLbl.setText("");
                                        emailLbl.setForeground(Color.WHITE);
                                        patientPhoneLbl.setText("");
                                        phoneLbl.setForeground(Color.WHITE);
                                        allergiesTxtf.setText("");
                                        allergiesLbl.setForeground(Color.WHITE);
                                        remarkTxtf.setText("");
                                        remarkLbl.setForeground(Color.WHITE);
                                        visitTbl.clearSelection();
                                        throw new AppException("The user choose to assign a pacient");
                                    } else {
                                        visitTbl.clearSelection();
                                        throw new StopException("The user doesn't want to continue");
                                    }
                                }
                            }
                        } catch (AppException ae) {
                        } catch (StopException se) {
                        }
                    }
                    setPatientData(patient);
                }
            }
        });
    }
    
    public void setPatientData(Patient patient) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        patientNameLbl.setText(patient.getName() + " (" + patient.getParentName() + ") " + patient.getSurname());
        nameLbl.setForeground(Color.WHITE);
        patientGenderLbl.setText(patient.getGender());
        genderLbl.setForeground(Color.WHITE);
        patientDateOfBirthLbl.setText(dateFormat.format(patient.getDateOfBirth()));
        dateOfBirthLbl.setForeground(Color.WHITE);
        patientPlaceOfBirthLbl.setText(patient.getPlaceOFBirth());
        placeOfBirthLbl.setForeground(Color.WHITE);
        patientCityLbl.setText(patient.getCity());
        cityLbl.setForeground(Color.WHITE);
        patientEmailLbl.setText(patient.getEmail());
        emailLbl.setForeground(Color.WHITE);
        patientPhoneLbl.setText(patient.getPhone());
        phoneLbl.setForeground(Color.WHITE);
        allergiesTxtf.setText(patient.getAllergies());
        allergiesLbl.setForeground(Color.WHITE);
        Date date;
        if(currentUser.getRole().equals("Doctor")){
            remarkTxtf.setText(((DoctorVisit)mainVisit).getRemark());
            date=((DoctorVisit)mainVisit).getTimeStamp();
            dateCalendar.setDate(date);
            hourCombo.setSelectedIndex(date.getHours()+1);
            minuteCombo.setSelectedIndex(date.getMinutes()+1);
            
        }
        else if (currentUser.getRole().equals("LaboratorTechnician")){
            remarkTxtf.setText(((AnalysisVisit)mainVisit).getRemark());
            date=((AnalysisVisit)mainVisit).getTimeStamp();
            dateCalendar.setDate(date);
            hourCombo.setSelectedIndex(date.getHours()+1);
            minuteCombo.setSelectedIndex(date.getMinutes()+1);
        }
        else if (currentUser.getRole().equals("Recepsion")){
            if(mainVisit instanceof DoctorVisit){
                remarkTxtf.setText(((DoctorVisit)mainVisit).getRemark());
            date=((DoctorVisit)mainVisit).getTimeStamp();
            dateCalendar.setDate(date);
            hourCombo.setSelectedIndex(date.getHours()+1);
            minuteCombo.setSelectedIndex(date.getMinutes()+1);
            }else {
                remarkTxtf.setText(((AnalysisVisit)mainVisit).getRemark());
            date=((AnalysisVisit)mainVisit).getTimeStamp();
                dateCalendar.setDate(date);
                hourCombo.setSelectedIndex(date.getHours()+1);
                minuteCombo.setSelectedIndex(date.getMinutes()+1);
            }
        }
        remarkLbl.setForeground(Color.WHITE);
    }
    
    private void fillTimeCombos() {
        minuteCombo.addItem("Choose");
        hourCombo.addItem("Choose");
        for(int i=0;i<60;i++){
            minuteCombo.addItem(Integer.toString(i));
            if(i<24)
                hourCombo.addItem(Integer.toString(i));
        }
    }
    
    public void visitTableLoad(){
        visitTM.clear();
        if(currentUser.getRole().equals("Recepsion")){
            List<Visit> visitsList= new ArrayList<Visit>();
            if(doctorVisitCBox.isSelected()){
                if(seeAllCBox.isSelected()){
                    visitsList.addAll(doctorVisitIr.findAll());
                }else {
                    visitsList.addAll(doctorVisitIr.findPresentAndFuture());
                }
            }
            if(analysisVisitCBox.isSelected()){
                if(seeAllCBox.isSelected()){
                    visitsList.addAll(analysisVisitIr.findAll());
                }else{
                    visitsList.addAll(analysisVisitIr.findPresentAndFuture());
                }
            }
                visitTM.add(visitsList);
                visitTbl.setModel(visitTM);
                visitTM.fireTableDataChanged();
                    
            
        }else if(currentUser.getRole().equals("Doctor")){
            doctorVisitTM.clear();
            List<DoctorVisit> visitList;
            if (seeAllCBox.isSelected()) {
                visitList = doctorVisitIr.findAllForCurrentUser(currentUser);
            } else {
                visitList = doctorVisitIr.findPresentAndFutureForCurrentUser(currentUser);
            }

            visitTbl.setModel(doctorVisitTM);
            doctorVisitTM.add(visitList);
            doctorVisitTM.fireTableDataChanged();

            TableCellRenderer buttonRenderer = new SeeVisits.JTableButtonRenderer();
            visitTbl.getColumn("Button").setCellRenderer(buttonRenderer);
        }else if (currentUser.getRole().equals("LaboratorTechnician")){
            analysisVisitTM.clear();
            List<AnalysisVisit> analysisVisit;
            if(seeAllCBox.isSelected()){
                analysisVisit= analysisVisitIr.findAllForCurrentUser(currentUser);
            }else{
                analysisVisit= analysisVisitIr.findPresentAndFutureForCurrentUser(currentUser);
            }
            visitTbl.setModel(analysisVisitTM);
            analysisVisitTM.add(analysisVisit);
            
            analysisVisitTM.fireTableDataChanged();
        }
    }

    private void validation()throws AppException {
        if(visitTbl.getSelectedRow()==-1)
            throw new AppException ("You have to select the visit you want to edit.");
    }

    private void seeVisitFormListeners() {
        checkBoxListener(seeAllCBox);
        checkBoxListener(doctorVisitCBox);
        checkBoxListener(analysisVisitCBox);
        visitTblListeners();
        checkBoxListener();
        
    }
    
    private void checkBoxListener(JCheckBox target){
        target.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ie){
                visitTableLoad();
            }
        });
    }

     public void checkBoxListener(){
        autoDateCBox.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ie) {
                boolean state=!autoDateCBox.isSelected();
                    
                    hourCombo.setEnabled(state);
                    minuteCombo.setEnabled(state);
                    dateCalendar.setEnabled(state);
            }
            
        });
    }
    
    
    private static class JTableButtonRenderer implements TableCellRenderer {        
        @Override public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JButton button = (JButton)value;
            return button;  
        }
    }
    
    public void clickMoveKey() {

        visitTbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (visitTbl.getModel() == doctorVisitTM) {
                    if (e.getButton() == 1) {
                        if ((visitTbl.getColumnModel().getColumnIndexAtX(e.getX())) == 6) {
                            int column = visitTbl.getColumnModel().getColumnIndexAtX(e.getX()); // get the coloum of the button
                            int row = e.getY() / visitTbl.getRowHeight(); //get the row of the button

                            //Checking the row or column is valid or not
                            if (row < visitTbl.getRowCount() && row >= 0 && column < visitTbl.getColumnCount() && column >= 0) {
                                Object value = visitTbl.getValueAt(row, column);
                                if (value instanceof JButton) {
                                    //perform a click event
                                    ((JButton) value).doClick();
                                }
                            }
                        } else {
                            try {
                                int row = visitTbl.getSelectedRow();
                                if (diagnosisForVisitTM.isEmpty()) {
                                    throw new AppException("There is no diagnosis for this visit.");
                                }
                                if (!infoLbl.getText().equals("")) {
                                    infoLbl.setText("");
                                    infoLbl.setForeground(Color.WHITE);
                                }
                            } catch (AppException ae) {
                                infoLbl.setText(ae.getMessage());
                                infoLbl.setForeground(Color.RED);
                            }
                        }
                    }
                } else if (e.getButton() == 3) {
                    visitTableLoad();
                    clearObject();
                }
            }
        });
    }
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backgroundPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        nameLbl = new javax.swing.JLabel();
        patientNameLbl = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        genderLbl = new javax.swing.JLabel();
        dateOfBirthLbl = new javax.swing.JLabel();
        patientGenderLbl = new javax.swing.JLabel();
        patientDateOfBirthLbl = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        placeOfBirthLbl = new javax.swing.JLabel();
        cityLbl = new javax.swing.JLabel();
        patientPlaceOfBirthLbl = new javax.swing.JLabel();
        patientCityLbl = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        phoneLbl = new javax.swing.JLabel();
        emailLbl = new javax.swing.JLabel();
        patientPhoneLbl = new javax.swing.JLabel();
        patientEmailLbl = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        allergiesTxtf = new javax.swing.JTextArea();
        allergiesLbl = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        remarkTxtf = new javax.swing.JTextArea();
        remarkLbl = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        staffLbl1 = new javax.swing.JLabel();
        dateCalendar = new com.toedter.calendar.JDateChooser();
        autoDateCBox = new javax.swing.JCheckBox();
        hourLbl = new javax.swing.JLabel();
        hourCombo = new javax.swing.JComboBox<>();
        minuteLbl = new javax.swing.JLabel();
        minuteCombo = new javax.swing.JComboBox<>();
        seeAllCBox = new javax.swing.JCheckBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        visitTbl = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        clearBtn = new javax.swing.JButton();
        changePatientBtn = new javax.swing.JButton();
        changeDetailsBtn = new javax.swing.JButton();
        doctorVisitCBox = new javax.swing.JCheckBox();
        analysisVisitCBox = new javax.swing.JCheckBox();
        infoLbl = new javax.swing.JLabel();

        setClosable(true);
        setTitle("See Visits");
        setMaximumSize(new java.awt.Dimension(1100, 654));
        setMinimumSize(new java.awt.Dimension(1100, 654));
        setPreferredSize(new java.awt.Dimension(1100, 654));

        backgroundPanel.setBackground(new java.awt.Color(102, 102, 102));
        backgroundPanel.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));

        jPanel7.setBackground(new java.awt.Color(102, 102, 102));
        jPanel7.setOpaque(false);

        nameLbl.setForeground(new java.awt.Color(255, 255, 255));
        nameLbl.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        nameLbl.setText("Name:");

        patientNameLbl.setMaximumSize(new java.awt.Dimension(40, 14));
        patientNameLbl.setMinimumSize(new java.awt.Dimension(40, 14));
        patientNameLbl.setPreferredSize(new java.awt.Dimension(40, 14));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(nameLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(patientNameLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLbl)
                    .addComponent(patientNameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1))
        );

        jPanel8.setBackground(new java.awt.Color(102, 102, 102));
        jPanel8.setOpaque(false);

        genderLbl.setForeground(new java.awt.Color(255, 255, 255));
        genderLbl.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        genderLbl.setText("Gender:");

        dateOfBirthLbl.setForeground(new java.awt.Color(255, 255, 255));
        dateOfBirthLbl.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        dateOfBirthLbl.setText("DateOfBirth:");

        patientGenderLbl.setMaximumSize(new java.awt.Dimension(40, 14));
        patientGenderLbl.setMinimumSize(new java.awt.Dimension(40, 14));
        patientGenderLbl.setPreferredSize(new java.awt.Dimension(40, 14));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(genderLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(patientGenderLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateOfBirthLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(patientDateOfBirthLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(genderLbl)
            .addComponent(patientGenderLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(dateOfBirthLbl, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(patientDateOfBirthLbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel9.setBackground(new java.awt.Color(102, 102, 102));
        jPanel9.setOpaque(false);

        placeOfBirthLbl.setForeground(new java.awt.Color(255, 255, 255));
        placeOfBirthLbl.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        placeOfBirthLbl.setText("PlaceOFBirth:");

        cityLbl.setForeground(new java.awt.Color(255, 255, 255));
        cityLbl.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        cityLbl.setText("City:");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(placeOfBirthLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(patientPlaceOfBirthLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(cityLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(patientCityLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(placeOfBirthLbl)
                    .addComponent(patientPlaceOfBirthLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(cityLbl))
            .addComponent(patientCityLbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel11.setBackground(new java.awt.Color(102, 102, 102));
        jPanel11.setOpaque(false);

        phoneLbl.setForeground(new java.awt.Color(255, 255, 255));
        phoneLbl.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        phoneLbl.setText("Phone:");

        emailLbl.setForeground(new java.awt.Color(255, 255, 255));
        emailLbl.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        emailLbl.setText("Email:");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(phoneLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(patientPhoneLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(emailLbl)
                .addGap(5, 5, 5)
                .addComponent(patientEmailLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(phoneLbl)
                    .addComponent(patientPhoneLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(emailLbl))
            .addComponent(patientEmailLbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        allergiesTxtf.setColumns(20);
        allergiesTxtf.setRows(5);
        jScrollPane1.setViewportView(allergiesTxtf);

        allergiesLbl.setForeground(new java.awt.Color(255, 255, 255));
        allergiesLbl.setText("Allergies:");

        remarkTxtf.setColumns(20);
        remarkTxtf.setRows(5);
        jScrollPane2.setViewportView(remarkTxtf);

        remarkLbl.setForeground(new java.awt.Color(255, 255, 255));
        remarkLbl.setText("Remark:");

        jPanel5.setOpaque(false);

        staffLbl1.setForeground(new java.awt.Color(255, 255, 255));
        staffLbl1.setText("Time of Visit:");

        dateCalendar.setDateFormatString("dd-MM-yyyy");

        autoDateCBox.setForeground(new java.awt.Color(255, 255, 255));
        autoDateCBox.setText("Now");

        hourLbl.setForeground(new java.awt.Color(255, 255, 255));
        hourLbl.setText("hour:");

        minuteLbl.setForeground(new java.awt.Color(255, 255, 255));
        minuteLbl.setText("minute:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(staffLbl1)
                .addGap(3, 3, 3)
                .addComponent(dateCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hourLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hourCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(minuteLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(minuteCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(autoDateCBox)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(autoDateCBox)
                        .addComponent(hourLbl)
                        .addComponent(hourCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(minuteLbl)
                        .addComponent(minuteCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dateCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(staffLbl1)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(allergiesLbl))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(remarkLbl))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(allergiesLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(remarkLbl)
                .addGap(4, 4, 4)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1))
        );

        backgroundPanel.add(jPanel2);
        jPanel2.setBounds(0, 0, 520, 370);

        seeAllCBox.setText("All");
        backgroundPanel.add(seeAllCBox);
        seeAllCBox.setBounds(520, 10, 37, 23);

        visitTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        visitTbl.setShowHorizontalLines(false);
        visitTbl.setShowVerticalLines(false);
        jScrollPane3.setViewportView(visitTbl);

        backgroundPanel.add(jScrollPane3);
        jScrollPane3.setBounds(520, 50, 550, 510);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        clearBtn.setBackground(new java.awt.Color(0, 153, 102));
        clearBtn.setForeground(new java.awt.Color(204, 255, 204));
        clearBtn.setText("Clear");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        changePatientBtn.setBackground(new java.awt.Color(0, 153, 102));
        changePatientBtn.setForeground(new java.awt.Color(204, 255, 204));
        changePatientBtn.setText("Change Patient");
        changePatientBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changePatientBtnActionPerformed(evt);
            }
        });

        changeDetailsBtn.setBackground(new java.awt.Color(0, 153, 102));
        changeDetailsBtn.setForeground(new java.awt.Color(204, 255, 204));
        changeDetailsBtn.setText("Change Details");
        changeDetailsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeDetailsBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(changeDetailsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(changePatientBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clearBtn)
                    .addComponent(changePatientBtn)
                    .addComponent(changeDetailsBtn))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        backgroundPanel.add(jPanel1);
        jPanel1.setBounds(530, 580, 530, 23);

        doctorVisitCBox.setText("Visits");
        backgroundPanel.add(doctorVisitCBox);
        doctorVisitCBox.setBounds(570, 10, 49, 23);

        analysisVisitCBox.setText("Analysis");
        backgroundPanel.add(analysisVisitCBox);
        analysisVisitCBox.setBounds(630, 10, 70, 23);

        infoLbl.setMaximumSize(new java.awt.Dimension(34, 14));
        infoLbl.setMinimumSize(new java.awt.Dimension(34, 14));
        infoLbl.setPreferredSize(new java.awt.Dimension(34, 14));
        backgroundPanel.add(infoLbl);
        infoLbl.setBounds(560, 10, 510, 14);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1080, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(backgroundPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1080, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 747, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(backgroundPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void changeDetailsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeDetailsBtnActionPerformed
        if(visitTbl.getSelectedRow()!=-1){
        
        }else
            try {
                throw new AppException("");
        } catch (AppException ex) {
            Logger.getLogger(SeeVisits.class.getName()).log(Level.SEVERE, null, ex);
        }
        clearObject();
    }//GEN-LAST:event_changeDetailsBtnActionPerformed

    private void changePatientBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changePatientBtnActionPerformed
        mainFrame.keepRunning=false;
        try {
            validation();
            
            
            clearObject();
            
            JOptionPane.showMessageDialog(this, "The diagnosis was added sucsesfully to this visit.");
            
            throw new StopException ("");
            
        } catch (AppException ae) {
            ae.printStackTrace();
            JOptionPane.showMessageDialog(this, ae.getMessage());
        }catch(StopException se){
            se.printStackTrace();
        }

        synchronized(mainFrame.messagesThread){
            mainFrame.keepRunning = true;
            mainFrame.messagesThread.notifyAll();
        }
    }//GEN-LAST:event_changePatientBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        clearObject();
    }//GEN-LAST:event_clearBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel allergiesLbl;
    private javax.swing.JTextArea allergiesTxtf;
    private javax.swing.JCheckBox analysisVisitCBox;
    private javax.swing.JCheckBox autoDateCBox;
    private javax.swing.JPanel backgroundPanel;
    private javax.swing.JButton changeDetailsBtn;
    private javax.swing.JButton changePatientBtn;
    private javax.swing.JLabel cityLbl;
    private javax.swing.JButton clearBtn;
    private com.toedter.calendar.JDateChooser dateCalendar;
    private javax.swing.JLabel dateOfBirthLbl;
    private javax.swing.JCheckBox doctorVisitCBox;
    private javax.swing.JLabel emailLbl;
    private javax.swing.JLabel genderLbl;
    private javax.swing.JComboBox<String> hourCombo;
    private javax.swing.JLabel hourLbl;
    private javax.swing.JLabel infoLbl;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JComboBox<String> minuteCombo;
    private javax.swing.JLabel minuteLbl;
    private javax.swing.JLabel nameLbl;
    private javax.swing.JLabel patientCityLbl;
    private javax.swing.JLabel patientDateOfBirthLbl;
    private javax.swing.JLabel patientEmailLbl;
    private javax.swing.JLabel patientGenderLbl;
    private javax.swing.JLabel patientNameLbl;
    private javax.swing.JLabel patientPhoneLbl;
    private javax.swing.JLabel patientPlaceOfBirthLbl;
    private javax.swing.JLabel phoneLbl;
    private javax.swing.JLabel placeOfBirthLbl;
    private javax.swing.JLabel remarkLbl;
    private javax.swing.JTextArea remarkTxtf;
    private javax.swing.JCheckBox seeAllCBox;
    private javax.swing.JLabel staffLbl1;
    private javax.swing.JTable visitTbl;
    // End of variables declaration//GEN-END:variables
}
