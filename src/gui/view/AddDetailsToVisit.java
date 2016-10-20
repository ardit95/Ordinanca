package gui.view;

import ExceptionPackage.AppException;
import ExceptionPackage.StopException;
import bl.DiagnosisForVisitInterface;
import bl.DiagnosisForVisitRepository;
import bl.DiagnosisInterface;
import bl.DiagnosisRepository;
import bl.DoctorVisitInterface;
import bl.DoctorVisitRepository;
import ejb.DiagnosisForVisit;
import ejb.DoctorVisit;
import ejb.Staff;
import gui.model.DoctorVisitTableModel;
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
DoctorVisitTableModel doctorVisitTM;

    public AddDetailsToVisit(EntityManager entityManager,Staff currentUser) {
        this.currentUser=currentUser;
        this.entityManager=entityManager;
        initComponents();
        initInterfaces(this.entityManager);
        initTableModels();
        setLocation(220, 10);
        visitTableLoad();
        addDetailsToVisitFormListeners();
        mainScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        /*DefaultCaret caret = (DefaultCaret)complaintTxtf.getCaret();
         caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
         mainScrollPane.setViewportView(complaintTxtf);*/
        //int currentCaretPosition = anamnesisTxtf.getCaretPosition();
        //mainScrollPane.getViewport().scrollRectToVisible(focused.getBounds());
        //mainScrollPane.getVerticalScrollBar().setValue(currentCaretPosition);
        scroll();
        threadScroll2();
    }
    
    public void visitTableLoad(){
        List <DoctorVisit> visitList;
        if(seeAllCBox.isSelected())
            visitList= doctorVisitIr.findAllForCurrentUser(currentUser);
        else 
            visitList= doctorVisitIr.findPresentAndFuture(currentUser);
        
        if(!visitList.isEmpty()){
            doctorVisitTM.add(visitList);
            visitTbl.setModel(doctorVisitTM);
            doctorVisitTM.fireTableDataChanged();
        }
    }
    
    private void initInterfaces(EntityManager entityManager) {
        doctorVisitIr=new DoctorVisitRepository(entityManager);
        diagnosisIr=new DiagnosisRepository(entityManager);
        diagnosisForVisitIr= new DiagnosisForVisitRepository(entityManager);
    }
    
    private void initTableModels(){
        String[] doctorVisitColumns={"Date","SumPrice","Remark","Finished","PatientID","DoctorID","StaffID"};
        doctorVisitTM=new DoctorVisitTableModel(doctorVisitColumns);
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
        jLabel4 = new javax.swing.JLabel();
        patientGenderLbl = new javax.swing.JLabel();
        patientDateOfBirthLbl = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        placeOfBirthLbl = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        patientPlaceOfBirthLbl = new javax.swing.JLabel();
        patientCityLbl = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        phoneLbl = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        patientPhoneLbl = new javax.swing.JLabel();
        patientEmailLbl = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
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
        clearBtn = new javax.swing.JButton();
        saveBtn = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        visitTbl = new javax.swing.JTable();
        seeAllCBox = new javax.swing.JCheckBox();

        setClosable(true);
        setTitle("Add Notification");
        setMaximumSize(new java.awt.Dimension(1100, 654));
        setMinimumSize(new java.awt.Dimension(1100, 654));
        setPreferredSize(new java.awt.Dimension(1100, 654));

        nameLbl.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        nameLbl.setText("Name:");

        patientNameLbl.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        patientNameLbl.setMaximumSize(new java.awt.Dimension(40, 14));
        patientNameLbl.setMinimumSize(new java.awt.Dimension(40, 14));
        patientNameLbl.setPreferredSize(new java.awt.Dimension(40, 14));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nameLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(patientNameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(198, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLbl)
                    .addComponent(patientNameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1))
        );

        genderLbl.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        genderLbl.setText("Gender:");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("DateOfBirth:");

        patientGenderLbl.setMaximumSize(new java.awt.Dimension(40, 14));
        patientGenderLbl.setMinimumSize(new java.awt.Dimension(40, 14));
        patientGenderLbl.setPreferredSize(new java.awt.Dimension(40, 14));

        patientDateOfBirthLbl.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(genderLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(patientGenderLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(patientDateOfBirthLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(genderLbl)
                .addComponent(patientGenderLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(patientDateOfBirthLbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        placeOfBirthLbl.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        placeOfBirthLbl.setText("PlaceOFBirth:");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("City:");

        patientPlaceOfBirthLbl.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);

        patientCityLbl.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(placeOfBirthLbl)
                .addGap(18, 18, 18)
                .addComponent(patientPlaceOfBirthLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(patientCityLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(placeOfBirthLbl)
                .addComponent(patientPlaceOfBirthLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(patientCityLbl, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        phoneLbl.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        phoneLbl.setText("Phone:");

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel11.setText("Email:");

        patientPhoneLbl.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);

        patientEmailLbl.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(phoneLbl)
                .addGap(18, 18, 18)
                .addComponent(patientPhoneLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(patientEmailLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(phoneLbl)
                .addComponent(patientPhoneLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(patientEmailLbl, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel1.setText("Allergies:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        mainScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel4.setMaximumSize(new java.awt.Dimension(164, 94));
        jPanel4.setMinimumSize(new java.awt.Dimension(164, 94));

        jScrollPane5.setMaximumSize(new java.awt.Dimension(166, 56));
        jScrollPane5.setMinimumSize(new java.awt.Dimension(166, 56));
        jScrollPane5.setPreferredSize(new java.awt.Dimension(166, 56));
        jScrollPane5.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                jScrollPane5MouseWheelMoved(evt);
            }
        });

        complaintTxtf.setColumns(20);
        complaintTxtf.setLineWrap(true);
        complaintTxtf.setRows(5);
        complaintTxtf.setToolTipText("Write down the remark about this visit");
        complaintTxtf.setWrapStyleWord(true);
        complaintTxtf.setMaximumSize(new java.awt.Dimension(164, 94));
        complaintTxtf.setMinimumSize(new java.awt.Dimension(164, 94));
        jScrollPane5.setViewportView(complaintTxtf);

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

        clearBtn.setText("Clear");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        saveBtn.setText("Save");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGap(6, 6, 6)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(clearBtn)
                    .addComponent(saveBtn)))
        );

        mainScrollPane.setViewportView(mainPanel);

        javax.swing.GroupLayout backgroundLblLayout = new javax.swing.GroupLayout(backgroundLbl);
        backgroundLbl.setLayout(backgroundLblLayout);
        backgroundLblLayout.setHorizontalGroup(
            backgroundLblLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLblLayout.createSequentialGroup()
                .addGroup(backgroundLblLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        backgroundLblLayout.setVerticalGroup(
            backgroundLblLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLblLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1116, Short.MAX_VALUE))
        );

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

        seeAllCBox.setText("All");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(backgroundLbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seeAllCBox))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(backgroundLbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(seeAllCBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        clearObject();
    }//GEN-LAST:event_clearBtnActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        try {
            if(0==0)
            throw new AppException("");
            else if (1==1)
            throw new StopException("");

            validation();
            clearObject();

            JOptionPane.showMessageDialog(this, "The visit was stored sucsesfully.");
        } catch (AppException ae) {
            ae.printStackTrace();
            JOptionPane.showMessageDialog(this, ae.getMessage());
        }catch(StopException se){
            se.printStackTrace();
        }
    }//GEN-LAST:event_saveBtnActionPerformed
    
    
    
    private void jScrollPane5MouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jScrollPane5MouseWheelMoved
       
    }//GEN-LAST:event_jScrollPane5MouseWheelMoved
    
    private void scroll(){
        
        AbstractAction transferFocus = new AbstractAction ()
            {
                public void actionPerformed ( ActionEvent e )
                {
                    ( ( Component ) e.getSource () ).transferFocus ();
                }
            };
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
            
           mainScrollPane.getVerticalScrollBar().setValue(jPanel6.getLocation().y);
            
    }
    
    private void threadScroll2(){
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
            }
          });
        }
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel anamensisLbl;
    private javax.swing.JTextArea anamnesisTxtf;
    private javax.swing.JPanel backgroundLbl;
    private javax.swing.JButton clearBtn;
    private javax.swing.JLabel complaintLbl;
    private javax.swing.JTextArea complaintTxtf;
    private javax.swing.JLabel examinationLbl;
    private javax.swing.JTextArea examinationTxtf;
    private javax.swing.JLabel genderLbl;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextArea jTextArea1;
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
    private javax.swing.JLabel recommendationLbl;
    private javax.swing.JTextArea recommendationTxtf;
    private javax.swing.JButton saveBtn;
    private javax.swing.JCheckBox seeAllCBox;
    private javax.swing.JLabel therapyLbl;
    private javax.swing.JTextArea therapyTxtf;
    private javax.swing.JTable visitTbl;
    // End of variables declaration//GEN-END:variables

    private void validation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void clearObject() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    
}
