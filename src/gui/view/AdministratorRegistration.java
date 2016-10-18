package gui.view;

import ExceptionPackage.AppException;
import bl.StaffInterface;
import bl.StaffRepository;
import ejb.Staff;
import gui.model.StaffTableModel;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

public class AdministratorRegistration extends javax.swing.JFrame {

    EntityManager entityManager;
    StaffInterface staffIr;
    StaffTableModel staffTableModel;

    AdministratorRegistration(EntityManager em) {
        entityManager = em;
        initComponents();
        setLocation(220, 10);
        staffIr = new StaffRepository(entityManager);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        surnameTxtf = new javax.swing.JTextField();
        usernameTxtf = new javax.swing.JTextField();
        nameTxtf = new javax.swing.JTextField();
        genderCombo = new javax.swing.JComboBox<>();
        rePasswordTxtf = new javax.swing.JPasswordField();
        passwordTxtf = new javax.swing.JPasswordField();
        okButton = new javax.swing.JButton();
        roleCombo = new javax.swing.JComboBox<>();
        dateOfBirthCalendar = new com.toedter.calendar.JDateChooser();
        clearBtn = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        specializationTxtf = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        educationTxtf = new javax.swing.JTextArea();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("Surname :");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(20, 60, 140, 20);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("Username :");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(20, 100, 140, 20);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("Password :");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(20, 140, 140, 20);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel5.setText("Re-Password :");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(20, 180, 140, 20);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("Role :");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(20, 530, 140, 20);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel7.setText("Specialization :");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(20, 420, 140, 20);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel8.setText("Education :");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(20, 310, 140, 20);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel9.setText("Date Of Birth :");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(20, 270, 140, 20);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel10.setText("Gender :");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(20, 220, 140, 20);
        jPanel1.add(surnameTxtf);
        surnameTxtf.setBounds(170, 60, 160, 25);
        jPanel1.add(usernameTxtf);
        usernameTxtf.setBounds(170, 100, 160, 25);
        jPanel1.add(nameTxtf);
        nameTxtf.setBounds(170, 20, 160, 25);

        genderCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "null", "M", "F" }));
        jPanel1.add(genderCombo);
        genderCombo.setBounds(170, 220, 70, 30);
        jPanel1.add(rePasswordTxtf);
        rePasswordTxtf.setBounds(170, 180, 160, 25);
        jPanel1.add(passwordTxtf);
        passwordTxtf.setBounds(170, 140, 160, 25);

        okButton.setText("Add Administrator");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });
        jPanel1.add(okButton);
        okButton.setBounds(40, 580, 230, 23);

        roleCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrator" }));
        jPanel1.add(roleCombo);
        roleCombo.setBounds(170, 530, 190, 30);

        dateOfBirthCalendar.setDateFormatString("dd-MM-yyyy");
        jPanel1.add(dateOfBirthCalendar);
        dateOfBirthCalendar.setBounds(170, 260, 150, 30);

        clearBtn.setText("Clear");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });
        jPanel1.add(clearBtn);
        clearBtn.setBounds(340, 580, 260, 23);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel12.setText("Name :");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(20, 20, 140, 20);

        specializationTxtf.setColumns(20);
        specializationTxtf.setLineWrap(true);
        specializationTxtf.setRows(5);
        specializationTxtf.setWrapStyleWord(true);
        jScrollPane1.setViewportView(specializationTxtf);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(170, 420, 350, 96);

        educationTxtf.setColumns(20);
        educationTxtf.setLineWrap(true);
        educationTxtf.setRows(5);
        educationTxtf.setWrapStyleWord(true);
        jScrollPane2.setViewportView(educationTxtf);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(170, 310, 350, 96);
        jPanel1.add(background);
        background.setBounds(0, 0, 570, 620);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 639, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 642, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        Login login;
        try {
            addUserMethod();
            login = new Login();
            dispose();
            login.setVisible(true);
        } catch (AppException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(AddUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_okButtonActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        emptyLabels();
    }//GEN-LAST:event_clearBtnActionPerformed
    /*Zbraz te gjith textfields*/
    private void emptyLabels() {
        nameTxtf.setText("");
        surnameTxtf.setText("");
        usernameTxtf.setText("");
        passwordTxtf.setText("");
        rePasswordTxtf.setText("");
        genderCombo.setSelectedItem("null");
        dateOfBirthCalendar.setDate(null);
        educationTxtf.setText("");
        specializationTxtf.setText("");
        roleCombo.setSelectedItem("Doctor");
    }

    private void addUserMethod() throws AppException, SQLException {
        Staff staff = new Staff();
        String salt = "";
        for (int i = 0; i < 64; i++) {
            char c = (char) ThreadLocalRandom.current().nextInt(65, 122 + 1);
            salt += c;
        }
        byte[] password = staffIr.kripto(salt + passwordTxtf.getText().trim());
        String passwordString = passwordTxtf.getText().trim();
        staff.setUsername(usernameTxtf.getText().trim());
        staff.setName(nameTxtf.getText().trim());
        staff.setSurname(surnameTxtf.getText().trim());
        staff.setSalt(salt);
        staff.setPassword(password);
        staff.setDateOfBirth(dateOfBirthCalendar.getDate());
        staff.setGender(genderCombo.getSelectedItem().toString());
        staff.setEducation(educationTxtf.getText().trim());
        staff.setSpecialization(specializationTxtf.getText().trim());
        staff.setRole(roleCombo.getSelectedItem().toString());
        staff.setNumberOfLogins(0);
        staffIr.create(staff);
        staffIr.createMySQLUser(staff, passwordString);
        JOptionPane.showMessageDialog(this, "Perdoruesi u shtua me sukses !");
        emptyLabels();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JButton clearBtn;
    private com.toedter.calendar.JDateChooser dateOfBirthCalendar;
    private javax.swing.JTextArea educationTxtf;
    private javax.swing.JComboBox<String> genderCombo;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField nameTxtf;
    private javax.swing.JButton okButton;
    private javax.swing.JPasswordField passwordTxtf;
    private javax.swing.JPasswordField rePasswordTxtf;
    private javax.swing.JComboBox<String> roleCombo;
    private javax.swing.JTextArea specializationTxtf;
    private javax.swing.JTextField surnameTxtf;
    private javax.swing.JTextField usernameTxtf;
    // End of variables declaration//GEN-END:variables
}
