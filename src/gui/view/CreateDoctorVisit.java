package gui.view;

import ExceptionPackage.AppException;
import ExceptionPackage.StopException;
import bl.DoctorVisitInterface;
import bl.DoctorVisitRepository;
import bl.LogsRepository;
import bl.PatientInterface;
import bl.PatientRepository;
import bl.StaffInterface;
import bl.StaffRepository;
import ejb.DoctorVisit;
import ejb.Staff;
import gui.model.PatientTableModel;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

public class CreateDoctorVisit extends javax.swing.JInternalFrame {
    StaffInterface staffIr;
    Staff currentUser;
    EntityManager em;
    DoctorVisitInterface doctorVisitIr;
    PatientTableModel patientTM;
    PatientInterface patientIr;
    EntityManager entityManager;
    List<Staff> staffList;
    
    public CreateDoctorVisit(EntityManager entityManager,Staff currentUser) {
        initComponents();
        this.entityManager=entityManager;
        this.currentUser=currentUser;
        initInterface(this.entityManager);
        String[] patientTMColumns={"Name","Surname","ParentName","PlaceOfBirth","DateOfBirth"};
        patientTM=new PatientTableModel(patientTMColumns);
        this.setLocation(220, 10);
        this.setPreferredSize(new Dimension(1100, 654));
        createDoctorListeners();
        patientTableLoad();
    }
    
    private void initInterface(EntityManager entityManager){
        staffIr=new StaffRepository(entityManager);
        doctorVisitIr=new DoctorVisitRepository(entityManager);
        patientIr=new PatientRepository(entityManager);
    }
    
    private void clearObject(){
        remarkTxtf.setText("");
        visitCombo.setSelectedIndex(0);
        patientTbl.clearSelection();
    }

    private void patientTableLoad() {
        patientTM.add(patientIr.findAll());
        if(!patientTM.isEmpty()){
            patientTbl.setModel(patientTM);
            patientTM.fireTableDataChanged();
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backgroundPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        visitCombo = new javax.swing.JComboBox<>();
        typeOfVisitLbl = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        remarkLbl = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        remarkTxtf = new javax.swing.JTextArea();
        saveBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        staffCombo = new javax.swing.JComboBox<>();
        staffLbl = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        patientTbl = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setTitle("Create Doctor Visit");
        setMaximumSize(new java.awt.Dimension(1100, 654));
        setMinimumSize(new java.awt.Dimension(1100, 654));
        setPreferredSize(new java.awt.Dimension(1100, 654));

        visitCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "null", "Biochemical Analysis", "Microbiology Analysis", "Control", "Treatment", "Ultrasound" }));
        visitCombo.setToolTipText("");

        typeOfVisitLbl.setText("Type Of Visit :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(typeOfVisitLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(visitCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(visitCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(typeOfVisitLbl))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        remarkLbl.setText("Remark :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(remarkLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(remarkLbl))
        );

        remarkTxtf.setColumns(20);
        remarkTxtf.setLineWrap(true);
        remarkTxtf.setRows(5);
        remarkTxtf.setWrapStyleWord(true);
        jScrollPane2.setViewportView(remarkTxtf);

        saveBtn.setText("Save");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        clearBtn.setText("Clear");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        staffCombo.setToolTipText("");

        staffLbl.setText("Doctor to hold visit :");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(staffLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(staffCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(staffCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(staffLbl))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveBtn)
                    .addComponent(clearBtn)))
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

        jButton1.setText("Seen All");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        backgroundPanelLayout.setVerticalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(backgroundPanelLayout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        try {
            int rowNumber;
            validation();
            String typeOfVisit=visitCombo.getSelectedItem().toString();
            if(typeOfVisit.equals("Biochemical Analysis") || typeOfVisit.equals("Microbiology Analysis")){
                // krijo analysisVisit
            }
            else{
                //krijo doctorVisit
                DoctorVisit doctorVisit = new DoctorVisit(remarkTxtf.getText().trim(), staffList.get(staffCombo.getSelectedIndex()), currentUser);
                doctorVisit.setTimeStamp(new LogsRepository(entityManager).findDate());
                if ((rowNumber = patientTbl.getSelectedRow())==-1){
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
            clearObject();
            JOptionPane.showMessageDialog(this, "The visit was stored sucsesfully.");
        } catch (AppException ae) {
            ae.printStackTrace();
            JOptionPane.showMessageDialog(this, ae.getMessage());
        }catch(StopException se){
            se.printStackTrace();
        }
    }//GEN-LAST:event_saveBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        clearObject();
    }//GEN-LAST:event_clearBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backgroundPanel;
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable patientTbl;
    private javax.swing.JLabel remarkLbl;
    private javax.swing.JTextArea remarkTxtf;
    private javax.swing.JButton saveBtn;
    private javax.swing.JComboBox<String> staffCombo;
    private javax.swing.JLabel staffLbl;
    private javax.swing.JLabel typeOfVisitLbl;
    private javax.swing.JComboBox<String> visitCombo;
    // End of variables declaration//GEN-END:variables
    private void validation() throws AppException,StopException {
        if (visitCombo.getSelectedItem().toString().equals("null")) {
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

    private void createDoctorListeners() {
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
                    staffCombo.removeAll();
                    staffList = staffIr.findByRole(role);
                    for (Staff tempStaff : staffList) {
                        staffCombo.addItem(tempStaff.toString());
                    }
                    currentState = visitCombo.getSelectedItem().toString();
                }
            }
        });

    }
}
