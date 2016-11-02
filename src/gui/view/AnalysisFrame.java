
package gui.view;


public class AnalysisFrame extends javax.swing.JFrame {

    String patient;
    String laboratorTechnician;
    String analysis;
    String results;
    String date;
    String price;
    public AnalysisFrame(String []analysisInfo) {
        initComponents();
        patient=analysisInfo[0];
        laboratorTechnician=analysisInfo[1];
        analysis=analysisInfo[2];
        results=analysisInfo[3];
        date=analysisInfo[4];
        price=analysisInfo[5];
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
        patientTxtf = new javax.swing.JTextField();
        laboratorTechnicianTxtf = new javax.swing.JTextField();
        analysisTxtf = new javax.swing.JTextField();
        dateTxtf = new javax.swing.JTextField();
        priceTxtf = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultsTxtf = new javax.swing.JTextArea();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Analysis ");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setLayout(null);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Pacienti :");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(20, 20, 120, 30);

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("Laboranti :");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(20, 60, 120, 30);

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("Analiza :");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(20, 100, 120, 30);

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("Rezultatet :");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(20, 140, 120, 30);

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel5.setText("Data :");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(20, 800, 120, 30);

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("Çmimi :");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(20, 840, 120, 30);

        patientTxtf.setEditable(false);
        jPanel1.add(patientTxtf);
        patientTxtf.setBounds(150, 20, 200, 30);

        laboratorTechnicianTxtf.setEditable(false);
        jPanel1.add(laboratorTechnicianTxtf);
        laboratorTechnicianTxtf.setBounds(150, 60, 200, 30);

        analysisTxtf.setEditable(false);
        jPanel1.add(analysisTxtf);
        analysisTxtf.setBounds(150, 100, 400, 30);

        dateTxtf.setEditable(false);
        jPanel1.add(dateTxtf);
        dateTxtf.setBounds(150, 800, 200, 30);

        priceTxtf.setEditable(false);
        priceTxtf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceTxtfActionPerformed(evt);
            }
        });
        jPanel1.add(priceTxtf);
        priceTxtf.setBounds(150, 840, 200, 30);

        resultsTxtf.setEditable(false);
        resultsTxtf.setColumns(20);
        resultsTxtf.setRows(5);
        jScrollPane1.setViewportView(resultsTxtf);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(150, 140, 400, 650);
        jPanel1.add(background);
        background.setBounds(0, 0, 580, 900);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 580, 900));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void setFieldText(){
        patientTxtf.setText(patient);
        laboratorTechnicianTxtf.setText(laboratorTechnician);
        analysisTxtf.setText(analysis);
        resultsTxtf.setText(results);
        dateTxtf.setText(date);
        priceTxtf.setText(price);
    }
    
    private void priceTxtfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceTxtfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_priceTxtfActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField analysisTxtf;
    private javax.swing.JLabel background;
    private javax.swing.JTextField dateTxtf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField laboratorTechnicianTxtf;
    private javax.swing.JTextField patientTxtf;
    private javax.swing.JTextField priceTxtf;
    private javax.swing.JTextArea resultsTxtf;
    // End of variables declaration//GEN-END:variables
}
