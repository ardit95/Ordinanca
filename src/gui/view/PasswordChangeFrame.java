package gui.view;

import ExceptionPackage.AppException;
import bl.EntMngClass;
import bl.StaffInterface;
import bl.StaffRepository;
import ejb.Staff;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

public class PasswordChangeFrame extends javax.swing.JFrame {
    Staff staff;
    StaffInterface staffIr;
    public PasswordChangeFrame(Staff staff) throws AppException {
        initComponents();
        this.staff=staff;
        /*Duhet me u zavendsu ma vone gjithqysh jo me root me u bo qekjo query*/
        staffIr=new StaffRepository(new EntMngClass("root","12345","localhost").getEntityManager());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        exitBtn = new javax.swing.JButton();
        saveBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        usernameLbl = new javax.swing.JLabel();
        password1Txtf = new javax.swing.JPasswordField();
        password2Txtf = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Write new password");

        exitBtn.setText("exit");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });

        saveBtn.setText("save");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        jLabel2.setText("Re write new password");

        password1Txtf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                password1TxtfActionPerformed(evt);
            }
        });
        password1Txtf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                password1TxtfKeyPressed(evt);
            }
        });

        password2Txtf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                password2TxtfKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(exitBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                        .addComponent(saveBtn))
                    .addComponent(jLabel2)
                    .addComponent(usernameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(password1Txtf)
                    .addComponent(password2Txtf))
                .addGap(95, 95, 95))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(usernameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(password1Txtf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(password2Txtf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveBtn)
                    .addComponent(exitBtn))
                .addContainerGap(69, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed

    }//GEN-LAST:event_exitBtnActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        changePasswordMethod();
    }//GEN-LAST:event_saveBtnActionPerformed

    private void password1TxtfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_password1TxtfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_password1TxtfActionPerformed

    private void password1TxtfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_password1TxtfKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
        changePasswordMethod();
    }//GEN-LAST:event_password1TxtfKeyPressed

    private void password2TxtfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_password2TxtfKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
        changePasswordMethod();
    }//GEN-LAST:event_password2TxtfKeyPressed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exitBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField password1Txtf;
    private javax.swing.JPasswordField password2Txtf;
    private javax.swing.JButton saveBtn;
    private javax.swing.JLabel usernameLbl;
    // End of variables declaration//GEN-END:variables
    private void changePasswordMethod(){
    try{
            if(!password1Txtf.getText().equals(password2Txtf.getText()))
                throw new AppException("Fjalëkalimi i parë dallon nga fjalëkalimi i dytë");
            byte[] password = staffIr.kripto(staff.getSalt()+password1Txtf.getText().trim());
            staff.setPassword(password);
            staffIr.changeLoginPassword(staff,password1Txtf.getText());
            staff.setNumberOfLogins(1);
            staffIr.edit(staff);
            this.dispose();
            new Login().setVisible(true);
        }catch(AppException ae){
            ae.printStackTrace();
            JOptionPane.showMessageDialog(this,ae.getMessage());
        }
    }
}
