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
import ejb.Analysis;
import ejb.AnalysisForVisit;
import ejb.AnalysisVisit;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import ejb.Diagnosis;
import ejb.DiagnosisForVisit;
import ejb.DoctorVisit;
import ejb.Patient;
import ejb.Staff;
import gui.model.AnalysisVisitTableModel;
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
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.KeyboardFocusManager;
import java.awt.MouseInfo;
import java.awt.Panel;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.DateFormatter;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.DefaultCaret;

public class AddDetailsToVisit extends javax.swing.JInternalFrame {
EntityManager entityManager;
Staff currentUser;

DoctorVisitInterface doctorVisitIr;
DiagnosisInterface diagnosisIr;
DiagnosisForVisitInterface diagnosisForVisitIr;
AnalysisVisitInterface analysisVisitIr;
AnalysisInterface analysisIr;
AnalysisForVisitInterface analysisForVisitIr;
PatientInterface patientIr;
DoctorVisitTableModel doctorVisitTM;
AnalysisVisitTableModel analysisVisitTM;
DiagnosisForVisitTableModel diagnosisForVisitTM;
DoctorVisit mainDoctorVisit;
AnalysisVisit mainAnalysisVisit;
MainFrame mainFrame;

    private javax.swing.JLabel resultsLbl;
    private javax.swing.JLabel titleOfAnalysisLbl;
    private javax.swing.JPanel analysisPanel;
    private javax.swing.JTextArea resultTxtf;
    private javax.swing.JTextField analysisTxtf;
    JTextField analysisPriceTxtf;
 
/*Info needed for doctor visit print*/
String patientNameForVisit="";
String patientSurnameForVisit="";
String patientGenderForVisit="";
String patientDateOfBirthForVisit="";
String patientAllergiesForVisit="";
int doctorVisitId=0;
String doctorNameForVisit="";
String doctorSurnameForVisit="";
String complaintForVisit="";
String anamnesisForVisit="";
String examinationForVisit="";
String therapyForVisit="";
String recommendationForVisit="";
String priceForVisit="";
String dateForVisit="";

/*Info needed for analysis visit print*/
int analysisVisitId=0;
String patientNameForAnalysis="";
String patientSurnameForAnalysis="";
String patientGenderForAnalysis="";
String patientDateOfBirthForAnalysis="";
String patientAllergiesForAnalysis="";
String doctorNameForAnalysis="";
String doctorSurnameForAnalysis="";
String analysisForAnalysis="";
String resultsForAnalysis="";
String priceForAnalysis="";
String dateForAnalysis="";

    public AddDetailsToVisit(EntityManager entityManager,Staff currentUser,MainFrame mainFrame) {
        this.currentUser=currentUser;
        this.entityManager=entityManager;
        this.mainFrame=mainFrame;
        initComponents();
        initInterfaces();
        initTableModels();
        setLocation(220, 10);
        visitTableLoad();
        addDetailsToVisitFormListeners();
        visitTblListeners();
        mainScrollBarMethods();
        setPriority();
    }
    
