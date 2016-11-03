package gui.view;

import ExceptionPackage.AppException;
import ExceptionPackage.StopException;
import bl.AnalysisVisitInterface;
import bl.AnalysisVisitRepository;
import bl.DoctorVisitInterface;
import bl.DoctorVisitRepository;
import bl.LogsRepository;
import bl.PatientInterface;
import bl.PatientRepository;
import bl.StaffInterface;
import bl.StaffRepository;
import ejb.AnalysisVisit;
import ejb.DoctorVisit;
import ejb.Staff;
import gui.model.PatientTableModel;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

public class CreateVisit extends javax.swing.JInternalFrame {
    StaffInterface staffIr;
    Staff currentUser;
    EntityManager em;
    DoctorVisitInterface doctorVisitIr;
    PatientTableModel patientTM;
    PatientInterface patientIr;
    AnalysisVisitInterface analysisVisitIr;
    EntityManager entityManager;
    List<Staff> staffList;
    MainFrame mainFrame;
    
    public CreateVisit(EntityManager entityManager,Staff currentUser,MainFrame mainFrame) {
        initComponents();
        this.entityManager=entityManager;
        this.currentUser=currentUser;
        this.mainFrame=mainFrame;
        initInterface(this.entityManager);
        String[] patientTMColumns={"Name","Surname","ParentName","PlaceOfBirth","DateOfBirth"};
        patientTM=new PatientTableModel(patientTMColumns);
        this.setLocation(220, 10);
        this.setPreferredSize(new Dimension(1100, 654));
        createVisitFormListeners();
        patientTableLoad();
        fillTimeCombos();
    }
    
    private void initInterface(EntityManager entityManager){
        staffIr=new StaffRepository(entityManager);
        doctorVisitIr=new DoctorVisitRepository(entityManager);
        patientIr=new PatientRepository(entityManager);
        analysisVisitIr=new AnalysisVisitRepository(entityManager);
    }
    
    private void clearObject(){
        remarkTxtf.setText("");
        visitCombo.setSelectedIndex(0);
        minuteCombo.setSelectedIndex(0);
        hourCombo.setSelectedIndex(0);
        dateCalendar.setDate(null);
        patientTbl.clearSelection();
    }

