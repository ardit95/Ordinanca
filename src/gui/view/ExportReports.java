package gui.view;

import ejbView.ReportDoctor;
import ejbView.ReportMonthDoctorVisit;
import ejbView.ReportMonthAnalysisVisit;

import bl.ReportDoctorInterface;
import bl.ReportDoctorRepository;
import bl.ReportMonthDoctorVisitInterface;
import bl.ReportMonthDoctorVisitRepository;
import bl.ReportMonthAnalysisVisitInterface;
import bl.ReportMonthAnalysisVisitRepository;

import gui.model.ReportDoctorTableModel;
import gui.model.ReportMonthDoctorVisitTableModel;
import gui.model.ReportMonthAnalysisVisitTableModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.TreeMap;
import javax.persistence.EntityManager;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.AreaReference;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFTable;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportReports extends javax.swing.JInternalFrame {
    
    EntityManager entityManager;
    ReportMonthDoctorVisitInterface reportMonthDoctorVisitIr;
    ReportMonthAnalysisVisitInterface reportMonthAnalysisVisitIr;
    ReportDoctorInterface reportDoctorIr;
    
    ReportMonthDoctorVisitTableModel reportMonthDoctorVisitTM;
    ReportMonthAnalysisVisitTableModel reportMonthAnalysisVisitTM;
    ReportDoctorTableModel reportDoctorTM;
    
    public ExportReports(EntityManager entityManager) {
        initComponents();
        this.setLocation(220, 10);
        this.entityManager=entityManager;
        reportMonthDoctorVisitIr=new ReportMonthDoctorVisitRepository(entityManager);
        reportMonthAnalysisVisitIr=new ReportMonthAnalysisVisitRepository(entityManager);
        reportDoctorIr=new ReportDoctorRepository(entityManager);
        String[] columnNamesReportMonthDoctorVisitTableModel = {"Year", "Month", "NumberOFVisits", "NumberOfPatients"};
        reportMonthDoctorVisitTM = new ReportMonthDoctorVisitTableModel(columnNamesReportMonthDoctorVisitTableModel);
        String[] columnNamesReportMonthAnalysisVisitTableModel = {"Year", "Month", "NumberOFAnalysis", "NumberOfPatients"};
        reportMonthAnalysisVisitTM = new ReportMonthAnalysisVisitTableModel(columnNamesReportMonthAnalysisVisitTableModel);
        String[] columnNamesReportDoctorTableModel = {"Year", "Month", "DoctorName", "DoctorSurname","NumberOfVisits"};
        reportDoctorTM = new ReportDoctorTableModel(columnNamesReportDoctorTableModel);
    }
    
    private void reportMonthDoctorVisitTableLoad(){
        List<ReportMonthDoctorVisit> list=reportMonthDoctorVisitIr.findAll();
        reportMonthDoctorVisitTM.add(list);
        reportTbl.setModel(reportMonthDoctorVisitTM);
        reportMonthDoctorVisitTM.fireTableDataChanged();
    }
    
    private void reportMonthAnalysisVisitTableLoad(){
        List<ReportMonthAnalysisVisit> list=reportMonthAnalysisVisitIr.findAll();
        reportMonthAnalysisVisitTM.add(list);
        reportTbl.setModel(reportMonthAnalysisVisitTM);
        reportMonthAnalysisVisitTM.fireTableDataChanged();
    }
    
    private void reportDoctorTableLoad(){
        List<ReportDoctor> list=reportDoctorIr.findAll();
        reportDoctorTM.add(list);
        reportTbl.setModel(reportDoctorTM);
        reportDoctorTM.fireTableDataChanged();
    }
    
    private void exportExcel(String directory) throws IOException{
        XSSFWorkbook wb=new XSSFWorkbook();
        XSSFSheet ws=wb.createSheet();
        TableModel tm=reportTbl.getModel();
        int rows=tm.getRowCount();
        int columns=tm.getColumnCount();
        
        
        TreeMap<String ,Object[]> data=new TreeMap<>();
        
        String[] strs=new String [columns];
        
        
        
        
        
        for(int i=0;i<columns;i++){
            strs[i]=tm.getColumnName(i);
        }
        
        data.put("-1", strs);
        
        Object[] o;
        for(int i=0;i<rows;i++){
            o=new Object[columns];
            for(int j=0;j<columns;j++){
                o[j]= tm.getValueAt(i, j);
            }
         data.put(Integer.toString(i),o);
         
        }
        
        
        XSSFRow row;
        int rowID=0;
        
        int [] keyat=new int[rows+1];
        
        for(int i=0;i<rows+1;i++){
            keyat[i]=i-1;
        }
        
        
        
        for(int key: keyat){
            row=ws.createRow(rowID++);
            Object[] values=data.get(Integer.toString(key));
            int cellID=0;
            for(Object obj: values){
                Cell cell=row.createCell(cellID++);
                cell.setCellValue(obj.toString());
                
            }
        }
        
        for(int i=0;i<columns;i++){
            ws.autoSizeColumn(i);
        }
        
        
        XSSFCellStyle style =wb.createCellStyle();
        style.setFillForegroundColor(new XSSFColor(new java.awt.Color(53,100,57)));
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBottomBorderColor(IndexedColors.RED.getIndex());
        XSSFFont font = wb.createFont();
        font.setColor(IndexedColors.WHITE.getIndex());
        font.setBold(true);
        font.setFontHeight(12);
        style.setFont(font);
        
        XSSFCellStyle style2 =wb.createCellStyle();
        style2.setBorderBottom(HSSFCellStyle.BORDER_DOTTED);
        style2.setBorderTop(HSSFCellStyle.BORDER_DOTTED);
        style2.setBorderLeft(HSSFCellStyle.BORDER_NONE);
        style2.setBorderRight(HSSFCellStyle.BORDER_NONE);
        style2.setBottomBorderColor(IndexedColors.RED.getIndex());
        style2.setTopBorderColor(IndexedColors.RED.getIndex());
        for(int i=0;i<tm.getColumnCount();i++){
            ws.getRow(0).getCell(i).setCellStyle(style);
            
        }
        
        for(int i=0;i<tm.getColumnCount();i++){
            for(int j=0;j<tm.getRowCount();j++){
                if(j!=0){
                    ws.getRow(j).getCell(i).setCellStyle(style2);
                } 
            }     
        }
        String r=rows+"";
        String r2=getCol(columns);
        
        String range="A1:"+r2+r;
        
        ws.setAutoFilter(CellRangeAddress.valueOf(range));
        ws.setDisplayGridlines(false);
        
        
        /*if(tbl.getModel()==tvtm)
            exportTrainingView(tm,ws);
        else if(tbl.getModel()==pvtm)
            exportParticipantView(tm,ws);  
        else if(tbl.getModel()==rtevvtm){
            exportEvaluationView(tm,ws);
        }*/
        try{
            FileOutputStream fos;
            fos = new FileOutputStream(new File(directory+emertoFilin(reportTbl)+".xlsx"));
             
            wb.write(fos);
            fos.close();
            JOptionPane.showMessageDialog(null,"U ruajt me sukses");
        }catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"File është i hapur.Mbylleni file-in per ta ruajtur");
        }
        
    }
    
    
    
    public String getCol(int i){
        switch(i){
            case 0:
                return "A";
            case 1:
                return "A";
            case 2:
                return "B";
            case 3:
                return "C";
            case 4:
                return "D";
            case 5:
                return "E";
            case 6:
                return "F";
            case 7:
                return "G";
            case 8:
                return "H";
            case 9:
                return "I";
            case 10:
                return "J";
            case 11:
                return "K";
            case 12:
                return "L";
            case 13:
                return "M";
            case 14:
                return "N";
            case 15:
                return "O";
            case 16:
                return "P";
            case 17:
                return "Q";
            case 18:
                return "R";
            case 19:
                return "S";
            case 20:
                return "T";    
            case 21:
                return "U";
            case 22:
                return "V";
            case 23:
                return "W";
            case 24:
                return "X";
            case 25:
                return "Y";
            case 26:
                return "Z";
            default:
                return null;
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        reportTbl = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        tableTypeCombo = new javax.swing.JComboBox<>();
        generateBtn = new javax.swing.JButton();
        exportBtn = new javax.swing.JButton();
        background = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Export Reports");
        setMaximumSize(new java.awt.Dimension(1100, 654));
        setMinimumSize(new java.awt.Dimension(1100, 654));
        setNormalBounds(new java.awt.Rectangle(1100, 654, 654, 654));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(null);

        reportTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(reportTbl);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 90, 1060, 520);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Zgjidh llojin e tabelës  :");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(20, 20, 160, 30);
        jLabel1.getAccessibleContext().setAccessibleDescription("");

        tableTypeCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Raport Mujor i Vizitave", "Raport Mujor i Analizave", "Raport i Doktorave" }));
        jPanel1.add(tableTypeCombo);
        tableTypeCombo.setBounds(190, 20, 240, 30);

        generateBtn.setText("Gjenero Tabelën");
        generateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateBtnActionPerformed(evt);
            }
        });
        jPanel1.add(generateBtn);
        generateBtn.setBounds(450, 20, 160, 30);

        exportBtn.setText("Export to Excel");
        exportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportBtnActionPerformed(evt);
            }
        });
        jPanel1.add(exportBtn);
        exportBtn.setBounds(650, 10, 200, 50);
        jPanel1.add(background);
        background.setBounds(0, 0, 1080, 620);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1083, 622));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void generateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateBtnActionPerformed
        if(tableTypeCombo.getSelectedItem().toString().equals("Raport Mujor i Vizitave")){
               
            reportMonthDoctorVisitTableLoad();
            
        }
        else if(tableTypeCombo.getSelectedItem().toString().equals("Raport Mujor i Analizave")){
            
            reportMonthAnalysisVisitTableLoad();
            
        }
        else if(tableTypeCombo.getSelectedItem().toString().equals("Raport i Doktorave")){
            reportDoctorTableLoad();
        }
    }//GEN-LAST:event_generateBtnActionPerformed

    private void exportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportBtnActionPerformed
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
        if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            
            directory=chooser.getSelectedFile().toString()+"\\";
            
            try {
             if(reportTbl.getModel()==reportDoctorTM||reportTbl.getModel()==reportMonthDoctorVisitTM||reportTbl.getModel()==reportMonthAnalysisVisitTM){
                exportExcel(directory);
             }
             else{
                 JOptionPane.showMessageDialog(null,"Gjeneroni njeren nga tabelat!!");
             }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,"IOException");
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Duhet te zgjedhni lokacionin se ku deshironi te ruani file !");
        }
    }//GEN-LAST:event_exportBtnActionPerformed
    
    private String emertoFilin(JTable tbl) {
     String t="Raport";
     
                
                if(tbl.getModel()==reportMonthDoctorVisitTM){
                    t="Raporti Mujor Vizitat";
                }
                else if(tbl.getModel()==reportMonthAnalysisVisitTM){
                    t="Raporti Mujor Analizat";
                }
                else if(tbl.getModel()==reportDoctorTM){
                    t="Raporti i Doktorave";
                }
                
        return t;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JButton exportBtn;
    private javax.swing.JButton generateBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable reportTbl;
    private javax.swing.JComboBox<String> tableTypeCombo;
    // End of variables declaration//GEN-END:variables
}