    public void setPriority(){
        if(currentUser.getRole().equals("LaboratorTechnician")){
            mainScrollPane.setVisible(false);

            resultTxtf = new javax.swing.JTextArea();
            analysisTxtf= new javax.swing.JTextField();
            analysisPanel = new javax.swing.JPanel();
            resultsLbl = new javax.swing.JLabel();
            titleOfAnalysisLbl= new javax.swing.JLabel();

            backgroundPanel.setBackground(new java.awt.Color(102, 102, 102));
        backgroundPanel.setLayout(null);

        analysisPanel.setMaximumSize(new java.awt.Dimension(164, 94));
        analysisPanel.setMinimumSize(new java.awt.Dimension(164, 94));
        analysisPanel.setOpaque(false);
        analysisPanel.setPreferredSize(new java.awt.Dimension(491, 287));

        jScrollPane6.setMaximumSize(new java.awt.Dimension(166, 56));
        jScrollPane6.setMinimumSize(new java.awt.Dimension(166, 56));
        jScrollPane6.setPreferredSize(new java.awt.Dimension(166, 56));
        

        resultTxtf.setColumns(20);
        resultTxtf.setLineWrap(true);
        resultTxtf.setRows(5);
        resultTxtf.setToolTipText("Write down the remark about this visit");
        resultTxtf.setWrapStyleWord(true);
        resultTxtf.setMaximumSize(new java.awt.Dimension(164, 94));
        resultTxtf.setMinimumSize(new java.awt.Dimension(164, 94));
        resultTxtf.setEditable(true);
        jScrollPane6.setViewportView(resultTxtf);
        priceTxtf.setEditable(true);

        resultsLbl.setForeground(new java.awt.Color(255, 255, 255));
        resultsLbl.setText("Results:");
        titleOfAnalysisLbl.setText("Title Of Analysis:");
        titleOfAnalysisLbl.setForeground(Color.WHITE);
        priceLbl.setText("Price:");

        javax.swing.GroupLayout analysisPanelLayout = new javax.swing.GroupLayout(analysisPanel);
        analysisPanel.setLayout(analysisPanelLayout);
        analysisPanelLayout.setHorizontalGroup(
            analysisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(analysisPanelLayout.createSequentialGroup()
                .addGroup(analysisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(analysisPanelLayout.createSequentialGroup()
                        .addContainerGap(283, Short.MAX_VALUE)
                        .addComponent(priceLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(priceTxtf, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(analysisPanelLayout.createSequentialGroup()
                        .addComponent(resultsLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(titleOfAnalysisLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(analysisTxtf)))
                .addContainerGap())
        );
        analysisPanelLayout.setVerticalGroup(
            analysisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(analysisPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(analysisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(resultsLbl)
                    .addComponent(titleOfAnalysisLbl)
                    .addComponent(analysisTxtf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(analysisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(priceTxtf, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(priceLbl))
                .addContainerGap())
        );
            
            

            backgroundPanel.add(analysisPanel);
            analysisPanel.setBounds(0, 350, 491, 255);
        }
    }
    
    
    
    
    public void mainScrollBarMethods(){
        setScrollPosition();
        changeTab();
        mainScrollPane.getVerticalScrollBar().setUnitIncrement(16);
    }
    
    private static class JTableButtonRenderer implements TableCellRenderer {        
        @Override public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JButton button = (JButton)value;
            return button;  
        }
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
                    Patient patient = null;
                    if (currentUser.getRole().equals("Doctor")) {
                        if (visitTbl.getModel() == doctorVisitTM) {
                            try {
                                mainDoctorVisit = doctorVisitTM.getDoctorVisit(selectedIndex);
                                patient = mainDoctorVisit.getPatientID();

                                if (patient == null) {
                                    String[] opcionet = {"Yes", "No"};
                                    int response = JOptionPane.showOptionDialog(null,
                                            "You have to assign a pacient to a visit before giving a diagnosis to it. Press yes to add the patient and no to stop.", "Warning",
                                            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                                            null, opcionet, opcionet[0]);
                                    if (response == 0) {
                                        SetPatientToVisit setPatientToVisit = new SetPatientToVisit(AddDetailsToVisit.this, mainDoctorVisit, entityManager, currentUser,mainFrame);
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
                    } else if (currentUser.getRole().equals("LaboratorTechnician")) {
                        try {
                            if (visitTbl.getModel() == analysisVisitTM) {
                                mainAnalysisVisit = analysisVisitTM.getAnalysisVisit(selectedIndex);
                                patient = mainAnalysisVisit.getPatientID();
                                if (patient == null) {
                                    String[] opcionet = {"Yes", "No"};
                                    int response = JOptionPane.showOptionDialog(null,
                                            "You have to assign a pacient to a visit before doing an analysis to it. Press yes to add the patient and no to stop.", "Warning",
                                            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                                            null, opcionet, opcionet[0]);
                                    if (response == 0) {
                                        SetPatientToVisit setPatientToVisit = new SetPatientToVisit(AddDetailsToVisit.this, mainAnalysisVisit, entityManager, currentUser,mainFrame);
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
                                        visitTbl.clearSelection();
                                        throw new AppException("The user choose to assign a pacient");
                                    } else {
                                        visitTbl.clearSelection();
                                        throw new StopException("The user doesn't want to continue");
                                    }
                                }
                            }
                            setPatientData(patient);
                        } catch (AppException ae) {
                        } catch (StopException se) {
                        }

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
                                    infoLbl.setForeground(Color.BLACK);
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
                    clearDiagnosis();
                }
            }
        });
    }
    
    public void diagnosisForVisitTableLoad(int row)throws AppException{
        List<DiagnosisForVisit> diagnosisForVisitList=diagnosisForVisitIr.findByVisit(doctorVisitTM.getDoctorVisit(row));
        if(diagnosisForVisitList==null || diagnosisForVisitList.isEmpty())
            throw new AppException("There's no diagnosis for this patient.");
        diagnosisForVisitTM.add(diagnosisForVisitList);
        visitTbl.setModel(diagnosisForVisitTM);
        diagnosisForVisitTM.fireTableDataChanged();
    }
    
    public void visitTableLoad(){
        if(currentUser.getRole().equals("Doctor")){
            List<DoctorVisit> visitList;
            if (seeAllCBox.isSelected()) {
                visitList = doctorVisitIr.findAllForCurrentUser(currentUser);
            } else {
                visitList = doctorVisitIr.findPresentAndFuture(currentUser);
            }

            visitTbl.setModel(doctorVisitTM);
            doctorVisitTM.add(visitList);
            doctorVisitTM.fireTableDataChanged();

            TableCellRenderer buttonRenderer = new JTableButtonRenderer();
            visitTbl.getColumn("Button").setCellRenderer(buttonRenderer);
        }else if (currentUser.getRole().equals("LaboratorTechnician")){
            List<AnalysisVisit> analysisVisit;
            if(seeAllCBox.isSelected()){
                analysisVisit= analysisVisitIr.findAllForCurrentUser(currentUser);
            }else{
                analysisVisit= analysisVisitIr.findPresentAndFuture(currentUser);
            }
            visitTbl.setModel(analysisVisitTM);
            analysisVisitTM.add(analysisVisit);
            analysisVisitTM.fireTableDataChanged();
        }
    }
        
        public void setButtonListener(JButton target,int index){
            target.addActionListener(new ActionListener(){
                
                @Override
                public void actionPerformed(ActionEvent ae){
                    try {
                        diagnosisForVisitTableLoad(index);
                    } catch (AppException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }
            });
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
        printBtn = new javax.swing.JButton();
        infoLbl = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Add Notification");
        setMaximumSize(new java.awt.Dimension(1100, 654));
        setMinimumSize(new java.awt.Dimension(1100, 654));
        setPreferredSize(new java.awt.Dimension(1100, 654));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        backgroundPanel.add(jPanel2);
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
        complaintTxtf.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                complaintTxtfMouseWheelMoved(evt);
            }
        });
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
                .addContainerGap(353, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(complaintLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel5.setMaximumSize(new java.awt.Dimension(164, 94));
        jPanel5.setMinimumSize(new java.awt.Dimension(164, 94));
        jPanel5.setOpaque(false);
        jPanel5.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                jPanel5MouseWheelMoved(evt);
            }
        });

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

        backgroundPanel.add(mainScrollPane);
        mainScrollPane.setBounds(0, 340, 502, 260);

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

        saveBtn.setBackground(new java.awt.Color(0, 153, 102));
        saveBtn.setForeground(new java.awt.Color(204, 255, 204));
        saveBtn.setText("Save");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        printBtn.setBackground(new java.awt.Color(0, 153, 102));
        printBtn.setForeground(new java.awt.Color(204, 255, 204));
        printBtn.setText("Print Visit");
        printBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(printBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clearBtn)
                    .addComponent(saveBtn)
                    .addComponent(printBtn))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        backgroundPanel.add(jPanel1);
        jPanel1.setBounds(530, 580, 490, 23);

        getContentPane().add(backgroundPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 0, 1080, 620));

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
        mainFrame.keepRunning=false;
        try {
            validation();
            if(currentUser.getRole().equals("Doctor")){
                Diagnosis diagnosis = new Diagnosis(complaintTxtf.getText().trim(), examinationTxtf.getText().trim(),
                        anamnesisTxtf.getText().trim(), therapyTxtf.getText().trim(), recommendationTxtf.getText().trim());
                diagnosis = diagnosisIr.create(diagnosis);
                DiagnosisForVisit diagnosisForVisit = new DiagnosisForVisit(diagnosis, mainDoctorVisit, Double.parseDouble(priceTxtf.getText().trim()));
                diagnosisForVisitIr.create(diagnosisForVisit);
                mainDoctorVisit.setSumPrice(mainDoctorVisit.getSumPrice() + diagnosisForVisit.getPrice());
                if (!mainDoctorVisit.getFinished().equals("Yes")) {
                    mainDoctorVisit.setFinished("Yes");
                }
                doctorVisitIr.edit(mainDoctorVisit);
            }else if (currentUser.getRole().equals("LaboratorTechnician")){
                Analysis analysis = new Analysis (analysisTxtf.getText().trim(),resultTxtf.getText().trim());
                analysisIr.create(analysis);
                AnalysisForVisit analysisForVisit = new AnalysisForVisit(Double.parseDouble(priceTxtf.getText().trim()),analysis,mainAnalysisVisit);
                analysisForVisitIr.create(analysisForVisit);
                mainAnalysisVisit.setSumPrice(mainAnalysisVisit.getSumPrice()+analysisForVisit.getPrice());
                analysisVisitIr.create(mainAnalysisVisit);
            }
            
            clearObject();
            clearDiagnosis();
            JOptionPane.showMessageDialog(this, "The diagnosis was added sucsesfully to this visit.");
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
    }//GEN-LAST:event_saveBtnActionPerformed

    private void printBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printBtnActionPerformed
        //printi duhet me i shtyp krejt diagnozat e vizites
        int selectedRow=visitTbl.getSelectedRow();
        if(visitTbl.getModel()==doctorVisitTM&&selectedRow>-1){
            setInfoForDoctorVisitPrint();
        }
        if(visitTbl.getModel()==analysisVisitTM&&selectedRow>-1){
            setInfoForAnalysisVisitPrint();
        }
        
        JFileChooser chooser = new JFileChooser(); 
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Chooser");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        String directory="";
        //
        // disable the "All files" option.
        //
        chooser.setAcceptAllFileFilterUsed(false);
        //    
        
        if(visitTbl.getModel()==doctorVisitTM&&selectedRow>-1){
            if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){

                directory=chooser.getSelectedFile().toString()+"\\";

                    printDocotorVisitPdf(directory);

            }
            else{
                JOptionPane.showMessageDialog(null, "Duhet te zgjedhni lokacionin se ku deshironi te ruani file ");
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Choose a visit to print !");
        }
        
        if(visitTbl.getModel()==analysisVisitTM&&selectedRow>-1){
            if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){

                directory=chooser.getSelectedFile().toString()+"\\";

                    printAnalysisVisitPdf(directory);

            }
            else{
                JOptionPane.showMessageDialog(null, "Duhet te zgjedhni lokacionin se ku deshironi te ruani file ");
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Choose a visit to print !");
        }
    }//GEN-LAST:event_printBtnActionPerformed

    private void jPanel5MouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jPanel5MouseWheelMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel5MouseWheelMoved

    private void complaintTxtfMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_complaintTxtfMouseWheelMoved
       
    }//GEN-LAST:event_complaintTxtfMouseWheelMoved
    
    private void printDocotorVisitPdf(String directory){
        Document document=new Document();
        try{
            
            String path=directory+" "+dateForVisit+"-"+patientNameForVisit+" "+patientSurnameForVisit+" - "+doctorNameForVisit+" "+doctorSurnameForVisit+" - "+".pdf";
            PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();
           
            
            
            
            Paragraph para=new Paragraph();
            Chunk t=new Chunk("Klinika Mjeksore diqka",FontFactory.getFont(FontFactory.HELVETICA,18,Font.BOLD));
            Chunk space=new Chunk("\n");
            Phrase prat=new Phrase();
            
            prat.add(space);
            prat.add(space);
            prat.add(t);
            para.setAlignment(Element.ALIGN_CENTER);
            para.add(prat);
           
            document.add(para);
            
            
            
            Phrase pha1=new Phrase();
            Phrase pha2=new Phrase();
            Phrase pha3=new Phrase();
            
            Paragraph para1=new Paragraph();
            Paragraph para2=new Paragraph();
            Paragraph para3=new Paragraph();
            
            Chunk glue2 = new Chunk(new VerticalPositionMark());
            
            
            Chunk c1=new Chunk("Pacienti :"+patientNameForVisit+" "+patientSurnameForVisit,FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
            
            Chunk c2=new Chunk("Gjinia :"+patientGenderForVisit,FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
            
            Chunk c3=new Chunk("Data e Lindjes :"+patientDateOfBirthForVisit,FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
            
            Chunk c4=new Chunk("Alergjitë :"+patientAllergiesForVisit,FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
            
            
            
            pha1.add(space);
            pha1.add(space);
            pha1.add(c1);
            pha1.add(space);
            pha1.add(c2);
            pha1.add(space);
            pha1.add(c3);
            pha1.add(space);
            pha1.add(c4);
            
            
            para1.add(pha1);
            
            Chunk c10=new Chunk("Ankesat :",FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
            Chunk c11=new Chunk(complaintForVisit);
            Chunk c12=new Chunk("Anamneza :",FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
            Chunk c13=new Chunk(anamnesisForVisit);
            Chunk c14=new Chunk("Ekzaminimi :",FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
            Chunk c15=new Chunk(examinationForVisit);
            Chunk c16=new Chunk("Terapia :",FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
            Chunk c17=new Chunk(therapyForVisit);
            Chunk c18=new Chunk("Rekomandimi :",FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
            Chunk c19=new Chunk(recommendationForVisit);
            
            pha2.add(space);
            pha2.add(space);
            pha2.add(c10);
            pha2.add(space);
            pha2.add(c11);
            pha2.add(space);
            pha2.add(c12);
            pha2.add(space);
            pha2.add(c13);
            pha2.add(space);
            pha2.add(c14);
            pha2.add(space);
            pha2.add(c15);
            pha2.add(space);
            pha2.add(c16);
            pha2.add(space);
            pha2.add(c17);
            pha2.add(space);
            pha2.add(c18);
            pha2.add(space);
            pha2.add(c19);
            
            para2.add(pha2);
            
            
            Chunk c20=new Chunk("Doktori :"+doctorNameForVisit+" "+doctorSurnameForVisit,FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
            Chunk c21=new Chunk("Data :"+dateForVisit,FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
            Chunk c22=new Chunk("Çmimi : "+priceForVisit,FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
            
            pha3.add(space);
            pha3.add(space);
            pha3.add(space);
            pha3.add(c20);
            pha3.add(space);
            pha3.add(c21);
            pha3.add(new Chunk(glue2));
            pha3.add(c22);
            
            para3.add(pha3);
            
            
            
            document.add(para1);
            document.add(para2);
            document.add(para3);
            
            
            
            document.close();
            JOptionPane.showMessageDialog(null,"U ruajt me sukses");
        }catch(FileNotFoundException | DocumentException | HeadlessException e){
            e.printStackTrace();
        }
    }
    
    private void printAnalysisVisitPdf(String directory){
        Document document=new Document();
        try{
            
            String path=directory+" "+dateForAnalysis+"-"+patientNameForAnalysis+" "+patientSurnameForAnalysis+" - "+doctorNameForAnalysis+" "+doctorSurnameForAnalysis+" - "+".pdf";
            PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();
           
            
            
            
            Paragraph para=new Paragraph();
            Chunk t=new Chunk("Klinika Mjeksore diqka",FontFactory.getFont(FontFactory.HELVETICA,18,Font.BOLD));
            Chunk space=new Chunk("\n");
            Phrase prat=new Phrase();
            
            prat.add(space);
            prat.add(space);
            prat.add(t);
            para.setAlignment(Element.ALIGN_CENTER);
            para.add(prat);
           
            document.add(para);
            
            
            
            Phrase pha1=new Phrase();
            Phrase pha2=new Phrase();
            Phrase pha3=new Phrase();
            
            Paragraph para1=new Paragraph();
            Paragraph para2=new Paragraph();
            Paragraph para3=new Paragraph();
            
            Chunk glue2 = new Chunk(new VerticalPositionMark());
            
            
            Chunk c1=new Chunk("Pacienti :"+patientNameForAnalysis+" "+patientSurnameForAnalysis,FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
            
            Chunk c2=new Chunk("Gjinia :"+patientGenderForAnalysis,FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
            
            Chunk c3=new Chunk("Data e Lindjes :"+patientDateOfBirthForAnalysis,FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
            
            Chunk c4=new Chunk("Alergjitë :"+patientAllergiesForAnalysis,FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
            
            
            
            pha1.add(space);
            pha1.add(space);
            pha1.add(c1);
            pha1.add(space);
            pha1.add(c2);
            pha1.add(space);
            pha1.add(c3);
            pha1.add(space);
            pha1.add(c4);
            
            
            para1.add(pha1);
            
            Chunk c10=new Chunk("Analiza :",FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
            Chunk c11=new Chunk(analysisForAnalysis);
            Chunk c12=new Chunk("Rezultati :",FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
            Chunk c13=new Chunk(resultsForAnalysis);
            
            pha2.add(space);
            pha2.add(space);
            pha2.add(c10);
            pha2.add(space);
            pha2.add(c11);
            pha2.add(space);
            pha2.add(c12);
            pha2.add(space);
            pha2.add(c13);
            
            para2.add(pha2);
            
            
            Chunk c20=new Chunk("Laboranti :"+doctorNameForAnalysis+" "+doctorSurnameForAnalysis,FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
            Chunk c21=new Chunk("Data :"+dateForAnalysis,FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
            Chunk c22=new Chunk("Çmimi : "+priceForAnalysis,FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
            
            pha3.add(space);
            pha3.add(space);
            pha3.add(space);
            pha3.add(c20);
            pha3.add(space);
            pha3.add(c21);
            pha3.add(new Chunk(glue2));
            pha3.add(c22);
            
            para3.add(pha3);
            
            
            
            document.add(para1);
            document.add(para2);
            document.add(para3);
            
            
            
            document.close();
            JOptionPane.showMessageDialog(null,"U ruajt me sukses");
        }catch(FileNotFoundException | DocumentException | HeadlessException e){
            e.printStackTrace();
        }
    }
    
    private void setInfoForDoctorVisitPrint(){
        
        DoctorVisit selectedDoctorVisit=new DoctorVisit();
        int selectedRow=visitTbl.getSelectedRow();
        if(visitTbl.getModel()==doctorVisitTM&&selectedRow>-1){
            selectedDoctorVisit=doctorVisitTM.getDoctorVisit(selectedRow);
        }
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
        
        doctorVisitId=selectedDoctorVisit.getDoctorVisitID();
        patientNameForVisit=selectedDoctorVisit.getPatientID().getName();
        patientSurnameForVisit=selectedDoctorVisit.getPatientID().getSurname();
        patientDateOfBirthForVisit=sdf.format(selectedDoctorVisit.getPatientID().getDateOfBirth());
        patientGenderForVisit=selectedDoctorVisit.getPatientID().getGender();
        patientAllergiesForVisit=selectedDoctorVisit.getPatientID().getAllergies();
        doctorNameForVisit=selectedDoctorVisit.getDoctorID().getName();
        doctorSurnameForVisit=selectedDoctorVisit.getDoctorID().getSurname();
        
        priceForVisit=selectedDoctorVisit.getSumPrice()+"";
        dateForVisit=sdf.format(selectedDoctorVisit.getTimeStamp());
        
        List<Diagnosis>diagnosis=diagnosisIr.findByDiagnosisForVisit(doctorVisitId);
        String allComplaints="";
        String allAnamnesis="";
        String allExaminations="";
        String allTherapys="";
        String allRecommendations="";
        for(int i=0;i<diagnosis.size();i++){
            int nr=i+1;
            allComplaints+="    Ankesa "+ nr +" : "+diagnosis.get(i).getComplaint()+" . \n";
            allAnamnesis+="    Anamneza "+ nr +" : "+diagnosis.get(i).getAnamnesis()+" . \n";
            allExaminations+="    Examinimi "+ nr +" : "+diagnosis.get(i).getExamination()+" . \n";
            allTherapys+="    Therapia "+ nr +" : "+diagnosis.get(i).getTherapy()+" . \n";
            allRecommendations+="    Recommendimi "+ nr +" : "+diagnosis.get(i).getRecommendation()+" . \n";
        }
        
        
        complaintForVisit=allComplaints;
        anamnesisForVisit=allAnamnesis;
        examinationForVisit=allExaminations;
        therapyForVisit=allTherapys;
        recommendationForVisit=allRecommendations;
    }
    
    public void clearDiagnosis(){
        complaintTxtf.setText("");
        examinationTxtf.setText("");
        anamnesisTxtf.setText("");
        therapyTxtf.setText("");
        recommendationTxtf.setText("");
        mainScrollPane.getVerticalScrollBar().setValue(0);
        complaintTxtf.requestFocus();
    }
    
    
    private void setInfoForAnalysisVisitPrint(){
        
        AnalysisVisit selectedAnalysisVisit=new AnalysisVisit();
        int selectedRow=visitTbl.getSelectedRow();
        if(visitTbl.getModel()==analysisVisitTM&&selectedRow>-1){
            selectedAnalysisVisit=analysisVisitTM.getAnalysisVisit(selectedRow);
        }
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
        
        analysisVisitId=selectedAnalysisVisit.getAnalysisVisitID();
        patientNameForAnalysis=selectedAnalysisVisit.getPatientID().getName();
        patientSurnameForAnalysis=selectedAnalysisVisit.getPatientID().getSurname();
        patientDateOfBirthForAnalysis=sdf.format(selectedAnalysisVisit.getPatientID().getDateOfBirth());
        patientGenderForAnalysis=selectedAnalysisVisit.getPatientID().getGender();
        patientAllergiesForAnalysis=selectedAnalysisVisit.getPatientID().getAllergies();
        doctorNameForAnalysis=selectedAnalysisVisit.getLaboratorTechnicianID().getName();
        doctorSurnameForAnalysis=selectedAnalysisVisit.getLaboratorTechnicianID().getSurname();
        
        priceForAnalysis=selectedAnalysisVisit.getSumPrice()+"";
        dateForAnalysis=sdf.format(selectedAnalysisVisit.getTimeStamp());
        
        List<Analysis> analysis=analysisIr.findByAnalysisForVisit(analysisVisitId);
        String allAnalysis="";
        String allResults="";
        for(int i=0;i<analysis.size();i++){
            allAnalysis+="Analysis "+ (i+1)+":"+analysis.get(i).getAnalysis();
            allResults+="Results "+ (i+1)+":"+analysis.get(i).getResults();
        }
        analysisForAnalysis=allAnalysis;
        resultsForAnalysis=allResults;
        
    }
    
    
    
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel allergiesLbl;
    private javax.swing.JTextArea allergiesTxtf;
    private javax.swing.JLabel anamensisLbl;
    private javax.swing.JTextArea anamnesisTxtf;
    private javax.swing.JLabel background;
    private javax.swing.JPanel backgroundPanel;
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
    private javax.swing.JButton printBtn;
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
        if (currentUser.getRole().equals("Doctor")) {
            if(mainDoctorVisit==null)
                throw new AppException("You must select a DoctorVisit from the table.");
            
            if (mainDoctorVisit.getPatientID() == null) {
                throw new AppException("You must assign a patient to the visit before saving a diagnostic for it.");
            }

            if (complaintTxtf.getText().trim().length() >= 300) {
                complaintTxtf.requestFocus();
                throw new AppException("Complaints cannot contain more than 300 characters");
            }

            if (anamnesisTxtf.getText().trim().length() >= 300) {
                anamnesisTxtf.requestFocus();
                throw new AppException("Anamensis cannot contain more than 300 characters");
            }

            if (examinationTxtf.getText().trim().length() >= 300) {
                examinationTxtf.requestFocus();
                throw new AppException("Examination cannot contain more than 300 characters");
            }

            if (therapyTxtf.getText().trim().length() >= 300) {
                therapyTxtf.requestFocus();
                throw new AppException("Therapy cannot contain more than 300 characters");
            }

            if (recommendationTxtf.getText().trim().length() >= 300) {
                recommendationTxtf.requestFocus();
                throw new AppException("Therapy cannot contain more than 300 characters");
            }

            if (visitTbl.getSelectedRow() == -1) {
                throw new AppException("Choose the visit for which you want to add the diagnosis.");
            }

            if (complaintTxtf.getText().trim().isEmpty()) {
                complaintTxtf.requestFocus();
                throw new AppException("You have to write down the complaints of the patient.");
            }

            if (examinationTxtf.getText().trim().isEmpty()) {
                examinationTxtf.requestFocus();
                throw new AppException("You have to write down the examination of the patient.");
            }

            if (priceTxtf.getText().trim().isEmpty()) {
                priceTxtf.requestFocus();
                throw new AppException("You have to set a price for the diagnosis");
            }

            try {
                Double.parseDouble(priceTxtf.getText().trim());
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
                throw new AppException("Write the correct price.");
            }

            if (anamnesisTxtf.getText().trim().isEmpty()
                    || therapyTxtf.getText().trim().isEmpty() || recommendationTxtf.getText().trim().isEmpty()) {
                String s = "Are you sure you want to leave ";
                if (anamnesisTxtf.getText().trim().isEmpty()) {
                    s += " anamnesis ,";
                }
                if (therapyTxtf.getText().trim().isEmpty()) {
                    s += " therapy ,";
                }
                if (recommendationTxtf.getText().trim().isEmpty()) {
                    s += " recommendation ,";
                }
                s = s.substring(0, s.length() - 2);
                s += " empty ?";
                String[] opcionet = {"Po", "Jo"};
                int response = JOptionPane.showOptionDialog(null, s, "Warning",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, opcionet, opcionet[0]);
                if (response == 0) {
                } else {
                    throw new StopException("The user wants to fill the data from nullable fields");
                }
            }

            if (!allergiesTxtf.getText().trim().equals(mainDoctorVisit.getPatientID().getAllergies())) {
                Patient patient = mainDoctorVisit.getPatientID();
                String[] opcionet = {"Yes", "No", "Cancel"};
                int response = JOptionPane.showOptionDialog(null,
                        "You have changed the allergies of the patient press yes to save them this way or no to return them to the past value.(Cancel to stop the registration)", "Warning",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, opcionet, opcionet[0]);
                if (response == 0) {
                    patient.setAllergies(allergiesTxtf.getText().trim());
                    patientIr.edit(patient);
                } else if (response == 1) {
                    allergiesTxtf.setText(mainDoctorVisit.getPatientID().getAllergies());
                } else {
                    throw new StopException("The user has pressed cancel thereby stopping the registration");
                }
            }

            if (!remarkTxtf.getText().trim().equals(mainDoctorVisit.getRemark().trim())) {
                String[] opcionet = {"Yes", "No", "Cancel"};
                int response = JOptionPane.showOptionDialog(null,
                        "You have changed the remark of the visit press yes to save it this way or no to return it to the past value.(Cancel to stop the registration)", "Warning",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, opcionet, opcionet[0]);
                if (response == 0) {
                    mainDoctorVisit.setRemark(remarkTxtf.getText().trim());
                    doctorVisitIr.edit(mainDoctorVisit);
                } else if (response == 1) {
                    remarkTxtf.setText(mainDoctorVisit.getRemark());
                } else {
                    throw new StopException("The user has pressed cancel thereby stopping the registration");
                }
            }
        } else if (currentUser.getRole().equals("LaboratorTechnician")) {
            if(mainAnalysisVisit==null)
                throw new AppException("You must select a AnalysisVisit from the table.");
            
            if (mainAnalysisVisit.getPatientID() == null) {
                throw new AppException("You must assign a patient to the visit before saving a diagnostic for it.");
            }

            if (visitTbl.getSelectedRow() == -1) {
                throw new AppException("Choose the visit for which you want to add the analysis.");
            }

            if (analysisTxtf.getText().trim().isEmpty()) {
                analysisTxtf.requestFocus();
                throw new AppException("You have to write down the analysis of the patient.");
            }
            
            if (resultTxtf.getText().trim().isEmpty()) {
                resultTxtf.requestFocus();
                throw new AppException("You have to write down the results of the patient.");
            }
            
            if (priceTxtf.getText().trim().isEmpty()) {
                priceTxtf.requestFocus();
                throw new AppException("You have to set a price for the diagnosis.");
            }

            try {
                Double.parseDouble(priceTxtf.getText().trim());
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
                throw new AppException("Write the correct price.");
            }

            if (!allergiesTxtf.getText().trim().equals(mainAnalysisVisit.getPatientID().getAllergies())) {
                Patient patient = mainAnalysisVisit.getPatientID();
                String[] opcionet = {"Yes", "No", "Cancel"};
                int response = JOptionPane.showOptionDialog(null,
                        "You have changed the allergies of the patient press yes to save them this way or no to return them to the past value.(Cancel to stop the registration)", "Warning",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, opcionet, opcionet[0]);
                if (response == 0) {
                    patient.setAllergies(allergiesTxtf.getText().trim());
                    patientIr.edit(patient);
                } else if (response == 1) {
                    allergiesTxtf.setText(mainAnalysisVisit.getPatientID().getAllergies());
                } else {
                    throw new StopException("The user has pressed cancel thereby stopping the registration.");
                }
            }

            if (!remarkTxtf.getText().trim().equals(mainAnalysisVisit.getRemark().trim())) {
                String[] opcionet = {"Yes", "No", "Cancel"};
                int response = JOptionPane.showOptionDialog(null,
                        "You have changed the remark of the visit press yes to save it this way or no to return it to the past value.(Cancel to stop the registration)", "Warning",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, opcionet, opcionet[0]);
                if (response == 0) {
                    mainAnalysisVisit.setRemark(remarkTxtf.getText().trim());
                    analysisVisitIr.edit(mainAnalysisVisit);
                } else if (response == 1) {
                    remarkTxtf.setText(mainAnalysisVisit.getRemark());
                } else {
                    throw new StopException("The user has pressed cancel thereby stopping the registration");
                }
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
        if(currentUser.getRole().equals("Doctor"))
            remarkTxtf.setText(mainDoctorVisit.getRemark());
        else
            remarkTxtf.setText(mainAnalysisVisit.getRemark());
        remarkLbl.setForeground(Color.BLUE);
    }
    
}
