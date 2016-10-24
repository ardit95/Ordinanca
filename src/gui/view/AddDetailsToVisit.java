package gui.view;

import ExceptionPackage.AppException;
import ExceptionPackage.StopException;
import bl.DiagnosisForVisitInterface;
import bl.DiagnosisForVisitRepository;
import bl.DiagnosisInterface;
import bl.DiagnosisRepository;
import bl.DoctorVisitInterface;
import bl.DoctorVisitRepository;
import bl.PatientInterface;
import bl.PatientRepository;
import ejb.Diagnosis;
import ejb.DiagnosisForVisit;
import ejb.DoctorVisit;
import ejb.Patient;
import ejb.Staff;
import gui.model.DiagnosisForVisitTableModel;
import gui.model.DoctorVisitTableModel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.DateFormatter;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.text.DefaultCaret;

public class AddDetailsToVisit extends javax.swing.JInternalFrame {
EntityManager entityManager;
Staff currentUser;

DoctorVisitInterface doctorVisitIr;
DiagnosisInterface diagnosisIr;
DiagnosisForVisitInterface diagnosisForVisitIr;
PatientInterface patientIr;
DoctorVisitTableModel doctorVisitTM;
DiagnosisForVisitTableModel diagnosisForVisitTM;
DoctorVisit mainDoctorVisit;

    public AddDetailsToVisit(EntityManager entityManager,Staff currentUser) {
        this.currentUser=currentUser;
        this.entityManager=entityManager;
        initComponents();
        initInterfaces(this.entityManager);
        initTableModels();
        setLocation(220, 10);
        visitTableLoad();
        addDetailsToVisitFormListeners();
        visitTblListeners();
        mainScrollBarMethods();
    }
    
    
    
    public void mainScrollBarMethods(){
        setScrollPosition();
        changeTab();
        mainScrollPane.getVerticalScrollBar().setUnitIncrement(16);
    }
    
