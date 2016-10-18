package gui.view;

import ExceptionPackage.AppException;
import ejb.Patient;
import ejb.DiagnosisForVisit;
import java.awt.Dimension;
import java.util.List;
import bl.PatientInterface;
import bl.PatientRepository;
import bl.AnalysisForVisitInterface;
import bl.AnalysisForVisitRepository;
import bl.DiagnosisForVisitInterface;
import bl.DiagnosisForVisitRepository;
import ejb.AnalysisForVisit;
import gui.model.PatientTableModel;
import gui.model.AnalysisForVisitTableModel;
import gui.model.DiagnosisForVisitTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.MessageFormat;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Search extends javax.swing.JInternalFrame {
    
    EntityManager entityManager;
    PatientInterface patientIr;
    AnalysisForVisitInterface analysisForVisitIr;
    DiagnosisForVisitInterface diagnosisForVisitIr; 
    PatientTableModel patientTM;
    AnalysisForVisitTableModel analysisForVisitTM;
    DiagnosisForVisitTableModel diagnosisForVisitTM;
    public Search(EntityManager entityManager) {
        initComponents();
        this.setLocation(220, 10);
        this.entityManager=entityManager;
        patientIr=new PatientRepository(entityManager);
        analysisForVisitIr=new AnalysisForVisitRepository(entityManager);
        diagnosisForVisitIr=new DiagnosisForVisitRepository(entityManager);
        String[] columnNamesPatientTableModel = {"Name", "Surname", "ParentName", "Gender", "DateOfBirth", "City", "Phone", "Allergies"};
        patientTM = new PatientTableModel(columnNamesPatientTableModel);
        String[] columnNamesAnalysisTableModel = {"Patient", "Analysis", "Date","Price"};
        analysisForVisitTM = new AnalysisForVisitTableModel(columnNamesAnalysisTableModel);
        String[] columnNamesDiagnosisTableModel = {"Patient", "Complaint", "Examiantion","Therapy", "Date","Price"};
        diagnosisForVisitTM = new DiagnosisForVisitTableModel(columnNamesDiagnosisTableModel);
        
        clickMoveKey();
        patientTableLoad();
        
        
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
    
    
    
    private void patientTableLoad() {
        List<Patient> list = patientIr.findAll();
        patientTM.add(list);
        patientTbl.setModel(patientTM);
        patientTM.fireTableDataChanged();
    }

    private void staffTableFindByAll(String text) {
        List<Patient> list = patientIr.findByAll(text);
        patientTM.add(list);
        patientTbl.setModel(patientTM);
        patientTM.fireTableDataChanged();
    }
    
    private void patientAnalysisTableLoad(int PatientID) {
        List<AnalysisForVisit> list = analysisForVisitIr.findByPatient(PatientID);
        analysisForVisitTM.add(list);
        patientTbl.setModel(analysisForVisitTM);
        analysisForVisitTM.fireTableDataChanged();
    }
    
    private void patientDoctorVisitTableLoad(int PatientID) {
        List<DiagnosisForVisit> list = diagnosisForVisitIr.findByPatient(PatientID);
        diagnosisForVisitTM.add(list);
        patientTbl.setModel(diagnosisForVisitTM);
        diagnosisForVisitTM.fireTableDataChanged();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        searchTxtf = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        patientTbl = new javax.swing.JTable();
        printBtn = new javax.swing.JButton();
        background = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Search");
        setMaximumSize(new java.awt.Dimension(1100, 654));
        setMinimumSize(new java.awt.Dimension(1100, 654));
        setPreferredSize(new java.awt.Dimension(1100, 654));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(null);
        jPanel1.add(searchTxtf);
        searchTxtf.setBounds(120, 20, 400, 25);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Search :");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(20, 20, 90, 25);

        patientTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        patientTbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                patientTblMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(patientTbl);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 82, 1060, 530);

        printBtn.setText("Prito në PDF");
        printBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printBtnActionPerformed(evt);
            }
        });
        jPanel1.add(printBtn);
        printBtn.setBounds(720, 10, 160, 40);

        background.setOpaque(true);
        jPanel1.add(background);
        background.setBounds(0, 0, 1080, 620);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 620));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void patientTblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_patientTblMouseClicked
        forVisitTableModelSwitch();
    }//GEN-LAST:event_patientTblMouseClicked

    private void printBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printBtnActionPerformed
        printoPdf();
    }//GEN-LAST:event_printBtnActionPerformed
    
    private void clickMoveKey() {
        patientTbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (patientTbl.getModel() == analysisForVisitTM) {
                    if (e.getButton() == 1) {
                        AnalysisFrame analysisFrame=new AnalysisFrame();
                        analysisFrame.setVisible(true);
                    }
                }
                else if (patientTbl.getModel() == diagnosisForVisitTM) {
                    if (e.getButton() == 1) {
                        DoctorVisitFrame doctorVisitFrame=new DoctorVisitFrame();
                        doctorVisitFrame.setVisible(true);
                    }
                }
                if (patientTbl.getModel() == diagnosisForVisitTM||patientTbl.getModel() == analysisForVisitTM) {
                    if (e.getButton() == 3) {
                        patientTableLoad();
                    }
                } 
            }
        });

    }
    
    private void forVisitTableModelSwitch(){
        try {
            if (patientTbl.getSelectedRow() != -1) {
                String[] opcionet = {"Analizat", "Vizitat"};
                int response = JOptionPane.showOptionDialog(this,
                        "Dëshironi të shihni analizat apo vizitat e pacientit?", null,
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, opcionet, opcionet[0]);
                if (response == 0) {
                    Patient victimPatient = patientTM.getPatient(patientTbl.getSelectedRow());
                    if(analysisForVisitIr.findByPatient(victimPatient.getPatientID()).size()==0){
                        throw new AppException("Nuk ka analiza per kete pacient");
                    }
                    else{
                        patientAnalysisTableLoad(victimPatient.getPatientID());
                    }    
                }
                else if(response == 1){
                    Patient victimPatient = patientTM.getPatient(patientTbl.getSelectedRow());
                    
                    /*if(diagnosisForVisitIr.findByPatient(victimPatient.getPatientID()).size()==0){
                        throw new AppException("Nuk ka vizita per kete pacient");
                    }*/
                    //else{
                    JOptionPane.showMessageDialog(this,diagnosisForVisitIr.findByPatient(victimPatient.getPatientID()).size());
                    patientDoctorVisitTableLoad(victimPatient.getPatientID());
                    
                    //}
                }
            } else {
                throw new AppException("Selekto Userin qe deshiron me e fshi.");
            }
        } catch (AppException ae) {
            JOptionPane.showMessageDialog(this, ae.getMessage());
        }
    }
    
    public void printoPdf(){
        MessageFormat header= new MessageFormat("Report Print");
        
        MessageFormat footer=new MessageFormat("");
        
        try{
            patientTbl.print(JTable.PrintMode.FIT_WIDTH,header,footer);
        }
        catch (Exception e)
        {
            System.err.format("Cannot Print ",e.getMessage());
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable patientTbl;
    private javax.swing.JButton printBtn;
    private javax.swing.JTextField searchTxtf;
    // End of variables declaration//GEN-END:variables
}
