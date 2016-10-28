package gui.view;

import ExceptionPackage.AppException;
import bl.AnalysisVisitInterface;
import bl.AnalysisVisitRepository;
import bl.DoctorVisitInterface;
import bl.DoctorVisitRepository;
import bl.LogsInterface;
import bl.LogsRepository;
import bl.PatientInterface;
import bl.PatientRepository;
import ejb.AnalysisVisit;
import ejb.DoctorVisit;
import ejb.Logs;
import ejb.Patient;
import ejb.Staff;
import gui.model.PatientTableModel;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SetPatientToVisit extends javax.swing.JFrame {

    PatientInterface patientIr;
    LogsInterface logsIr;
    DoctorVisitInterface doctorVisitIr;
    AnalysisVisitInterface mainAnalysisIr;
    PatientTableModel patientTM;
    EntityManager entityManager;
    Staff currentUser;
    DoctorVisit mainDoctorVisit;
    AnalysisVisit mainAnalysisVisit;
    AddDetailsToVisit addDetailsToVisit;
    
    public SetPatientToVisit(AddDetailsToVisit addDetailsToVisit, DoctorVisit mainDoctorVisit,EntityManager entityManager,Staff currentUser) {
        initComponents();
        this.entityManager=entityManager;
        initInterfaces();
        initTableModel();
        this.currentUser=currentUser;
        this.mainDoctorVisit=mainDoctorVisit;
        this.addDetailsToVisit=addDetailsToVisit;
        patientTableLoad();
        searchTxtfListener();
        patientTblListeners();
    }
    
    public SetPatientToVisit(AddDetailsToVisit addDetailsToVisit, AnalysisVisit mainAnalysisVisit,EntityManager entityManager,Staff currentUser) {
        initComponents();
        this.entityManager=entityManager;
        initInterfaces();
        initTableModel();
        this.currentUser=currentUser;
        this.mainAnalysisVisit=mainAnalysisVisit;
        this.addDetailsToVisit=addDetailsToVisit;
        patientTableLoad();
        searchTxtfListener();
        patientTblListeners();
    }
    
    private void initTableModel(){
        String[] patientTableModelColumns ={"Name", "Surname", "ParentName", "Gender", "DateOfBirth", "City", "Phone", "Allergies"};
        patientTM=new PatientTableModel(patientTableModelColumns);
    }
    
    private void patientTableLoad() {
        List<Patient> list = patientIr.findAll();
        patientTM.add(list);
        patientTbl.setModel(patientTM);
        patientTM.fireTableDataChanged();
    }
    
    public final void initInterfaces(){
        patientIr=new PatientRepository(entityManager);
        logsIr=new LogsRepository(entityManager);
        mainAnalysisIr=new AnalysisVisitRepository(entityManager);
        doctorVisitIr=new DoctorVisitRepository(entityManager);
    }

    public final void searchTxtfListener(){
    searchTxtf.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                kerkoparticipant();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                kerkoparticipant();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                kerkoparticipant();
            }

            public void kerkoparticipant() {
                staffTableFindByAll(searchTxtf.getText());
            }
        });
    }
    
    public void staffTableFindByAll(String text) {
        List<Patient> list = patientIr.findByAll(text);
        patientTM.add(list);
        patientTbl.setModel(patientTM);
        patientTM.fireTableDataChanged();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        nameTxtf0 = new javax.swing.JLabel();
        nameTxtf1 = new javax.swing.JLabel();
        nameTxtf2 = new javax.swing.JLabel();
        nameTxtf3 = new javax.swing.JLabel();
        nameTxtf4 = new javax.swing.JLabel();
        nameTxtf5 = new javax.swing.JLabel();
        nameTxtf6 = new javax.swing.JLabel();
        nameTxtf7 = new javax.swing.JLabel();
        nameTxtf8 = new javax.swing.JLabel();
        nameTxtf = new javax.swing.JTextField();
        surnameTxtf = new javax.swing.JTextField();
        parentNameTxtf = new javax.swing.JTextField();
        phoneTxtf = new javax.swing.JTextField();
        emailTxtf = new javax.swing.JTextField();
        cityTxtf = new javax.swing.JTextField();
        dateOfBirthCalendar = new com.toedter.calendar.JDateChooser();
        genderCombo = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        allergiesTxtf = new javax.swing.JTextArea();
        clearBtn = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        nameTxtf9 = new javax.swing.JLabel();
        birthplaceTxtf = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        patientTbl = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        searchTxtf = new javax.swing.JTextField();
        infoLbl = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nameTxtf0.setForeground(new java.awt.Color(255, 255, 255));
        nameTxtf0.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        nameTxtf0.setText("Name :");
        jPanel2.add(nameTxtf0, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 120, 20));

        nameTxtf1.setForeground(new java.awt.Color(255, 255, 255));
        nameTxtf1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        nameTxtf1.setText("Surname :");
        jPanel2.add(nameTxtf1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 120, 20));

        nameTxtf2.setForeground(new java.awt.Color(255, 255, 255));
        nameTxtf2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        nameTxtf2.setText("Parent Name :");
        jPanel2.add(nameTxtf2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 120, 20));

        nameTxtf3.setForeground(new java.awt.Color(255, 255, 255));
        nameTxtf3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        nameTxtf3.setText("Date Of Birth :");
        jPanel2.add(nameTxtf3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 120, 20));

        nameTxtf4.setForeground(new java.awt.Color(255, 255, 255));
        nameTxtf4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        nameTxtf4.setText("Gender :");
        jPanel2.add(nameTxtf4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 120, 20));

        nameTxtf5.setForeground(new java.awt.Color(255, 255, 255));
        nameTxtf5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        nameTxtf5.setText("Telephone :");
        jPanel2.add(nameTxtf5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 120, 20));

        nameTxtf6.setForeground(new java.awt.Color(255, 255, 255));
        nameTxtf6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        nameTxtf6.setText("Email :");
        jPanel2.add(nameTxtf6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 120, 20));

        nameTxtf7.setForeground(new java.awt.Color(255, 255, 255));
        nameTxtf7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        nameTxtf7.setText("Birthplace :");
        jPanel2.add(nameTxtf7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 120, 20));

        nameTxtf8.setForeground(new java.awt.Color(255, 255, 255));
        nameTxtf8.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        nameTxtf8.setText("Allergies :");
        jPanel2.add(nameTxtf8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 120, 20));
        jPanel2.add(nameTxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 160, 25));
        jPanel2.add(surnameTxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 160, 25));
        jPanel2.add(parentNameTxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 160, 25));
        jPanel2.add(phoneTxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, 160, 25));
        jPanel2.add(emailTxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, 160, 25));
        jPanel2.add(cityTxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 340, 160, 25));

        dateOfBirthCalendar.setDateFormatString("dd-MM-yyyy");
        jPanel2.add(dateOfBirthCalendar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 160, 25));

        genderCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "null", "M", "F" }));
        jPanel2.add(genderCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, 80, 25));

        allergiesTxtf.setColumns(20);
        allergiesTxtf.setLineWrap(true);
        allergiesTxtf.setRows(5);
        allergiesTxtf.setWrapStyleWord(true);
        jScrollPane1.setViewportView(allergiesTxtf);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 380, 260, 150));

        clearBtn.setBackground(new java.awt.Color(0, 153, 102));
        clearBtn.setForeground(new java.awt.Color(204, 255, 204));
        clearBtn.setText("Clear");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });
        jPanel2.add(clearBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 540, 200, 40));

        saveButton.setBackground(new java.awt.Color(0, 153, 102));
        saveButton.setForeground(new java.awt.Color(204, 255, 204));
        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        jPanel2.add(saveButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 540, 200, 40));

        nameTxtf9.setForeground(new java.awt.Color(255, 255, 255));
        nameTxtf9.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        nameTxtf9.setText("City :");
        jPanel2.add(nameTxtf9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 120, 20));
        jPanel2.add(birthplaceTxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 300, 160, 25));

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 490, 620);

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        patientTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        patientTbl.setShowHorizontalLines(false);
        patientTbl.setShowVerticalLines(false);
        jScrollPane2.setViewportView(patientTbl);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 84, 540, 500));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Search :");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 22, 170, 35));
        jPanel3.add(searchTxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 300, 35));
        jPanel3.add(infoLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 580, 10));

        jPanel1.add(jPanel3);
        jPanel3.setBounds(500, 10, 570, 600);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1080, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1080, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        emptyLabels();
    }//GEN-LAST:event_clearBtnActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        try {
            validation();
                addPatientToVisitMethod();
            JOptionPane.showMessageDialog(this,"The patient has been added to this visit.");
            this.dispose();
        } catch (AppException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_saveButtonActionPerformed
  
    
    
    public Patient addPatientMethod()throws AppException{
        Patient patient = new Patient();
        patient.setName(nameTxtf.getText().trim());
        patient.setSurname(surnameTxtf.getText().trim());
        patient.setParentName(parentNameTxtf.getText().trim());
        patient.setDateOfBirth(dateOfBirthCalendar.getDate());
        patient.setGender(genderCombo.getSelectedItem().toString());
        patient.setPhone(phoneTxtf.getText().trim());
        patient.setEmail(emailTxtf.getText().trim());
        patient.setPlaceOFBirth(birthplaceTxtf.getText().trim());
        patient.setCity(cityTxtf.getText().trim());
        patient.setAllergies(allergiesTxtf.getText().trim());
        patient.setUsername(currentUser);
        patient=patientIr.create(patient);
        addCreateLog(patient);
        JOptionPane.showMessageDialog(this, "Pacienti u shtua me sukses");
        return patient;
    }
    
    public void addPatientToVisitMethod()throws AppException{
        Patient patient;
        if(patientTbl.getSelectedRow()==-1){
            patient=addPatientMethod();
        }else {
            patient=patientTM.getPatient(patientTbl.getSelectedRow());
        }
        
        
            editVisit(patient);
    }
    
    public void addCreateLog(Patient patient) throws AppException {

        Logs logs = new Logs();
        Date date = logsIr.findDate();
        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat sdf2 = new SimpleDateFormat("HH:mm");
        String today = sdf.format(date);
        String koha = sdf2.format(date);

        String message = currentUser.getName() + " " + currentUser.getSurname() + " me username-in: " + currentUser.getUsername() + " Ka shtuar pacientin " + patient.getName() + " " + patient.getSurname() + " në datën : " + today + " në ora " + koha;

        logs.setUsername(currentUser);
        logs.setTimeStamp(date);
        logs.setMessage(message);
        logs.setType("Create");

        logsIr.create(logs);
    }
    
    public void validation()throws AppException{
        if(currentUser.getRole().equals("Doctor")){
        if (mainDoctorVisit.getPatientID() != null) {
            this.dispose();
            throw new AppException("This visit got a patient assigned.");
        }
        }else if (currentUser.getRole().equals("LaboratorTechnician")){
            if(mainAnalysisVisit.getPatientID()!=null){
                this.dispose();
                throw new AppException("This analysis got a patient assigned.");
            }
        }
        
        if (nameTxtf.getText().trim().isEmpty()) {
            nameTxtf.requestFocus();
            throw new AppException("The name cannot be empty.");
        }
        if (surnameTxtf.getText().trim().isEmpty()) {
            surnameTxtf.requestFocus();
            throw new AppException("The surname cannot be empty.");
        }
        if (parentNameTxtf.getText().trim().isEmpty()) {
            parentNameTxtf.requestFocus();
            throw new AppException("The name cannot be empty.");
        }
        if (dateOfBirthCalendar.getDate() == null) {
            dateOfBirthCalendar.requestFocus();
            throw new AppException("The date is incorrect.");
        }
        if (genderCombo.getSelectedIndex() == 0) {
            genderCombo.requestFocus();
            throw new AppException("The gender cannot be null.");
        }

        if (nameTxtf.getText().trim().length() > 50) {
            nameTxtf.requestFocus();
            throw new AppException("The name cannot contain more than 50 characters.");
        }
        if (surnameTxtf.getText().trim().length() > 50) {
            surnameTxtf.requestFocus();
            throw new AppException("The name cannot contain more than 50 characters.");
        }
        if (parentNameTxtf.getText().trim().length() > 50) {
            parentNameTxtf.requestFocus();
            throw new AppException("The name cannot contain more than 50 characters.");
        }
        
    }
    
    public void emptyLabels() {
        nameTxtf.setText("");
        surnameTxtf.setText("");
        parentNameTxtf.setText("");
        phoneTxtf.setText("");
        emailTxtf.setText("");
        birthplaceTxtf.setText("");
        cityTxtf.setText("");
        dateOfBirthCalendar.setDate(null);
        genderCombo.setSelectedItem("null");
        allergiesTxtf.setText("");
        patientTbl.clearSelection();
        nameTxtf.requestFocus();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea allergiesTxtf;
    private javax.swing.JTextField birthplaceTxtf;
    private javax.swing.JTextField cityTxtf;
    private javax.swing.JButton clearBtn;
    private com.toedter.calendar.JDateChooser dateOfBirthCalendar;
    private javax.swing.JTextField emailTxtf;
    private javax.swing.JComboBox<String> genderCombo;
    private javax.swing.JLabel infoLbl;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField nameTxtf;
    private javax.swing.JLabel nameTxtf0;
    private javax.swing.JLabel nameTxtf1;
    private javax.swing.JLabel nameTxtf2;
    private javax.swing.JLabel nameTxtf3;
    private javax.swing.JLabel nameTxtf4;
    private javax.swing.JLabel nameTxtf5;
    private javax.swing.JLabel nameTxtf6;
    private javax.swing.JLabel nameTxtf7;
    private javax.swing.JLabel nameTxtf8;
    private javax.swing.JLabel nameTxtf9;
    private javax.swing.JTextField parentNameTxtf;
    private javax.swing.JTable patientTbl;
    private javax.swing.JTextField phoneTxtf;
    private javax.swing.JButton saveButton;
    private javax.swing.JTextField searchTxtf;
    private javax.swing.JTextField surnameTxtf;
    // End of variables declaration//GEN-END:variables

    private void editVisit(Patient patient)throws AppException {
        if(currentUser.getRole().equals("Doctor")){
            mainDoctorVisit.setPatientID(patient);
            doctorVisitIr.edit(mainDoctorVisit);
        }else if (currentUser.getRole().equals("LaboratorTechnician")){
            mainAnalysisVisit.setPatientID(patient);
            mainAnalysisIr.edit(mainAnalysisVisit);
        }
    }
    
    private void patientTblListeners(){
        clickMoveKey();
        patientTableMoveKey();
    }
    
    private void clickMoveKey(){
        patientTbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me){
                if(me.getButton()==3){
                    patientTbl.clearSelection();
                    nameTxtf.setText("");
                    surnameTxtf.setText("");
                    parentNameTxtf.setText("");
                    genderCombo.setSelectedItem("");
                    dateOfBirthCalendar.setDate(null);
                    phoneTxtf.setText("");
                    emailTxtf.setText("");
                    birthplaceTxtf.setText("");
                    cityTxtf.setText("");
                    allergiesTxtf.setText("");
                    infoLbl.setForeground(Color.BLACK);
                    infoLbl.setText("");
                }
            }
            
        });
    }
    
    private void patientTableMoveKey() {
        ListSelectionModel rowSM = patientTbl.getSelectionModel();
        rowSM.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    return;
                }
                ListSelectionModel rowSM = (ListSelectionModel) e.getSource();
                int selectedIndex = rowSM.getMinSelectionIndex();
                if (selectedIndex > -1) {
                    Patient patient = patientTM.getPatient(selectedIndex);
                    nameTxtf.setText(patient.getName());
                    surnameTxtf.setText(patient.getSurname());
                    parentNameTxtf.setText(patient.getParentName());
                    genderCombo.setSelectedItem(patient.getGender());
                    dateOfBirthCalendar.setDate(patient.getDateOfBirth());
                    phoneTxtf.setText(patient.getPhone());
                    emailTxtf.setText(patient.getEmail());
                    birthplaceTxtf.setText(patient.getPlaceOFBirth());
                    cityTxtf.setText(patient.getCity());
                    allergiesTxtf.setText(patient.getAllergies());
                    infoLbl.setForeground(Color.GRAY);
                    infoLbl.setText("You can remove the selection with a right click on the table.");
                    
                }
            }
        });
    }
}
