
package gui.view;

import ejb.DoctorVisit;
import ejb.DiagnosisForVisit;
import ejb.Diagnosis;
import ejb.AnalysisVisit;
import ejb.AnalysisForVisit;
import ejb.Analysis;
import ejb.Staff;

import bl.DoctorVisitInterface;
import bl.DoctorVisitRepository;
import bl.DiagnosisForVisitInterface;
import bl.DiagnosisForVisitRepository;
import bl.DiagnosisInterface;
import bl.DiagnosisRepository;
import bl.AnalysisForVisitInterface;
import bl.AnalysisForVisitRepository;
import bl.AnalysisInterface;
import bl.AnalysisRepository;
import bl.AnalysisVisitInterface;
import bl.AnalysisVisitRepository;
import bl.PatientInterface;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

import gui.model.AnalysisVisitTableModel;
import gui.model.DiagnosisForVisitTableModel;
import gui.model.DoctorVisitTableModel;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableCellRenderer;



public class PrintVisits extends javax.swing.JInternalFrame {
    
    EntityManager entityManager;
    Staff currentUser;
    
    
    DoctorVisitInterface doctorVisitIr;
    DiagnosisInterface diagnosisIr;
    DiagnosisForVisitInterface diagnosisForVisitIr;
    AnalysisVisitInterface analysisVisitIr;
    AnalysisInterface analysisIr;
    AnalysisForVisitInterface analysisForVisitIr;
    DoctorVisitTableModel doctorVisitTM;
    AnalysisVisitTableModel analysisVisitTM;
    DiagnosisForVisitTableModel diagnosisForVisitTM;
    DoctorVisit mainDoctorVisit;
    AnalysisVisit mainAnalysisVisit;
    MainFrame mainFrame;
    
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
    
    public PrintVisits(EntityManager entityManager,Staff currentUser,MainFrame mainFrame) {
        this.entityManager=entityManager;
        this.currentUser=currentUser;
        this.mainFrame=mainFrame;
        this.setLocation(220, 10);
        initComponents();
        initInterfaces();
        initTableModels();
        doctorVisitClickMoveKey();
        analysisVisitClickMoveKey();
        doctorVisitTableLoad();
        analysisVisitTableLoad();
        searchTxtfListeners();
        visitsCheckBoxListeners();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        doctorVisitsScrollPane = new javax.swing.JScrollPane();
        doctorVisitsTbl = new javax.swing.JTable();
        seeAllDoctorVisitsBtn = new javax.swing.JCheckBox();
        doctorVisitPrintBtn = new javax.swing.JButton();
        doctorVisitsSearchTxtf = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        background1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        analysisVisitsTbl = new javax.swing.JTable();
        seeAllAnalysisVisitsBtn = new javax.swing.JCheckBox();
        analysisVisitsSearchTxtf = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        analysisPrintBtn = new javax.swing.JButton();
        background2 = new javax.swing.JLabel();

        setClosable(true);
        setResizable(true);
        setTitle("Print Visits");
        setPreferredSize(new java.awt.Dimension(1090, 650));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        doctorVisitsTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        doctorVisitsScrollPane.setViewportView(doctorVisitsTbl);

        jPanel3.add(doctorVisitsScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 1060, 510));