    public void visitTblListeners(){
        tableMoveKey();
        clickMoveKey();
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
                    if (visitTbl.getModel() == doctorVisitTM) {
                        try {
                            mainDoctorVisit = doctorVisitTM.getDoctorVisit(selectedIndex);
                            Patient patient = mainDoctorVisit.getPatientID();
                            if (patient == null) {
                                String[] opcionet = {"Yes", "No"};
                                int response = JOptionPane.showOptionDialog(null,
                                        "You have to assign a pacient to a visit before giving a diagnosis to it. Press yes to add the patient and no to stop.", "Warning",
                                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                                        null, opcionet, opcionet[0]);
                                if (response == 0) {
                                    SetPatientToVisit setPatientToVisit = new SetPatientToVisit(AddDetailsToVisit.this, mainDoctorVisit, entityManager, currentUser);
                                    setPatientToVisit.setVisible(true);

                                    patientNameLbl.setText("");
                                    nameLbl.setForeground(Color.BLACK);
                                    patientGenderLbl.setText("");
                                    genderLbl.setForeground(Color.BLACK);
                                    patientDateOfBirthLbl.setText("");
                                    dateOfBirthLbl.setForeground(Color.BLACK);
                                    patientPlaceOfBirthLbl.setText("");
                                    placeOfBirthLbl.setForeground(Color.BLACK);
                                    patientCityLbl.setText("");
                                    cityLbl.setForeground(Color.BLACK);
                                    patientEmailLbl.setText("");
                                    emailLbl.setForeground(Color.BLACK);
                                    patientPhoneLbl.setText("");
                                    phoneLbl.setForeground(Color.BLACK);
                                    allergiesTxtf.setText("");
                                    allergiesLbl.setForeground(Color.BLACK);
                                    remarkTxtf.setText("");
                                    remarkLbl.setForeground(Color.BLACK);
                                    throw new AppException("The user choose to assign a pacient");
                                } else {
                                    throw new StopException("The user doesn't want to continue");
                                }
                            }
                            setPatientData(patient);
                        } catch (AppException ex) {
                            ex.printStackTrace();
                        } catch (StopException se) {
                            se.printStackTrace();
                        }

                    } else if (visitTbl.getModel() == diagnosisForVisitTM) {
                        Diagnosis diagnosis = diagnosisForVisitTM.getDiagnosisForVisit(visitTbl.getSelectedRow()).getDiagnosisID();
                        complaintTxtf.setText(diagnosis.getComplaint());
                        complaintLbl.setForeground(Color.BLUE);
                        anamnesisTxtf.setText(diagnosis.getAnamnesis());
                        anamensisLbl.setForeground(Color.BLUE);
                        examinationTxtf.setText(diagnosis.getExamination());
                        examinationLbl.setForeground(Color.BLUE);
                        therapyTxtf.setText(diagnosis.getTherapy());
                        therapyLbl.setForeground(Color.BLUE);
                        recommendationTxtf.setText(diagnosis.getRecommendation());
                        recommendationLbl.setForeground(Color.BLUE);
                    }
                }
            }
        });
    }
    
    public void clickMoveKey() {

        visitTbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (visitTbl.getModel() == doctorVisitTM) {
                    if (e.getButton() == 1) {
                        try {
                            int row = visitTbl.getSelectedRow();
                            diagnosisForVisitTableLoad(row);
                            if (!infoLbl.getText().equals("")) {
                                infoLbl.setText("");
                                infoLbl.setForeground(Color.BLACK);
                            }
                        } catch (AppException ae) {
                            infoLbl.setText(ae.getMessage());
                            infoLbl.setForeground(Color.RED);
                        }
                    }
                } else if (e.getButton() == 3) {
                    visitTableLoad();
                }

            }
        });
    }
    
    public void diagnosisForVisitTableLoad(int row)throws AppException{
        diagnosisForVisitTM.add(diagnosisForVisitIr.findByVisit(doctorVisitTM.getDoctorVisit(row)));
        if(diagnosisForVisitTM.isEmpty())
            throw new AppException("There is no diagnosis for this visit.");
        // niher per niher ma vone kqyrim visitTbl.setModel(diagnosisForVisitTM);
        //niher per niher ma vone kqyrim  diagnosisForVisitTM.fireTableDataChanged();
    }
    
    public void visitTableLoad(){
        List <DoctorVisit> visitList;
        if(seeAllCBox.isSelected())
            visitList= doctorVisitIr.findAllForCurrentUser(currentUser);
        else 
            visitList= doctorVisitIr.findPresentAndFuture(currentUser);
        
        visitTbl.setModel(doctorVisitTM);
        doctorVisitTM.add(visitList);
        doctorVisitTM.fireTableDataChanged();
    }
    
    private void initInterfaces(EntityManager entityManager) {
        doctorVisitIr=new DoctorVisitRepository(entityManager);
        diagnosisIr=new DiagnosisRepository(entityManager);
        diagnosisForVisitIr= new DiagnosisForVisitRepository(entityManager);
        patientIr= new PatientRepository(entityManager);
    }
    
    private void initTableModels(){
        String[] doctorVisitColumns={"Date","SumPrice","Remark","Finished","PatientID","DoctorID","StaffID"};
        doctorVisitTM=new DoctorVisitTableModel(doctorVisitColumns);
        String[] diagnosisForVisitColumns={"DiagnosisForVisitID","CurrentPrice","DiagnosisID","DoctorVisitID","Patient","Complaint","Examination","Therapy","Date","Price"};
        diagnosisForVisitTM=new DiagnosisForVisitTableModel(diagnosisForVisitColumns);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backgroundLbl = new javax.swing.JPanel();
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
        mainScrollPane = new javax.swing.JScrollPane();
        mainPanel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        complaintTxtf = new javax.swing.JTextArea();
        complaintLbl = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        anamnesisTxtf = new javax.swing.JTextArea();
        anamensisLbl = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        examinationTxtf = new javax.swing.JTextArea();
        examinationLbl = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        therapyTxtf = new javax.swing.JTextArea();
        therapyLbl = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        recommendationTxtf = new javax.swing.JTextArea();
        recommendationLbl = new javax.swing.JLabel();
        priceTxtf = new javax.swing.JTextField();
        priceLbl = new javax.swing.JLabel();
        seeAllCBox = new javax.swing.JCheckBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        visitTbl = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        clearBtn = new javax.swing.JButton();
        saveBtn = new javax.swing.JButton();
        infoLbl = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Add Notification");
        setMaximumSize(new java.awt.Dimension(1100, 654));
        setMinimumSize(new java.awt.Dimension(1100, 654));
        setPreferredSize(new java.awt.Dimension(1100, 654));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        backgroundLbl.setBackground(new java.awt.Color(102, 102, 102));
        backgroundLbl.setLayout(null);

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
                        .addComponent(remarkLbl)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        backgroundLbl.add(jPanel2);
        jPanel2.setBounds(0, 0, 502, 334);

        mainScrollPane.setBackground(new java.awt.Color(102, 102, 102));
        mainScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        mainPanel.setBackground(new java.awt.Color(102, 102, 102));

        jPanel4.setMaximumSize(new java.awt.Dimension(164, 94));
        jPanel4.setMinimumSize(new java.awt.Dimension(164, 94));
        jPanel4.setOpaque(false);

        jScrollPane5.setMaximumSize(new java.awt.Dimension(166, 56));
        jScrollPane5.setMinimumSize(new java.awt.Dimension(166, 56));
        jScrollPane5.setPreferredSize(new java.awt.Dimension(166, 56));
        

        complaintTxtf.setColumns(20);
        complaintTxtf.setLineWrap(true);
        complaintTxtf.setRows(5);
        complaintTxtf.setToolTipText("Write down the remark about this visit");
        complaintTxtf.setWrapStyleWord(true);
        complaintTxtf.setMaximumSize(new java.awt.Dimension(164, 94));
        complaintTxtf.setMinimumSize(new java.awt.Dimension(164, 94));
        jScrollPane5.setViewportView(complaintTxtf);

        complaintLbl.setForeground(new java.awt.Color(255, 255, 255));
        complaintLbl.setText("Complaint:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(complaintLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(complaintLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel5.setMaximumSize(new java.awt.Dimension(164, 94));
        jPanel5.setMinimumSize(new java.awt.Dimension(164, 94));
        jPanel5.setOpaque(false);

        jScrollPane6.setMaximumSize(new java.awt.Dimension(166, 56));
        jScrollPane6.setMinimumSize(new java.awt.Dimension(166, 56));
        jScrollPane6.setPreferredSize(new java.awt.Dimension(166, 56));

        anamnesisTxtf.setColumns(20);
        anamnesisTxtf.setLineWrap(true);
        anamnesisTxtf.setRows(5);
        anamnesisTxtf.setToolTipText("Write down the remark about this visit");
        anamnesisTxtf.setWrapStyleWord(true);
        anamnesisTxtf.setMaximumSize(new java.awt.Dimension(164, 94));
        anamnesisTxtf.setMinimumSize(new java.awt.Dimension(164, 94));
        jScrollPane6.setViewportView(anamnesisTxtf);

        anamensisLbl.setForeground(new java.awt.Color(255, 255, 255));
        anamensisLbl.setText("Anamnesis:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(anamensisLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 15, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(anamensisLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );

        jPanel6.setMaximumSize(new java.awt.Dimension(164, 94));
        jPanel6.setMinimumSize(new java.awt.Dimension(164, 94));
        jPanel6.setOpaque(false);

        jScrollPane7.setMaximumSize(new java.awt.Dimension(166, 56));
        jScrollPane7.setMinimumSize(new java.awt.Dimension(166, 56));
        jScrollPane7.setPreferredSize(new java.awt.Dimension(166, 56));

        examinationTxtf.setColumns(20);
        examinationTxtf.setLineWrap(true);
        examinationTxtf.setRows(5);
        examinationTxtf.setToolTipText("Write down the remark about this visit");
        examinationTxtf.setWrapStyleWord(true);
        examinationTxtf.setMaximumSize(new java.awt.Dimension(164, 94));
        examinationTxtf.setMinimumSize(new java.awt.Dimension(164, 94));
        jScrollPane7.setViewportView(examinationTxtf);

        examinationLbl.setForeground(new java.awt.Color(255, 255, 255));
        examinationLbl.setText("Examination:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(examinationLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 15, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(examinationLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );

        jPanel10.setMaximumSize(new java.awt.Dimension(164, 94));
        jPanel10.setMinimumSize(new java.awt.Dimension(164, 94));
        jPanel10.setOpaque(false);

        jScrollPane8.setMaximumSize(new java.awt.Dimension(166, 56));
        jScrollPane8.setMinimumSize(new java.awt.Dimension(166, 56));
        jScrollPane8.setPreferredSize(new java.awt.Dimension(166, 56));

        therapyTxtf.setColumns(20);
        therapyTxtf.setLineWrap(true);
        therapyTxtf.setRows(5);
        therapyTxtf.setToolTipText("Write down the remark about this visit");
        therapyTxtf.setWrapStyleWord(true);
        therapyTxtf.setMaximumSize(new java.awt.Dimension(164, 94));
        therapyTxtf.setMinimumSize(new java.awt.Dimension(164, 94));
        jScrollPane8.setViewportView(therapyTxtf);

        therapyLbl.setForeground(new java.awt.Color(255, 255, 255));
        therapyLbl.setText("Therapy:");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(therapyLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 7, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(therapyLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );

        jPanel12.setMaximumSize(new java.awt.Dimension(164, 94));
        jPanel12.setMinimumSize(new java.awt.Dimension(164, 94));
        jPanel12.setOpaque(false);

        jScrollPane9.setMaximumSize(new java.awt.Dimension(166, 56));
        jScrollPane9.setMinimumSize(new java.awt.Dimension(166, 56));
        jScrollPane9.setPreferredSize(new java.awt.Dimension(166, 56));

        recommendationTxtf.setColumns(20);
        recommendationTxtf.setLineWrap(true);
        recommendationTxtf.setRows(5);
        recommendationTxtf.setToolTipText("Write down the remark about this visit");
        recommendationTxtf.setWrapStyleWord(true);
        recommendationTxtf.setMaximumSize(new java.awt.Dimension(164, 94));
        recommendationTxtf.setMinimumSize(new java.awt.Dimension(164, 94));
        jScrollPane9.setViewportView(recommendationTxtf);

        recommendationLbl.setForeground(new java.awt.Color(255, 255, 255));
        recommendationLbl.setText("Recommendation:");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(recommendationLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 7, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(recommendationLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );

        priceLbl.setForeground(new java.awt.Color(255, 255, 255));
        priceLbl.setText("Price:");

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(priceLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(priceTxtf, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(160, 160, 160))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(priceTxtf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(priceLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 55, Short.MAX_VALUE))
        );

        mainScrollPane.setViewportView(mainPanel);

        backgroundLbl.add(mainScrollPane);
        mainScrollPane.setBounds(0, 340, 502, 260);

        seeAllCBox.setText("All");
        backgroundLbl.add(seeAllCBox);
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

        backgroundLbl.add(jScrollPane3);
        jScrollPane3.setBounds(520, 50, 550, 510);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        clearBtn.setBackground(new java.awt.Color(0, 0, 0));
        clearBtn.setForeground(new java.awt.Color(4, 205, 0));
        clearBtn.setText("Clear");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        saveBtn.setBackground(new java.awt.Color(0, 0, 0));
        saveBtn.setForeground(new java.awt.Color(4, 205, 0));
        saveBtn.setText("Save");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clearBtn)
                    .addComponent(saveBtn))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        backgroundLbl.add(jPanel1);
        jPanel1.setBounds(530, 580, 490, 23);

        getContentPane().add(backgroundLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 0, 1080, 620));

        infoLbl.setMaximumSize(new java.awt.Dimension(34, 14));
        infoLbl.setMinimumSize(new java.awt.Dimension(34, 14));
        infoLbl.setPreferredSize(new java.awt.Dimension(34, 14));
        getContentPane().add(infoLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(555, 0, 519, -1));

        background.setBackground(new java.awt.Color(102, 102, 102));
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 620));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        clearObject();
    }//GEN-LAST:event_clearBtnActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        try {
            validation();
            Diagnosis diagnosis = new Diagnosis(complaintTxtf.getText().trim(),examinationTxtf.getText().trim(),anamnesisTxtf.getText().trim(),therapyTxtf.getText().trim(),recommendationTxtf.getText().trim());
            diagnosis=diagnosisIr.create(diagnosis);
            DiagnosisForVisit diagnosisForVisit =new DiagnosisForVisit(diagnosis,mainDoctorVisit,Double.parseDouble(priceTxtf.getText().trim()));
            diagnosisForVisitIr.create(diagnosisForVisit);
            mainDoctorVisit.setSumPrice(mainDoctorVisit.getSumPrice()+diagnosisForVisit.getPrice());
            if(!mainDoctorVisit.getFinished().equals("Yes"))
                mainDoctorVisit.setFinished("Yes");
            doctorVisitIr.edit(mainDoctorVisit);
            clearObject();
            clearDiagnosis();
            JOptionPane.showMessageDialog(this, "The diagnosis was added sucsesfully to this visit.");
        } catch (AppException ae) {
            ae.printStackTrace();
            JOptionPane.showMessageDialog(this, ae.getMessage());
        }catch(StopException se){
            se.printStackTrace();
        }
    }//GEN-LAST:event_saveBtnActionPerformed


    
    public void clearDiagnosis(){
        complaintTxtf.setText("");
        examinationTxtf.setText("");
        anamnesisTxtf.setText("");
        therapyTxtf.setText("");
        recommendationTxtf.setText("");
        mainScrollPane.getVerticalScrollBar().setValue(0);
        complaintTxtf.requestFocus();
    }
    
    
    
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel allergiesLbl;
    private javax.swing.JTextArea allergiesTxtf;
    private javax.swing.JLabel anamensisLbl;
    private javax.swing.JTextArea anamnesisTxtf;
    private javax.swing.JLabel background;
    private javax.swing.JPanel backgroundLbl;
    private javax.swing.JLabel cityLbl;
    private javax.swing.JButton clearBtn;
    private javax.swing.JLabel complaintLbl;
    private javax.swing.JTextArea complaintTxtf;
    private javax.swing.JLabel dateOfBirthLbl;
    private javax.swing.JLabel emailLbl;
    private javax.swing.JLabel examinationLbl;
    private javax.swing.JTextArea examinationTxtf;
    private javax.swing.JLabel genderLbl;
    private javax.swing.JLabel infoLbl;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JScrollPane mainScrollPane;
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
    private javax.swing.JLabel priceLbl;
    private javax.swing.JTextField priceTxtf;
    private javax.swing.JLabel recommendationLbl;
    private javax.swing.JTextArea recommendationTxtf;
    private javax.swing.JLabel remarkLbl;
    private javax.swing.JTextArea remarkTxtf;
    private javax.swing.JButton saveBtn;
    private javax.swing.JCheckBox seeAllCBox;
    private javax.swing.JLabel therapyLbl;
    private javax.swing.JTextArea therapyTxtf;
    private javax.swing.JTable visitTbl;
    // End of variables declaration//GEN-END:variables

    private void validation()throws AppException,StopException{
        if(complaintTxtf.getText().trim().length()>=300){
            complaintTxtf.requestFocus();
            throw new AppException("Complaints cannot contain more than 300 characters");
        }
        
        if(anamnesisTxtf.getText().trim().length()>=300){
            anamnesisTxtf.requestFocus();
            throw new AppException("Anamensis cannot contain more than 300 characters");
        }
        
        if(examinationTxtf.getText().trim().length()>=300){
            examinationTxtf.requestFocus();
            throw new AppException("Examination cannot contain more than 300 characters");
        }
        
        if(therapyTxtf.getText().trim().length()>=300){
            therapyTxtf.requestFocus();
            throw new AppException("Therapy cannot contain more than 300 characters");
        }
        
        if(recommendationTxtf.getText().trim().length()>=300){
            recommendationTxtf.requestFocus();
            throw new AppException("Therapy cannot contain more than 300 characters");
        }
        
        if(visitTbl.getSelectedRow()==-1)
            throw new AppException("Choose the visit for which you want to add the diagnosis.");
        
        if(complaintTxtf.getText().trim().isEmpty()){
            complaintTxtf.requestFocus();
            throw new AppException("You have to write down the complaints of the patient.");
        }
        
        if(examinationTxtf.getText().trim().isEmpty()){
            examinationTxtf.requestFocus();
            throw new AppException("You have to write down the examination of the patient.");
        }
        
        if(priceTxtf.getText().trim().isEmpty()){
            priceTxtf.requestFocus();
            throw new AppException("You have to set a price for the diagnosis");
        }
        
        if(anamnesisTxtf.getText().trim().isEmpty() 
                || therapyTxtf.getText().trim().isEmpty() || recommendationTxtf.getText().trim().isEmpty()){
            String s="Are you sure you want to leave ";
            if(anamnesisTxtf.getText().trim().isEmpty())
                s+=" anamnesis ,";
            if(therapyTxtf.getText().trim().isEmpty())
                s+=" therapy ,";
            if(recommendationTxtf.getText().trim().isEmpty())
                s+=" recommendation ,";
            s=s.substring(0,s.length()-2);
            s+=" empty ?";
            String[] opcionet = {"Po", "Jo"};
            int response = JOptionPane.showOptionDialog(null,s, "Warning",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, opcionet, opcionet[0]);
            if (response == 0) {
            } else {
                throw new StopException("The user wants to fill the data from nullable fields");
            }
        }
        
        if(!allergiesTxtf.getText().trim().equals(mainDoctorVisit.getPatientID().getAllergies()))
        {
            Patient patient=mainDoctorVisit.getPatientID();
            String[] opcionet = {"Yes","No","Cancel"};
            int response = JOptionPane.showOptionDialog(null,
                    "You have changed the allergies of the patient press yes to save them this way or no to return them to the past value.(Cancel to stop the registration)", "Warning",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, opcionet, opcionet[0]);
            if (response == 0) {
                patient.setAllergies(allergiesTxtf.getText().trim());
                patientIr.edit(patient);
            } else if (response==1){
                allergiesTxtf.setText(mainDoctorVisit.getPatientID().getAllergies());
            }
            else{
                throw new StopException("The user has pressed cancel thereby stopping the registration");
            }
        }
        
        if(!remarkTxtf.getText().trim().equals(mainDoctorVisit.getRemark().trim())){
            String[] opcionet = {"Yes","No","Cancel"};
            int response = JOptionPane.showOptionDialog(null,
                    "You have changed the remark of the visit press yes to save it this way or no to return it to the past value.(Cancel to stop the registration)", "Warning",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, opcionet, opcionet[0]);
            if(response==0){
                mainDoctorVisit.setRemark(remarkTxtf.getText().trim());
                doctorVisitIr.edit(mainDoctorVisit);
            }
            else if (response==1){
                remarkTxtf.setText(mainDoctorVisit.getRemark());
            }
            else{
                throw new StopException("The user has pressed cancel thereby stopping the registration");
            }
        }
    }

    private void clearObject() {
        infoLbl.setText("");
        infoLbl.setForeground(Color.BLACK);
        patientNameLbl.setText("");
        nameLbl.setForeground(Color.BLACK);
        patientGenderLbl.setText("");
        genderLbl.setForeground(Color.BLACK);
        patientDateOfBirthLbl.setText("");
        dateOfBirthLbl.setForeground(Color.BLACK);
        patientPlaceOfBirthLbl.setText("");
        placeOfBirthLbl.setForeground(Color.BLACK);
        patientCityLbl.setText("");
        cityLbl.setForeground(Color.BLACK);
        patientEmailLbl.setText("");
        emailLbl.setForeground(Color.BLACK);
        patientPhoneLbl.setText("");
        phoneLbl.setForeground(Color.BLACK);
        allergiesTxtf.setText("");
        allergiesLbl.setForeground(Color.BLACK);
        remarkTxtf.setText("");
        remarkLbl.setForeground(Color.BLACK);
        visitTbl.clearSelection();
    }
    
    /*PatientID Name Surname ParentName Gender DateOfBirth PlaceOFBirth City Phone Email Allergies Allergies Username */

    private void addDetailsToVisitFormListeners() {
        seeAllCBox.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ie){
                visitTableLoad();
            }


            
        });
        
    }

    private void changeTab(){
        
        AbstractAction transferFocus = new AbstractAction ()
            {
                public void actionPerformed ( ActionEvent e )
                {
                    ( ( Component ) e.getSource () ).transferFocus ();
                }
            };
            
            allergiesTxtf.getInputMap ().put ( KeyStroke.getKeyStroke ( "TAB" ), "transferFocus" );
            allergiesTxtf.getActionMap ().put ( "transferFocus", transferFocus );
            
            remarkTxtf.getInputMap ().put ( KeyStroke.getKeyStroke ( "TAB" ), "transferFocus" );
            remarkTxtf.getActionMap ().put ( "transferFocus", transferFocus );
            
            complaintTxtf.getInputMap ().put ( KeyStroke.getKeyStroke ( "TAB" ), "transferFocus" );
            complaintTxtf.getActionMap ().put ( "transferFocus", transferFocus );
            
            anamnesisTxtf.getInputMap ().put ( KeyStroke.getKeyStroke ( "TAB" ), "transferFocus" );
            anamnesisTxtf.getActionMap ().put ( "transferFocus", transferFocus );
            
            examinationTxtf.getInputMap ().put ( KeyStroke.getKeyStroke ( "TAB" ), "transferFocus" );
            examinationTxtf.getActionMap ().put ( "transferFocus", transferFocus );
            
            therapyTxtf.getInputMap ().put ( KeyStroke.getKeyStroke ( "TAB" ), "transferFocus" );
            therapyTxtf.getActionMap ().put ( "transferFocus", transferFocus );
            
            recommendationTxtf.getInputMap ().put ( KeyStroke.getKeyStroke ( "TAB" ), "transferFocus" );
            recommendationTxtf.getActionMap ().put ( "transferFocus", transferFocus );
            
            priceTxtf.getInputMap ().put ( KeyStroke.getKeyStroke ( "TAB" ), "transferFocus" );
            priceTxtf.getActionMap ().put ( "transferFocus", transferFocus );
            
           mainScrollPane.getVerticalScrollBar().setValue(jPanel6.getLocation().y);
            
    }
    
    private void setScrollPosition(){
        KeyboardFocusManager.getCurrentKeyboardFocusManager()
                            .addPropertyChangeListener("focusOwner", 
                     new PropertyChangeListener() {

          @Override
          public void propertyChange(PropertyChangeEvent evt) {
           
                if(complaintTxtf.hasFocus()){
                    mainScrollPane.getVerticalScrollBar().setValue(0);
                }
                if(anamnesisTxtf.hasFocus()){
                    mainScrollPane.getVerticalScrollBar().setValue(250);
                }
                if(examinationTxtf.hasFocus()){
                    mainScrollPane.getVerticalScrollBar().setValue(510);
                }
                if(therapyTxtf.hasFocus()){
                    mainScrollPane.getVerticalScrollBar().setValue(760);
                }
                if(recommendationTxtf.hasFocus()){
                    mainScrollPane.getVerticalScrollBar().setValue(1030);
                }
                if(priceTxtf.hasFocus()){
                    mainScrollPane.getVerticalScrollBar().setValue(1070);
                }
            }
          });
        }

    void setPatientData(Patient patient) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        patientNameLbl.setText(patient.getName() + " (" + patient.getParentName() + ") " + patient.getSurname());
        nameLbl.setForeground(Color.BLUE);
        patientGenderLbl.setText(patient.getGender());
        genderLbl.setForeground(Color.BLUE);
        patientDateOfBirthLbl.setText(dateFormat.format(patient.getDateOfBirth()));
        dateOfBirthLbl.setForeground(Color.BLUE);
        patientPlaceOfBirthLbl.setText(patient.getPlaceOFBirth());
        placeOfBirthLbl.setForeground(Color.BLUE);
        patientCityLbl.setText(patient.getCity());
        cityLbl.setForeground(Color.BLUE);
        patientEmailLbl.setText(patient.getEmail());
        emailLbl.setForeground(Color.BLUE);
        patientPhoneLbl.setText(patient.getPhone());
        phoneLbl.setForeground(Color.BLUE);
        allergiesTxtf.setText(patient.getAllergies());
        allergiesLbl.setForeground(Color.BLUE);
        remarkTxtf.setText(mainDoctorVisit.getRemark());
        remarkLbl.setForeground(Color.BLUE);
    }
    
}
