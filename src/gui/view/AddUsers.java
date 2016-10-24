package gui.view;

import ExceptionPackage.AppException;
import bl.LogsInterface;
import bl.LogsRepository;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import bl.StaffInterface;
import bl.StaffRepository;
import ejb.Staff;
import gui.model.StaffTableModel;
import java.awt.Color;
import java.awt.Graphics;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class AddUsers extends javax.swing.JInternalFrame {

    EntityManager entityManager;
    StaffInterface staffIr;
    LogsInterface logsIr;
    StaffTableModel staffTableModel;
    Staff currentUser;

    public AddUsers(EntityManager entityManager, Staff currentUser) {
        this.entityManager = entityManager;
        initComponents();
        setLocation(220, 10);
        this.currentUser = currentUser;
        staffIr = new StaffRepository(entityManager);
        logsIr = new LogsRepository(entityManager);
        String[] columnNamesTableModel = {"Username", "Name", "Surname", "DateOfBirth", "Role"};
        staffTableModel = new StaffTableModel(columnNamesTableModel,this.entityManager,this.currentUser);
        staffTableLoad();
        staffTableMoveKey();

        searchTxtf.getDocument().addDocumentListener(new DocumentListener() {
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
                staffTableFindByAll(searchTxtf.getText());
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
        deleteBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        specializationTxtf = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        educationTxtf = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        staffTbl = new javax.swing.JTable();
        searchTxtf = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        background2 = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Add Users");
        setMaximumSize(new java.awt.Dimension(1100, 654));
        setMinimumSize(new java.awt.Dimension(1100, 654));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("Surname :");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(20, 60, 140, 20);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("Username :");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(20, 100, 140, 20);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("Password :");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(20, 140, 140, 20);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel5.setText("Re-Password :");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(20, 180, 140, 20);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("Role :");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(20, 530, 140, 20);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel7.setText("Specialization :");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(20, 420, 140, 20);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel8.setText("Education :");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(20, 310, 140, 20);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel9.setText("Date Of Birth :");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(20, 270, 140, 20);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
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

        okButton.setBackground(new java.awt.Color(0, 0, 0));
        okButton.setForeground(new java.awt.Color(4, 205, 0));
        okButton.setText("Add User");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });
        jPanel1.add(okButton);
        okButton.setBounds(10, 580, 110, 23);

        roleCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Doctor", "LaboratorTechnician", "Recepsion", "Director", "Administrator" }));
        jPanel1.add(roleCombo);
        roleCombo.setBounds(170, 530, 190, 30);

        dateOfBirthCalendar.setDateFormatString("dd-MM-yyyy");
        jPanel1.add(dateOfBirthCalendar);
        dateOfBirthCalendar.setBounds(170, 260, 150, 30);

        deleteBtn.setBackground(new java.awt.Color(0, 0, 0));
        deleteBtn.setForeground(new java.awt.Color(4, 205, 0));
        deleteBtn.setText("Delete User");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        jPanel1.add(deleteBtn);
        deleteBtn.setBounds(130, 580, 120, 23);

        clearBtn.setBackground(new java.awt.Color(0, 0, 0));
        clearBtn.setForeground(new java.awt.Color(4, 205, 0));
        clearBtn.setText("Clear");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });
        jPanel1.add(clearBtn);
        clearBtn.setBounds(400, 580, 130, 23);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel12.setText("Name :");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(20, 20, 140, 20);

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setForeground(new java.awt.Color(4, 205, 0));
        jButton1.setText("Reset Password");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(270, 580, 110, 23);

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
        educationTxtf.setToolTipText("");
        educationTxtf.setWrapStyleWord(true);
        jScrollPane2.setViewportView(educationTxtf);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(170, 310, 350, 96);

        jPanel2.setLayout(null);

        staffTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        staffTbl.setShowHorizontalLines(false);
        staffTbl.setShowVerticalLines(false);
        jScrollPane3.setViewportView(staffTbl);

        jPanel2.add(jScrollPane3);
        jScrollPane3.setBounds(10, 60, 520, 530);
        jPanel2.add(searchTxtf);
        searchTxtf.setBounds(110, 10, 300, 30);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Search :");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(20, 20, 80, 20);

        background2.setBackground(new java.awt.Color(153, 153, 153));
        background2.setOpaque(true);
        jPanel2.add(background2);
        background2.setBounds(0, 0, 540, 600);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(540, 10, 540, 600);

        background.setBackground(new java.awt.Color(102, 102, 102));
        background.setOpaque(true);
        jPanel1.add(background);
        background.setBounds(0, 0, 1084, 620);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1084, 620));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void staffTableLoad() {
        List<Staff> list = staffIr.findAll();
        staffTableModel.add(list);
        staffTbl.setModel(staffTableModel);
        staffTableModel.fireTableDataChanged();
    }

    public void staffTableFindByAll(String text) {
        List<Staff> list = staffIr.findByAll(text);
        staffTableModel.add(list);
        staffTbl.setModel(staffTableModel);
        staffTableModel.fireTableDataChanged();
    }

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        try {
            addUserMethod();
        } catch (AppException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(AddUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_okButtonActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        emptyLabels();
    }//GEN-LAST:event_clearBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        try {
            deleteStaff();
        } catch (SQLException ex) {
            Logger.getLogger(AddUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        resetPassword();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void staffTableMoveKey() {
        ListSelectionModel rowSM = staffTbl.getSelectionModel();
        rowSM.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    return;
                }
                ListSelectionModel rowSM = (ListSelectionModel) e.getSource();
                int selectedIndex = rowSM.getMinSelectionIndex();
                if (selectedIndex > -1) {
                    Staff staff = staffTableModel.getStaff(selectedIndex);
                    nameTxtf.setText(staff.getName());
                    surnameTxtf.setText(staff.getSurname());
                    usernameTxtf.setText(staff.getUsername());
                    genderCombo.setSelectedItem(staff.getGender());
                    dateOfBirthCalendar.setDate(staff.getDateOfBirth());
                    educationTxtf.setText(staff.getEducation());
                    specializationTxtf.setText(staff.getSpecialization());
                }
            }
        });
    }

    /*Krijon dhe ruan ne databaze userin*/
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
        validation();
        staffIr.create(staff);
        staffIr.createMySQLUser(staff, passwordString);
        addCreateLog(staff);
        JOptionPane.showMessageDialog(this, "Perdoruesi u shtua me sukses !");
        staffTableLoad();
        emptyLabels();
    }

    /*Fshin nga databaza userin*/
    public void deleteStaff() throws SQLException {
        try {
            if (staffTbl.getSelectedRow() != -1) {
                String[] opcionet = {"Po", "Jo"};
                int response = JOptionPane.showOptionDialog(this,
                        "A dëshiron me e fshi Userin ?", "Kujdesë",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, opcionet, opcionet[0]);
                if (response == 0) {
                    Staff victimUser = staffTableModel.getStaff(staffTbl.getSelectedRow());
                    if (victimUser.getRole().equals("Administrator")) {
                        throw new AppException("Nuk mund të fshihet useri administrator.");
                    }
                    staffIr.remove(victimUser);
                    staffIr.deleteMySQLUser(victimUser);
                    addDeleteLog(victimUser);
                    staffTableLoad();
                    JOptionPane.showMessageDialog(this, "Useri u fshi me suksesë.");
                    emptyLabels();
                }
            } else {
                throw new AppException("Selekto Userin qe deshiron me e fshi.");
            }
        } catch (AppException ae) {
            JOptionPane.showMessageDialog(this, ae.getMessage());
        }
    }

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

    private void resetPassword() {
        try {
            int index;
            if ((index = staffTbl.getSelectedRow()) != -1) {
                Staff staff = staffTableModel.getStaff(index);
                String[] opcionet = {"Po", "Jo"};
                int response = JOptionPane.showOptionDialog(this,
                        "A dëshiron me restartu passwordin e userit : " + staff.getUsername() + " ?", "Kujdesë",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, opcionet, opcionet[0]);
                if (response == 0) {
                    staff.setNumberOfLogins(0);
                    byte[] passwordi = staffIr.kripto(staff.getSalt() + "12345678");
                    staff.setPassword(passwordi);
                    staffIr.setStaffPassword(staff);
                    staffIr.edit(staff);
                    JOptionPane.showMessageDialog(this, "Passwordi i userit :" + staff.getUsername() + " është 12345678");
                }
            } else {
                throw new AppException("Selekto Userin qe deshiron me i restartu passwordin.");
            }
        } catch (AppException ae) {
            JOptionPane.showMessageDialog(this, ae.getMessage());
        }
    }

    private void validation() throws AppException {
        if (nameTxtf == null || nameTxtf.getText().trim().equals("") || nameTxtf.getText().trim().length() > 50) {
            throw new AppException("Emri nuk duhet të jetë i zbrazët ose të ketë më shumë se 50 karaktera");
        }
        if (surnameTxtf == null || surnameTxtf.getText().trim().equals("") || surnameTxtf.getText().trim().length() > 50) {
            throw new AppException("Mbiemri nuk duhet të jetë i zbrazët ose të ketë më shumë se 50 karaktera");
        }
        if (usernameTxtf == null || usernameTxtf.getText().trim().equals("") || usernameTxtf.getText().trim().length() > 50) {
            throw new AppException("Username nuk duhet të jetë i zbrazët ose të ketë më shumë se 50 karaktera");
        }
        if (passwordTxtf.getText() == null || passwordTxtf.getText().trim().equals("") || passwordTxtf.getText().trim().length() > 50) {
            throw new AppException("Passwordi nuk duhet të jetë i zbrazët dhe nuk duhet të ketë më shumë se 50 karaktera");
        }
        if (!passwordTxtf.getText().trim().equals(rePasswordTxtf.getText().trim())) {
            throw new AppException("Passwordi nuk është i njejtë në dy fushat e passwordit");
        }
        if (genderCombo.getSelectedItem().equals("null")) {
            throw new AppException("Gjinia duhet të zgjedhet");
        }
        if (educationTxtf.getText().trim().length() > 500) {
            throw new AppException("Edukimi nuk duhet të ketë më shumë se 500 karaktera");
        }
        if (specializationTxtf.getText().trim().length() > 500) {
            throw new AppException("Specializimi nuk duhet të ketë më shumë se 500 karaktera");
        }
    }

    public void addCreateLog(Staff newStaff) throws AppException {

        ejb.Logs logs = new ejb.Logs();
        Date date = logsIr.findDate();
        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat sdf2 = new SimpleDateFormat("HH:mm");
        String today = sdf.format(date);
        String koha = sdf2.format(date);

        String message = currentUser.getName() + " " + currentUser.getSurname() + " me username-in: " + currentUser.getUsername() + " Ka shtuar antarin e stafit " + newStaff.getName() + " " + newStaff.getSurname() + " në datën : " + today + " në ora " + koha;

        logs.setUsername(currentUser);
        logs.setTimeStamp(date);
        logs.setMessage(message);
        logs.setType("Create");

        logsIr.create(logs);
    }

    public void addDeleteLog(Staff victimStaff) throws AppException {

        ejb.Logs logs = new ejb.Logs();
        Date date = logsIr.findDate();
        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat sdf2 = new SimpleDateFormat("HH:mm");
        String today = sdf.format(date);
        String koha = sdf2.format(date);

        String message = currentUser.getName() + " " + currentUser.getSurname() + " me username-in: " + currentUser.getUsername() + " Ka fshirë antarin e stafit" + victimStaff.getName() + " " + victimStaff.getSurname() + " në datën : " + today + " në ora " + koha;

        logs.setUsername(currentUser);
        logs.setTimeStamp(date);
        logs.setMessage(message);
        logs.setType("Delete");

        logsIr.create(logs);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JLabel background2;
    private javax.swing.JButton clearBtn;
    private com.toedter.calendar.JDateChooser dateOfBirthCalendar;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JTextArea educationTxtf;
    private javax.swing.JComboBox<String> genderCombo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField nameTxtf;
    private javax.swing.JButton okButton;
    private javax.swing.JPasswordField passwordTxtf;
    private javax.swing.JPasswordField rePasswordTxtf;
    private javax.swing.JComboBox<String> roleCombo;
    private javax.swing.JTextField searchTxtf;
    private javax.swing.JTextArea specializationTxtf;
    private javax.swing.JTable staffTbl;
    private javax.swing.JTextField surnameTxtf;
    private javax.swing.JTextField usernameTxtf;
    // End of variables declaration//GEN-END:variables
}
