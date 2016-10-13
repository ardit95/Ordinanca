/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.view;

import ExceptionPackage.AppException;
import bl.EntMngClass;
import bl.LogsInterface;
import bl.LogsRepository;
import bl.StaffInterface;
import bl.StaffRepository;
import ejb.Staff;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import javax.persistence.NoResultException;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Login extends javax.swing.JFrame {
    StaffInterface staffIr;
    LogsInterface logsIr;
    
    public Login() throws AppException { 
        try { 
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"); 
        }catch (Exception ex) { 
            ex.printStackTrace(); 
        }
        setSize(452,605);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int jFramewidth=this.getSize().width;
        int jFrameheight=this.getSize().height;
        int locationx=(dim.width-jFramewidth)/2;
        int locationy=(dim.height-jFrameheight)/2;
        this.setLocation(locationx,locationy);
        initComponents();
        staffIr=new StaffRepository(new EntMngClass("Checker","12345","localhost").getEntityManager());
        usernameTxtf.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        loginBtn = new javax.swing.JButton();
        usernameTxtf = new javax.swing.JTextField();
        passwordTxtf = new javax.swing.JPasswordField();
        serverIpTxtf = new javax.swing.JTextField();
        backgroundPanel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        loginBtn.setText("Login");
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });
        jPanel1.add(loginBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 410, 110, 30));

        usernameTxtf.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        usernameTxtf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                usernameTxtfKeyPressed(evt);
            }
        });
        jPanel1.add(usernameTxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 235, 225, 30));

        passwordTxtf.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        passwordTxtf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordTxtfActionPerformed(evt);
            }
        });
        passwordTxtf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordTxtfKeyPressed(evt);
            }
        });
        jPanel1.add(passwordTxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 335, 225, 30));

        serverIpTxtf.setText("localhost");
        serverIpTxtf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serverIpTxtfActionPerformed(evt);
            }
        });
        serverIpTxtf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                serverIpTxtfKeyPressed(evt);
            }
        });
        jPanel1.add(serverIpTxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 90, -1));
        jPanel1.add(backgroundPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 452, 605));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void usernameTxtfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameTxtfKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            loginMethod();
        }
    }//GEN-LAST:event_usernameTxtfKeyPressed

    private void passwordTxtfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordTxtfKeyPressed

        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            loginMethod();
        }
    }//GEN-LAST:event_passwordTxtfKeyPressed

    private void serverIpTxtfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serverIpTxtfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_serverIpTxtfActionPerformed

    private void serverIpTxtfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_serverIpTxtfKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            loginMethod();
        }
    }//GEN-LAST:event_serverIpTxtfKeyPressed

    private void passwordTxtfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordTxtfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordTxtfActionPerformed

    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtnActionPerformed
        loginMethod();
    }//GEN-LAST:event_loginBtnActionPerformed

    public static void main(String args[]) {
            Login login=null;
        try{
            login=new Login();
            login.setVisible(true);
        } catch (AppException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,ex.getMessage());
            if(ex.getMessage().equals("Nuk mund të krijohet lidhja me server.Kontrollo kabllat ose kontakto administratën"))
                System.exit(0);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backgroundPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton loginBtn;
    private javax.swing.JPasswordField passwordTxtf;
    private javax.swing.JTextField serverIpTxtf;
    private javax.swing.JTextField usernameTxtf;
    // End of variables declaration//GEN-END:variables

    private void loginMethod() {
       String username=usernameTxtf.getText();
       String password=passwordTxtf.getText();       
       String serverip=serverIpTxtf.getText();
       Staff staff;
       try{
       
        EntMngClass emc=new EntMngClass(username.trim(),password.trim(),serverip.trim());
        staffIr=new StaffRepository(emc.getEntityManager());
        String salt = staffIr.findSalt(username);
        staff=staffIr.findByUsernamePassword(username,staffIr.kripto(salt+password));
        int numberOfLogins=staffIr.getNumberOfLogins(staff);
        /*
        logsIr=new LogsRepository(emc.getEntityManager());
        Date date = logsIr.findDate();
        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat sdf2 = new SimpleDateFormat("HH:mm");
        String today=sdf.format(date);
        String koha=sdf2.format(date);
        Logs logs=new Logs();
        String message=staff.getName()+" "+staff.getSurname()+" me username-in: "+staff.getUsername()+" Është kyqur në program në datën : "+today +" në ora "+koha;
        logs.setDate(date);
        logs.setTime(date);
        logs.setMessage(message);
        logs.setType("Login");
        logs.setUsername(staff);
        
        logsIr.create(logs);*/
        if(numberOfLogins==0){
            this.dispose();
            new PasswordChangeFrame(staff).setVisible(true);
        }else{
            MainFrame mainFrame = new MainFrame(staff,emc.getEntityManager());
            mainFrame.setVisible(true);
            dispose();
        }
        
       }catch (NoResultException nre){
           nre.printStackTrace();
           JOptionPane.showMessageDialog(null,"Gabimm ");
           usernameTxtf.setText("");
           passwordTxtf.setText("");
       }
       catch(AppException ae){
           ae.printStackTrace();
           JOptionPane.showMessageDialog(null, ae.getMessage());
       }
       
    }
    
    private  int close(){
        WindowEvent winClosing=new WindowEvent (this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosing);
        return 0;
    }
}
