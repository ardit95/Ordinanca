
package gui.view;


public class DoctorVisitFrame extends javax.swing.JFrame {
    
    String patient;
    String doctor;
    
    public DoctorVisitFrame(String patient,String doctor) {
        initComponents();
        this.patient=patient;
        this.doctor=doctor;
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
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(null);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Patient :");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(20, 20, 120, 30);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("Doctor :");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(20, 60, 120, 30);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("Complaints :");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(20, 100, 120, 30);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("Examination :");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(20, 200, 120, 30);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel5.setText("Therapy :");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(20, 300, 120, 30);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("Date :");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(20, 410, 120, 30);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel7.setText("Price :");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(20, 460, 120, 30);
        jPanel1.add(patientTxtf);
        patientTxtf.setBounds(150, 20, 200, 30);
        jPanel1.add(doctorTxtf);
        doctorTxtf.setBounds(150, 60, 200, 30);
        jPanel1.add(jTextField3);
        jTextField3.setBounds(150, 410, 200, 30);
        jPanel1.add(jTextField4);
        jTextField4.setBounds(150, 460, 200, 30);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(150, 210, 360, 80);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(150, 310, 360, 80);

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane3.setViewportView(jTextArea3);

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
    }
  
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JTextField doctorTxtf;
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
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField patientTxtf;
    // End of variables declaration//GEN-END:variables
}