        seeAllDoctorVisitsBtn.setForeground(new java.awt.Color(255, 255, 255));
        seeAllDoctorVisitsBtn.setText("See All");
        jPanel3.add(seeAllDoctorVisitsBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 30, -1, -1));

        doctorVisitPrintBtn.setBackground(new java.awt.Color(0, 153, 102));
        doctorVisitPrintBtn.setForeground(new java.awt.Color(204, 255, 204));
        doctorVisitPrintBtn.setText("Print Doctor Visit");
        doctorVisitPrintBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doctorVisitPrintBtnActionPerformed(evt);
            }
        });
        jPanel3.add(doctorVisitPrintBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 20, 200, 35));

        doctorVisitsSearchTxtf.setBackground(new java.awt.Color(204, 255, 204));
        jPanel3.add(doctorVisitsSearchTxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 350, 30));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Search :");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 140, 30));

        background1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel3.add(background1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-6, -2, 1080, 590));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1078, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(116, 116, 116))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Doctor Visits", jPanel1);

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));

        jPanel4.setOpaque(false);
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        analysisVisitsTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(analysisVisitsTbl);

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 1060, 510));

        seeAllAnalysisVisitsBtn.setForeground(new java.awt.Color(255, 255, 255));
        seeAllAnalysisVisitsBtn.setText("See All");
        seeAllAnalysisVisitsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seeAllAnalysisVisitsBtnActionPerformed(evt);
            }
        });
        jPanel4.add(seeAllAnalysisVisitsBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 30, -1, -1));

        analysisVisitsSearchTxtf.setBackground(new java.awt.Color(204, 255, 204));
        jPanel4.add(analysisVisitsSearchTxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 350, 30));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("Search :");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 140, 30));

        analysisPrintBtn.setBackground(new java.awt.Color(0, 153, 102));
        analysisPrintBtn.setForeground(new java.awt.Color(204, 255, 204));
        analysisPrintBtn.setText("Print Analysis Visit");
        analysisPrintBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                analysisPrintBtnActionPerformed(evt);
            }
        });
        jPanel4.add(analysisPrintBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 20, 200, 35));

        background2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel4.add(background2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 580));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Analysis Visits", jPanel2);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1083, 620));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void seeAllAnalysisVisitsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seeAllAnalysisVisitsBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_seeAllAnalysisVisitsBtnActionPerformed

    private void analysisPrintBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_analysisPrintBtnActionPerformed
        int selectedRow=analysisVisitsTbl.getSelectedRow();
        
        setInfoForAnalysisVisitPrint();
        
        
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
        
        if(selectedRow>-1){
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
    }//GEN-LAST:event_analysisPrintBtnActionPerformed

    private void doctorVisitPrintBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doctorVisitPrintBtnActionPerformed
        int selectedRow=doctorVisitsTbl.getSelectedRow();
        
        setInfoForDoctorVisitPrint();
        
        
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
        
        if(selectedRow>-1){
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
        
    }//GEN-LAST:event_doctorVisitPrintBtnActionPerformed
    
    public void doctorVisitTableLoad(){
            List<DoctorVisit> visitList;
            if (seeAllDoctorVisitsBtn.isSelected()) {
                visitList = doctorVisitIr.findAll();
            } else {
                visitList = doctorVisitIr.findPresentAndFuture();
            }

            doctorVisitsTbl.setModel(doctorVisitTM);
            doctorVisitTM.add(visitList);
            doctorVisitTM.fireTableDataChanged();

    }
    
    public void analysisVisitTableLoad(){
            List<AnalysisVisit> analysisVisit;
            if(seeAllAnalysisVisitsBtn.isSelected()){
                analysisVisit= analysisVisitIr.findAll();
            }else{
                analysisVisit= analysisVisitIr.findPresentAndFuture();
            }
            analysisVisitsTbl.setModel(analysisVisitTM);
            analysisVisitTM.add(analysisVisit);
            analysisVisitTM.fireTableDataChanged();
        
    }
    
    private void doctorVisitsTableLoad(){
        List<DoctorVisit>doctorVisits=doctorVisitIr.findAll();
        doctorVisitTM.add(doctorVisits);
        doctorVisitsTbl.setModel(doctorVisitTM);
        doctorVisitTM.fireTableDataChanged();
    }
    
    private void analysisVisitsTableLoad(){
        List<AnalysisVisit>analysisVisits=analysisVisitIr.findAll();
        analysisVisitTM.add(analysisVisits);
        analysisVisitsTbl.setModel(analysisVisitTM);
        analysisVisitTM.fireTableDataChanged();
    }
    
    public void doctorVisitTableFindByAll(String text) {
        List<DoctorVisit> list = doctorVisitIr.findByAll(text);
        doctorVisitTM.add(list);
        doctorVisitsTbl.setModel(doctorVisitTM);
        doctorVisitTM.fireTableDataChanged();
    }
    
    public void analysisVisitTableFindByAll(String text) {
        List<AnalysisVisit> list = analysisVisitIr.findByAll(text);
        analysisVisitTM.add(list);
        analysisVisitsTbl.setModel(analysisVisitTM);
        analysisVisitTM.fireTableDataChanged();
    }
    
    private void initInterfaces() {
        doctorVisitIr=new DoctorVisitRepository(entityManager);
        diagnosisIr=new DiagnosisRepository(entityManager);
        diagnosisForVisitIr= new DiagnosisForVisitRepository(entityManager);
        analysisVisitIr=new AnalysisVisitRepository(entityManager);
        analysisIr=new AnalysisRepository(entityManager);
        analysisForVisitIr=new AnalysisForVisitRepository(entityManager);
    }
    
    private void initTableModels(){
        String[] doctorVisitColumns={"PatientID","Visit Type","Date","Time","SumPrice","Finished"};
        doctorVisitTM=new DoctorVisitTableModel(doctorVisitColumns);
        String[] diagnosisForVisitColumns={"Complaint","Examination","Therapy","CurrentPrice"};
        diagnosisForVisitTM=new DiagnosisForVisitTableModel(diagnosisForVisitColumns);
        String[] analysisVisitColumns={"PatientID","Visit Type","Date","Time","SumPrice","Finished"};
        analysisVisitTM= new AnalysisVisitTableModel(analysisVisitColumns);
    }
    
    private void setInfoForDoctorVisitPrint(){
        
        DoctorVisit selectedDoctorVisit=new DoctorVisit();
        int selectedRow=doctorVisitsTbl.getSelectedRow();
        if(selectedRow>-1){
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
        
        List<Diagnosis>diagnosis=diagnosisIr.findByDoctorVisit(doctorVisitId);
        String allComplaints="";
        String allAnamnesis="";
        String allExaminations="";
        String allTherapys="";
        String allRecommendations="";
        if(diagnosis.size()==1){
                
            allComplaints=diagnosis.get(0).getComplaint()+" . \n";
            allAnamnesis=diagnosis.get(0).getAnamnesis()+" . \n";
            allExaminations=diagnosis.get(0).getExamination()+" . \n";
            allTherapys=diagnosis.get(0).getTherapy()+" . \n";
            allRecommendations+=diagnosis.get(0).getRecommendation()+" . \n";
        
        }else{
            for(int i=0;i<diagnosis.size();i++){
                int nr=i+1;
                allComplaints+="    Ankesa "+ nr +" : "+diagnosis.get(i).getComplaint()+" . \n";
                allAnamnesis+="    Anamneza "+ nr +" : "+diagnosis.get(i).getAnamnesis()+" . \n";
                allExaminations+="    Examinimi "+ nr +" : "+diagnosis.get(i).getExamination()+" . \n";
                allTherapys+="    Therapia "+ nr +" : "+diagnosis.get(i).getTherapy()+" . \n";
                allRecommendations+="    Recommendimi "+ nr +" : "+diagnosis.get(i).getRecommendation()+" . \n";
            }
        }
        
        
        complaintForVisit=allComplaints;
        anamnesisForVisit=allAnamnesis;
        examinationForVisit=allExaminations;
        therapyForVisit=allTherapys;
        recommendationForVisit=allRecommendations;
    }
    
    private void setInfoForAnalysisVisitPrint(){
        
        AnalysisVisit selectedAnalysisVisit=new AnalysisVisit();
        int selectedRow=analysisVisitsTbl.getSelectedRow();
        if(analysisVisitsTbl.getModel()==analysisVisitTM&&selectedRow>-1){
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
        
        priceForAnalysis=selectedAnalysisVisit.getSumPrice()+" \u20AC";
        dateForAnalysis=sdf.format(selectedAnalysisVisit.getTimeStamp());
        
        List<Analysis> analysis=analysisIr.findByAnalysisVisit(analysisVisitId);
        String allAnalysis="";
        String allResults="";
        for(int i=0;i<analysis.size();i++){
            allAnalysis+="  Analysis "+ (i+1)+" : \n"+analysis.get(i).getAnalysis();
            allResults+="   Results "+ (i+1)+" : \n"+analysis.get(i).getResults();
        }
        
        analysisForAnalysis=allAnalysis;
        resultsForAnalysis=allResults;
        
    }
    
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
            Chunk c22=new Chunk("Çmimi : "+priceForVisit+" \u20AC ",FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
            
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
            pha2.add(space);
            pha2.add(c11);
            pha2.add(space);
            pha2.add(space);
            pha2.add(c12);
            pha2.add(space);
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
    
    
    private void doctorVisitClickMoveKey() {
        doctorVisitsTbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                    if (e.getClickCount() == 2) {
                        setInfoForDoctorVisitPrint();
                        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
                        int rowIndex=doctorVisitsTbl.getSelectedRow();
                        
                        
                        String [] doctorVisitFrameStrings=new String[7];
                        doctorVisitFrameStrings[0]=patientNameForVisit+" "+patientSurnameForVisit;
                        doctorVisitFrameStrings[1]=doctorNameForVisit+" "+doctorSurnameForVisit;
                        doctorVisitFrameStrings[2]=complaintForVisit;
                        doctorVisitFrameStrings[3]=examinationForVisit;
                        doctorVisitFrameStrings[4]=therapyForVisit;
                        doctorVisitFrameStrings[5]=dateForVisit;
                        doctorVisitFrameStrings[6]=priceForVisit;
                        DoctorVisitFrame doctorVisitFrame=new DoctorVisitFrame(doctorVisitFrameStrings);
                        doctorVisitFrame.setVisible(true);
                    }
                
            }
        });

    }
    
    private void analysisVisitClickMoveKey() {
        analysisVisitsTbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                    if (e.getClickCount() == 2) {
                        setInfoForAnalysisVisitPrint();
                        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
                        int rowIndex=analysisVisitsTbl.getSelectedRow();
                        
                        
                        String [] analysisVisitFrameStrings=new String[6];
                        analysisVisitFrameStrings[0]=patientNameForAnalysis+" "+patientSurnameForAnalysis;
                        analysisVisitFrameStrings[1]=doctorNameForAnalysis+" "+doctorSurnameForAnalysis;
                        analysisVisitFrameStrings[2]=analysisForAnalysis;
                        analysisVisitFrameStrings[3]=resultsForAnalysis;
                        analysisVisitFrameStrings[4]=dateForAnalysis;
                        analysisVisitFrameStrings[5]=priceForAnalysis;
                        AnalysisFrame analysisVisitFrame=new AnalysisFrame(analysisVisitFrameStrings);
                        analysisVisitFrame.setVisible(true);
                    }
                
            }
        });

    }
    
    private void searchTxtfListeners(){
        doctorVisitsSearchTxtf.getDocument().addDocumentListener(new DocumentListener() {
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
                doctorVisitTableFindByAll(doctorVisitsSearchTxtf.getText());
            }
        });
        
        analysisVisitsSearchTxtf.getDocument().addDocumentListener(new DocumentListener() {
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
                analysisVisitTableFindByAll(analysisVisitsSearchTxtf.getText());
            }
        });
    }
    
    private void visitsCheckBoxListeners() {
        seeAllDoctorVisitsBtn.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ie){
                doctorVisitTableLoad();
            }
        });
        
        seeAllAnalysisVisitsBtn.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ie){
                analysisVisitTableLoad();
            }
        });
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton analysisPrintBtn;
    private javax.swing.JTextField analysisVisitsSearchTxtf;
    private javax.swing.JTable analysisVisitsTbl;
    private javax.swing.JLabel background1;
    private javax.swing.JLabel background2;
    private javax.swing.JButton doctorVisitPrintBtn;
    private javax.swing.JScrollPane doctorVisitsScrollPane;
    private javax.swing.JTextField doctorVisitsSearchTxtf;
    private javax.swing.JTable doctorVisitsTbl;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JCheckBox seeAllAnalysisVisitsBtn;
    private javax.swing.JCheckBox seeAllDoctorVisitsBtn;
    // End of variables declaration//GEN-END:variables
}