    private void patientTableLoad() {
        patientTM.add(patientIr.findAll());
        if(!patientTM.isEmpty()){
            patientTbl.setModel(patientTM);
            patientTM.fireTableDataChanged();
        }
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
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backgroundPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        visitCombo = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        remarkTxtf = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        staffLbl1 = new javax.swing.JLabel();
        dateCalendar = new com.toedter.calendar.JDateChooser();
        autoDateCBox = new javax.swing.JCheckBox();
        hourLbl = new javax.swing.JLabel();
        hourCombo = new javax.swing.JComboBox<>();
        minuteLbl = new javax.swing.JLabel();
        minuteCombo = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        remarkLbl = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        staffCombo = new javax.swing.JComboBox<>();
        staffLbl = new javax.swing.JLabel();
        typeOfVisitLbl = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        patientTbl = new javax.swing.JTable();
        saveBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();

        setClosable(true);
        setTitle("Create Doctor Visit");
        setMaximumSize(new java.awt.Dimension(1100, 654));
        setMinimumSize(new java.awt.Dimension(1100, 654));
        setPreferredSize(new java.awt.Dimension(1100, 654));

        backgroundPanel.setBackground(new java.awt.Color(102, 102, 102));

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));

        jPanel1.setOpaque(false);

        visitCombo.setToolTipText("Choose the type of visit");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(visitCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(visitCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        remarkTxtf.setColumns(20);
        remarkTxtf.setLineWrap(true);
        remarkTxtf.setRows(5);
        remarkTxtf.setToolTipText("Write down the remark about this visit");
        remarkTxtf.setWrapStyleWord(true);
        jScrollPane2.setViewportView(remarkTxtf);

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
                .addComponent(hourCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(minuteLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(minuteCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addComponent(autoDateCBox)
                .addContainerGap())
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

        jPanel6.setOpaque(false);

        remarkLbl.setForeground(new java.awt.Color(255, 255, 255));
        remarkLbl.setText("Remark :");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(remarkLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(remarkLbl)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        jPanel4.setOpaque(false);

        staffCombo.setToolTipText("Choose the staff member who will perform the visit");

        staffLbl.setForeground(new java.awt.Color(255, 255, 255));
        staffLbl.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        staffLbl.setText("Doctor to hold visit :");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(staffLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(staffCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(staffCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(staffLbl))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        typeOfVisitLbl.setForeground(new java.awt.Color(255, 255, 255));
        typeOfVisitLbl.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        typeOfVisitLbl.setText("Type Of Visit :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(typeOfVisitLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane2)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(typeOfVisitLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        patientTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        patientTbl.setShowHorizontalLines(false);
        patientTbl.setShowVerticalLines(false);
        jScrollPane3.setViewportView(patientTbl);

        saveBtn.setBackground(new java.awt.Color(0, 153, 102));
        saveBtn.setForeground(new java.awt.Color(204, 255, 204));
        saveBtn.setText("Save");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        clearBtn.setBackground(new java.awt.Color(0, 153, 102));
        clearBtn.setForeground(new java.awt.Color(204, 255, 204));
        clearBtn.setText("Clear");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout backgroundPanelLayout = new javax.swing.GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(backgroundPanelLayout);
        backgroundPanelLayout.setHorizontalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(backgroundPanelLayout.createSequentialGroup()
                        .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(139, 139, 139)
                        .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        backgroundPanelLayout.setVerticalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(clearBtn)
                            .addComponent(saveBtn))
                        .addContainerGap())
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 613, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        mainFrame.keepRunning=false;
        try {
            validation();
            String typeOfVisit=visitCombo.getSelectedItem().toString();
            if(typeOfVisit.equals("Biochemical Analysis") || typeOfVisit.equals("Microbiology Analysis")){
                // krijo analysisVisit
                createAnalysisVisit();
            }
            else{
                //krijo doctorVisit
                createDoctorVisit();
            }
            clearObject();
            JOptionPane.showMessageDialog(this, "The visit was stored sucsesfully.");
        } catch (AppException ae) {
            ae.printStackTrace();
            JOptionPane.showMessageDialog(this, ae.getMessage());
        }catch(StopException se){
            se.printStackTrace();
        }catch(ParseException pe){
            
        }
        synchronized(mainFrame.messagesThread){
            mainFrame.keepRunning = true;
            mainFrame.messagesThread.notifyAll();
        }
    }//GEN-LAST:event_saveBtnActionPerformed
    
    private void createAnalysisVisit()throws AppException,StopException, ParseException{
        int rowNumber;
        AnalysisVisit analysisVisit= new AnalysisVisit(remarkTxtf.getText().trim(),staffList.get(staffCombo.getSelectedIndex()),currentUser);
        analysisVisit.setTypeOfVisit(visitCombo.getSelectedItem().toString());
        if(autoDateCBox.isSelected()){
            analysisVisit.setTimeStamp(new LogsRepository(entityManager).findDate());
        }else {
            String temp;
            String completeDate="";
            SimpleDateFormat dateFormat=new SimpleDateFormat("HH-mm-dd-MM-yyyy");
            temp=Integer.toString(hourCombo.getSelectedIndex()-1);
            if(temp.length()==1)
                temp="0"+temp;
            completeDate+=temp;
            temp=Integer.toString(minuteCombo.getSelectedIndex()-1);
            if(temp.length()==1)
                temp="0"+temp;
            completeDate+="-"+temp;
            temp=new SimpleDateFormat("dd-MM-yyyy").format(dateCalendar.getDate());
            completeDate+="-"+temp;
            JOptionPane.showMessageDialog(null,completeDate);
            analysisVisit.setTimeStamp(dateFormat.parse(completeDate));
        }
        if ((rowNumber = patientTbl.getSelectedRow()) == -1) {
            String[] opcionet = {"Po", "Jo"};
            int response = JOptionPane.showOptionDialog(null,
                    "You didn't assign a patient for this visit, press yes to continue , no to stop?", "Warning",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, opcionet, opcionet[0]);
            if (response == 0) {
            } else {
                throw new StopException("The user wants to add a patient to the visit");
            }

        } else {
            analysisVisit.setPatientID(patientTM.getPatient(rowNumber));
        }
        analysisVisitIr.create(analysisVisit);
    }
    
    private void createDoctorVisit() throws AppException, StopException {
        int rowNumber;
        DoctorVisit doctorVisit = new DoctorVisit(
                remarkTxtf.getText().trim(), 
                staffList.get(staffCombo.getSelectedIndex()), 
                currentUser);
        doctorVisit.setTypeOfVisit(visitCombo.getSelectedItem().toString());
        if (autoDateCBox.isSelected()) {
            doctorVisit.setTimeStamp(new LogsRepository(entityManager).findDate());
        } else {
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateCalendar.getDate());
            cal.add(Calendar.HOUR_OF_DAY, hourCombo.getSelectedIndex() - 1);
            cal.add(Calendar.MINUTE, minuteCombo.getSelectedIndex() - 1);
            doctorVisit.setTimeStamp(cal.getTime());
        }
        if ((rowNumber = patientTbl.getSelectedRow()) == -1) {
            String[] opcionet = {"Po", "Jo"};
            int response = JOptionPane.showOptionDialog(null,
                    "You didn't assign a patient for this visit, press yes to continue , no to stop?", "Warning",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, opcionet, opcionet[0]);
            if (response == 0) {
            } else {
                throw new StopException("The user wants to add a patient to the visit");
            }

        } else {
            doctorVisit.setPatientID(patientTM.getPatient(rowNumber));
        }
        doctorVisitIr.create(doctorVisit);
    }
    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        clearObject();
    }//GEN-LAST:event_clearBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox autoDateCBox;
    private javax.swing.JPanel backgroundPanel;
    private javax.swing.JButton clearBtn;
    private com.toedter.calendar.JDateChooser dateCalendar;
    private javax.swing.JComboBox<String> hourCombo;
    private javax.swing.JLabel hourLbl;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JComboBox<String> minuteCombo;
    private javax.swing.JLabel minuteLbl;
    private javax.swing.JTable patientTbl;
    private javax.swing.JLabel remarkLbl;
    private javax.swing.JTextArea remarkTxtf;
    private javax.swing.JButton saveBtn;
    private javax.swing.JComboBox<String> staffCombo;
    private javax.swing.JLabel staffLbl;
    private javax.swing.JLabel staffLbl1;
    private javax.swing.JLabel typeOfVisitLbl;
    private javax.swing.JComboBox<String> visitCombo;
    // End of variables declaration//GEN-END:variables
    private void validation() throws AppException,StopException {
        if (visitCombo.getSelectedItem().toString().equals("Choose")) {
            throw new AppException("Select the type of visit for the patient.");
        }
        if (remarkTxtf.getText().trim().isEmpty()) {
            String[] opcionet={"Po","Jo"};
                int response = JOptionPane.showOptionDialog(null,
                "The remark does not contain any text do you want to save this visit or edit the remark?","Warning",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, opcionet, opcionet[0]);
                if(response==0){
                }
                else{
                    throw new StopException("The user wants to edit the remark text");
                }
        }
        if (remarkTxtf.getText().trim().length() > 500) {
            throw new AppException("The remark cannot contain more than 500 letters.");
        }
    }

    private void createVisitFormListeners() {
        checkBoxListener();//Doctor, LaboratorTechnician, Recepsion
        if(currentUser.getRole().equals("Recepsion")){
            visitCombo.addItem("Choose");
            visitCombo.addItem("Biochemical Analysis");
            visitCombo.addItem("Microbiology Analysis");
            visitCombo.addItem("Control");
            visitCombo.addItem("Treatment");
            visitCombo.addItem("Ultrasound");
            visitComboListener();
        }
        else if (currentUser.getRole().equals("Doctor")){
            staffList=new ArrayList<Staff>(1);
            staffList.add(currentUser);
            visitCombo.addItem("Choose");
            visitCombo.addItem("Control");
            visitCombo.addItem("Treatment");
            visitCombo.addItem("Ultrasound");
            staffCombo.addItem(currentUser.toString());
        }else if (currentUser.getRole().equals("LaboratorTechnician")){
            staffList=new ArrayList<Staff>(1);
            staffList.add(currentUser);
            visitCombo.addItem("Choose");
            visitCombo.addItem("Biochemical Analysis");
            visitCombo.addItem("Microbiology Analysis");
            staffCombo.addItem(currentUser.toString());
        }
        
        
        

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
    
    private void visitComboListener() {
        visitCombo.addItemListener(new ItemListener() {
            String currentState = "";
            String temp;

            @Override
            public void itemStateChanged(ItemEvent ie) {
                if (currentState != (temp = visitCombo.getSelectedItem().toString())) {
                    String role;
                    if (temp.equals("Biochemical Analysis") || temp.equals("Microbiology Analysis")) {
                        role = "LaboratorTechnician";
                    } else {
                        role = "Doctor";
                    }
                    staffCombo.removeAllItems();
                    if (!temp.equals("Choose")) {
                        staffList = staffIr.findByRole(role);
                        for (Staff tempStaff : staffList) {
                            staffCombo.addItem(tempStaff.toString());
                        }
                    }
                    currentState = visitCombo.getSelectedItem().toString();
                }
            }
        });
    }
}
