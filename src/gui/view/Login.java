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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        setSize(452, 605);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int jFramewidth = this.getSize().width;
        int jFrameheight = this.getSize().height;
        int locationx = (dim.width - jFramewidth) / 2;
        int locationy = (dim.height - jFrameheight) / 2;
        this.setLocation(locationx, locationy);
        initComponents();
        staffIr = new StaffRepository(new EntMngClass("Checker", "12345", "10.10.20.22").getEntityManager());
        usernameTxtf.requestFocus();
        addFocuseListeners();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        loginBtn = new javax.swing.JButton();
        usernameTxtf = new javax.swing.JTextField();
        passwordTxtf = new javax.swing.JPasswordField();
        serverIpTxtf = new javax.swing.JTextField();
        usernameLbl = new javax.swing.JLabel();
        passwordLbl = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        background2 = new javax.swing.JLabel();
        backgroundPanel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        loginBtn.setBackground(new java.awt.Color(0, 153, 102));
        loginBtn.setFont(new java.awt.Font("Arial Narrow", 0, 24)); // NOI18N
        loginBtn.setForeground(new java.awt.Color(204, 255, 204));
        loginBtn.setText("Login");
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });
        jPanel1.add(loginBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 440, 270, 50));

        usernameTxtf.setBackground(new java.awt.Color(234, 255, 234));
        usernameTxtf.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        usernameTxtf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                usernameTxtfKeyPressed(evt);
            }
        });
        jPanel1.add(usernameTxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 270, 225, 40));

        passwordTxtf.setBackground(new java.awt.Color(234, 255, 234));
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
        jPanel1.add(passwordTxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 370, 225, 40));

        serverIpTxtf.setEditable(false);
        serverIpTxtf.setBackground(new java.awt.Color(234, 255, 234));
        serverIpTxtf.setText("10.10.20.22");
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

        usernameLbl.setBackground(new java.awt.Color(255, 255, 255));
        usernameLbl.setForeground(new java.awt.Color(0, 204, 102));
        usernameLbl.setText("Username:");
        jPanel1.add(usernameLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, -1, -1));

        passwordLbl.setBackground(new java.awt.Color(255, 255, 255));
        passwordLbl.setForeground(new java.awt.Color(0, 204, 102));
        passwordLbl.setText("Password:");
        jPanel1.add(passwordLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 350, -1, -1));

        jLabel1.setBackground(new java.awt.Color(90, 101, 89));
        jLabel1.setOpaque(true);
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 340, 270, 80));

        jLabel2.setBackground(new java.awt.Color(90, 101, 89));
        jLabel2.setOpaque(true);
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 240, 270, 80));

        background2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/physician-icon-png-10.png"))); // NOI18N
        jPanel1.add(background2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 190, 200));
        jPanel1.add(backgroundPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 452, 480));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void usernameTxtfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameTxtfKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            loginMethod();
        }
    }//GEN-LAST:event_usernameTxtfKeyPressed
    
    
    private void passwordTxtfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordTxtfKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            loginMethod();
        }
    }//GEN-LAST:event_passwordTxtfKeyPressed

    private void serverIpTxtfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serverIpTxtfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_serverIpTxtfActionPerformed

    private void serverIpTxtfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_serverIpTxtfKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            loginMethod();
        }
    }//GEN-LAST:event_serverIpTxtfKeyPressed

    private void passwordTxtfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordTxtfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordTxtfActionPerformed

    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtnActionPerformed
        loginMethod();
    }//GEN-LAST:event_loginBtnActionPerformed
    
    private void addFocuseListeners(){
            usernameTxtf.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            }

            public void focusLost(FocusEvent e) {
                // nothing
            }
        });
    }
    
    public static void main(String args[]) {
        Login login = null;
        try {
            login = new Login();
            if (login.staffIr.CheckAdminExists() == 0) {
                new checkSystemAdmin().setVisible(true);
            } else {
                login.setVisible(true);
            }
        } catch (AppException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background2;
    private javax.swing.JLabel backgroundPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton loginBtn;
    private javax.swing.JLabel passwordLbl;
    private javax.swing.JPasswordField passwordTxtf;
    private javax.swing.JTextField serverIpTxtf;
    private javax.swing.JLabel usernameLbl;
    private javax.swing.JTextField usernameTxtf;
    // End of variables declaration//GEN-END:variables

    private void loginMethod() {
        String username = usernameTxtf.getText();
        String password = passwordTxtf.getText();
        String serverip = serverIpTxtf.getText();
        Staff currentUser;
        try {

            EntMngClass emc = new EntMngClass(username.trim(), password.trim(), serverip.trim());
            staffIr = new StaffRepository(emc.getEntityManager());
            String salt = staffIr.findSalt(username);
            currentUser = staffIr.findByUsernamePassword(username, staffIr.kripto(salt + password));
            int numberOfLogins = staffIr.getNumberOfLogins(currentUser);
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
            if (numberOfLogins == 0) {
                this.dispose();
                PasswordChangeFrame passFrame=new PasswordChangeFrame(currentUser);
                passFrame.setVisible(true);
            } else {
                MainFrame mainFrame = new MainFrame(emc.getEntityManager(), currentUser);
                mainFrame.setVisible(true);
                dispose();
            }

        } catch (NoResultException nre) {
            nre.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gabimm ");
            passwordTxtf.setText("");
            passwordTxtf.requestFocus();
        } catch (AppException ae) {
            ae.printStackTrace();
            JOptionPane.showMessageDialog(null, ae.getMessage());
        }

    }

    private int close() {
        WindowEvent winClosing = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosing);
        return 0;
    }
}
