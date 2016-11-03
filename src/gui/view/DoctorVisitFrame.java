
package gui.view;


public class DoctorVisitFrame extends javax.swing.JFrame {
    
    String patient;
    String doctor;
    String complaint;
    String examination;
    String therapy;
    String date;
    String price;
    
    public DoctorVisitFrame(String []visitInfo) {
        initComponents();
        patient=visitInfo[0];
        doctor=visitInfo[1];
        complaint=visitInfo[2];
        examination=visitInfo[3];
        therapy=visitInfo[4];
        date=visitInfo[5];
        price=visitInfo[6];
        setFieldText();
        
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        patientTxtf = new javax.swing.JTextField();
        doctorTxtf = new javax.swing.JTextField();
        dateTxtf = new javax.swing.JTextField();
        priceTxtf = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        examinationTxtf = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        therapyTxtf = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        complaintTxtf = new javax.swing.JTextArea();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Doctor Visit");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setLayout(null);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Patient :");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(20, 20, 120, 30);

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("Doctor :");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(20, 60, 120, 30);

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("Complaints :");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(20, 100, 120, 30);

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("Examination :");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(20, 200, 120, 30);

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel5.setText("Therapy :");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(20, 300, 120, 30);

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("Date :");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(20, 410, 120, 30);

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel7.setText("Price :");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(20, 460, 120, 30);

        patientTxtf.setEditable(false);
        patientTxtf.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.add(patientTxtf);
        patientTxtf.setBounds(150, 20, 200, 30);

        doctorTxtf.setEditable(false);
        doctorTxtf.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.add(doctorTxtf);
        doctorTxtf.setBounds(150, 60, 200, 30);

        dateTxtf.setEditable(false);
        dateTxtf.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.add(dateTxtf);
        dateTxtf.setBounds(150, 410, 200, 30);

        priceTxtf.setEditable(false);
        priceTxtf.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.add(priceTxtf);
        priceTxtf.setBounds(150, 460, 200, 30);

        examinationTxtf.setEditable(false);
        examinationTxtf.setBackground(new java.awt.Color(204, 255, 204));
        examinationTxtf.setColumns(20);
        examinationTxtf.setLineWrap(true);
        examinationTxtf.setRows(5);
        examinationTxtf.setWrapStyleWord(true);
        jScrollPane1.setViewportView(examinationTxtf);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(150, 210, 360, 80);

        therapyTxtf.setEditable(false);
        therapyTxtf.setBackground(new java.awt.Color(204, 255, 204));
        therapyTxtf.setColumns(20);
        therapyTxtf.setLineWrap(true);
        therapyTxtf.setRows(5);
        therapyTxtf.setWrapStyleWord(true);
        jScrollPane2.setViewportView(therapyTxtf);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(150, 310, 360, 80);

        complaintTxtf.setEditable(false);
        complaintTxtf.setBackground(new java.awt.Color(204, 255, 204));
        complaintTxtf.setColumns(20);
        complaintTxtf.setLineWrap(true);
        complaintTxtf.setRows(5);
        complaintTxtf.setWrapStyleWord(true);
        jScrollPane3.setViewportView(complaintTxtf);

        jPanel1.add(jScrollPane3);
        jScrollPane3.setBounds(150, 110, 360, 80);
        jPanel1.add(background);
        background.setBounds(0, 0, 520, 510);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, 510));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void setFieldText(){
        patientTxtf.setText(patient);
        doctorTxtf.setText(doctor);
        complaintTxtf.setText(complaint);
        examinationTxtf.setText(examination);
        therapyTxtf.setText(therapy);
        dateTxtf.setText(date);
        priceTxtf.setText(price);
    }
  
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JTextArea complaintTxtf;
    private javax.swing.JTextField dateTxtf;
    private javax.swing.JTextField doctorTxtf;
    private javax.swing.JTextArea examinationTxtf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField patientTxtf;
    private javax.swing.JTextField priceTxtf;
    private javax.swing.JTextArea therapyTxtf;
    // End of variables declaration//GEN-END:variables
}
