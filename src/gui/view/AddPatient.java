package gui.view;

import ExceptionPackage.AppException;
import bl.LogsInterface;
import bl.LogsRepository;
import bl.PatientInterface;
import bl.PatientRepository;
import ejb.Patient;
import ejb.Staff;
import ejb.Logs;
import gui.model.PatientTableModel;
import java.awt.Dimension;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class AddPatient extends javax.swing.JInternalFrame {

    EntityManager entityManager;
    PatientInterface patientIr;
    PatientTableModel patientTM;
    Staff currentUser;
    LogsInterface logsIr;
    MainFrame mainFrame;
    public AddPatient(EntityManager entityManager, Staff currentUser,MainFrame mainFrame) {
        this.entityManager = entityManager;
        this.currentUser = currentUser;
        initComponents();
        setLocation(220, 10);
        patientIr = new PatientRepository(entityManager);
        logsIr = new LogsRepository(entityManager);
        String[] columnNamesTableModel = {"Name", "Surname", "ParentName", "Gender", "DateOfBirth", "City", "Phone", "Allergies"};
        patientTM = new PatientTableModel(columnNamesTableModel);
        this.mainFrame=mainFrame;;

        patientTableLoad();
        patientTableMoveKey();

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
        jPanel2 = new javax.swing.JPanel();
        nameTxtf0 = new javax.swing.JLabel();
        nameTxtf1 = new javax.swing.JLabel();
        nameTxtf2 = new javax.swing.JLabel();
        nameTxtf3 = new javax.swing.JLabel();
        nameTxtf4 = new javax.swing.JLabel();
        nameTxtf5 = new javax.swing.JLabel();
        nameTxtf6 = new javax.swing.JLabel();
        nameTxtf7 = new javax.swing.JLabel();
        nameTxtf8 = new javax.swing.JLabel();
        nameTxtf = new javax.swing.JTextField();
        surnameTxtf = new javax.swing.JTextField();
        parentNameTxtf = new javax.swing.JTextField();
        phoneTxtf = new javax.swing.JTextField();
        emailTxtf = new javax.swing.JTextField();
        cityTxtf = new javax.swing.JTextField();
        dateOfBirthCalendar = new com.toedter.calendar.JDateChooser();
        genderCombo = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        allergiesTxtf = new javax.swing.JTextArea();
        deleteButton = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        nameTxtf9 = new javax.swing.JLabel();
        birthplaceTxtf = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        patientTbl = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        searchTxtf = new javax.swing.JTextField();
        background = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Add Patient");
        setMaximumSize(new java.awt.Dimension(1100, 654));
        setMinimumSize(new java.awt.Dimension(1100, 654));
        setPreferredSize(new java.awt.Dimension(1100, 654));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setLayout(null);

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nameTxtf0.setForeground(new java.awt.Color(255, 255, 255));
        nameTxtf0.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        nameTxtf0.setText("Name :");
        jPanel2.add(nameTxtf0, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 120, 20));

        nameTxtf1.setForeground(new java.awt.Color(255, 255, 255));
        nameTxtf1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        nameTxtf1.setText("Surname :");
        jPanel2.add(nameTxtf1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 120, 20));

        nameTxtf2.setForeground(new java.awt.Color(255, 255, 255));
        nameTxtf2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        nameTxtf2.setText("Parent Name :");
        jPanel2.add(nameTxtf2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 120, 20));

        nameTxtf3.setForeground(new java.awt.Color(255, 255, 255));
        nameTxtf3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        nameTxtf3.setText("Date Of Birth :");
        jPanel2.add(nameTxtf3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 120, 20));

        nameTxtf4.setForeground(new java.awt.Color(255, 255, 255));
        nameTxtf4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        nameTxtf4.setText("Gender :");
        jPanel2.add(nameTxtf4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 120, 20));

        nameTxtf5.setForeground(new java.awt.Color(255, 255, 255));
        nameTxtf5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        nameTxtf5.setText("Telephone :");
        jPanel2.add(nameTxtf5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 120, 20));

        nameTxtf6.setForeground(new java.awt.Color(255, 255, 255));
        nameTxtf6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        nameTxtf6.setText("Email :");
        jPanel2.add(nameTxtf6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 120, 20));

        nameTxtf7.setForeground(new java.awt.Color(255, 255, 255));
        nameTxtf7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        nameTxtf7.setText("Birthplace :");
        jPanel2.add(nameTxtf7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 120, 20));

        nameTxtf8.setForeground(new java.awt.Color(255, 255, 255));
        nameTxtf8.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        nameTxtf8.setText("Allergies :");
        jPanel2.add(nameTxtf8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 120, 20));

        nameTxtf.setBackground(new java.awt.Color(204, 255, 204));
        nameTxtf.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jPanel2.add(nameTxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 160, 25));

        surnameTxtf.setBackground(new java.awt.Color(204, 255, 204));
        surnameTxtf.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jPanel2.add(surnameTxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 160, 25));

        parentNameTxtf.setBackground(new java.awt.Color(204, 255, 204));
        parentNameTxtf.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jPanel2.add(parentNameTxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 160, 25));

        phoneTxtf.setBackground(new java.awt.Color(204, 255, 204));
        phoneTxtf.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jPanel2.add(phoneTxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, 160, 25));

        emailTxtf.setBackground(new java.awt.Color(204, 255, 204));
        emailTxtf.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jPanel2.add(emailTxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, 160, 25));

        cityTxtf.setBackground(new java.awt.Color(204, 255, 204));
        cityTxtf.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jPanel2.add(cityTxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 340, 160, 25));

        dateOfBirthCalendar.setBackground(new java.awt.Color(204, 255, 204));
        dateOfBirthCalendar.setDateFormatString("dd-MM-yyyy");
        dateOfBirthCalendar.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jPanel2.add(dateOfBirthCalendar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 160, 25));

        genderCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "null", "M", "F" }));
        jPanel2.add(genderCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, 80, 25));

        allergiesTxtf.setBackground(new java.awt.Color(204, 255, 204));
        allergiesTxtf.setColumns(20);
        allergiesTxtf.setFont(new java.awt.Font("Monospaced", 0, 11)); // NOI18N
        allergiesTxtf.setLineWrap(true);
        allergiesTxtf.setRows(5);
        allergiesTxtf.setWrapStyleWord(true);
        jScrollPane1.setViewportView(allergiesTxtf);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 380, 260, 150));

        deleteButton.setBackground(new java.awt.Color(0, 153, 102));
        deleteButton.setForeground(new java.awt.Color(204, 255, 204));
        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        jPanel2.add(deleteButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 540, 120, 40));

        clearBtn.setBackground(new java.awt.Color(0, 153, 102));
        clearBtn.setForeground(new java.awt.Color(204, 255, 204));
        clearBtn.setText("Clear");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });
        jPanel2.add(clearBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 540, 120, 40));

        saveButton.setBackground(new java.awt.Color(0, 153, 102));
        saveButton.setForeground(new java.awt.Color(204, 255, 204));
        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        jPanel2.add(saveButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 540, 120, 40));

        nameTxtf9.setForeground(new java.awt.Color(255, 255, 255));
        nameTxtf9.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        nameTxtf9.setText("City :");
        jPanel2.add(nameTxtf9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 120, 20));

        birthplaceTxtf.setBackground(new java.awt.Color(204, 255, 204));
        birthplaceTxtf.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jPanel2.add(birthplaceTxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 300, 160, 25));

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 490, 620);

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        patientTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        patientTbl.setShowHorizontalLines(false);
        patientTbl.setShowVerticalLines(false);
        jScrollPane2.setViewportView(patientTbl);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 84, 560, 500));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Search :");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 22, 179, 35));

        searchTxtf.setBackground(new java.awt.Color(204, 255, 204));
        jPanel3.add(searchTxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 300, 35));

        jPanel1.add(jPanel3);
        jPanel3.setBounds(490, 20, 580, 590);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 620));
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1084, 624));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void patientTableLoad() {
        List<Patient> list = patientIr.findAll();
        patientTM.add(list);
        patientTbl.setModel(patientTM);
        patientTM.fireTableDataChanged();
    }

    public void staffTableFindByAll(String text) {
        List<Patient> list = patientIr.findByAll(text);
        patientTM.add(list);
        patientTbl.setModel(patientTM);
        patientTM.fireTableDataChanged();
    }

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        mainFrame.keepRunning=false;
        try {
            addPatientMethod();
        } catch (AppException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        synchronized(mainFrame.messagesThread){
            mainFrame.keepRunning = true;
            mainFrame.messagesThread.notifyAll();
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        mainFrame.keepRunning=false;
        deletePatient();
        synchronized(mainFrame.messagesThread){
            mainFrame.keepRunning = true;
            mainFrame.messagesThread.notifyAll();
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        emptyLabels();
    }//GEN-LAST:event_clearBtnActionPerformed

    private void patientTableMoveKey() {
        ListSelectionModel rowSM = patientTbl.getSelectionModel();
        rowSM.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    return;
                }
                ListSelectionModel rowSM = (ListSelectionModel) e.getSource();
                int selectedIndex = rowSM.getMinSelectionIndex();
                if (selectedIndex > -1) {
                    Patient patient = patientTM.getPatient(selectedIndex);
                    nameTxtf.setText(patient.getName());
                    surnameTxtf.setText(patient.getSurname());
                    parentNameTxtf.setText(patient.getParentName());
                    genderCombo.setSelectedItem(patient.getGender());
                    dateOfBirthCalendar.setDate(patient.getDateOfBirth());
                    phoneTxtf.setText(patient.getPhone());
                    emailTxtf.setText(patient.getEmail());
                    birthplaceTxtf.setText(patient.getPlaceOFBirth());
                    cityTxtf.setText(patient.getCity());
                    allergiesTxtf.setText(patient.getAllergies());
                }
            }
        });
    }

    private void addPatientMethod() throws AppException {
        Patient patient = new Patient();
        patient.setName(nameTxtf.getText().trim());
        patient.setSurname(surnameTxtf.getText().trim());
        patient.setParentName(parentNameTxtf.getText().trim());
        patient.setDateOfBirth(dateOfBirthCalendar.getDate());
        patient.setGender(genderCombo.getSelectedItem().toString());
        patient.setPhone(phoneTxtf.getText().trim());
        patient.setEmail(emailTxtf.getText().trim());
        patient.setPlaceOFBirth(birthplaceTxtf.getText().trim());
        patient.setCity(cityTxtf.getText().trim());
        patient.setAllergies(allergiesTxtf.getText().trim());
        patient.setUsername(currentUser);
        validation();
        patientIr.create(patient);
        addCreateLog(patient);
        JOptionPane.showMessageDialog(this, "Pacienti u shtua me sukses");
        patientTableLoad();
        emptyLabels();
    }

    public void deletePatient() {
        try {
            if (patientTbl.getSelectedRow() != -1) {
                String[] opcionet = {"Po", "Jo"};
                int response = JOptionPane.showOptionDialog(this,
                        "A dëshiron me e fshi Pacientin ?", "Kujdesë",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, opcionet, opcionet[0]);
                if (response == 0) {
                    Patient victimPatient = patientTM.getPatient(patientTbl.getSelectedRow());
                    patientIr.remove(victimPatient);
                    patientTableLoad();
                    addDeleteLog(victimPatient);
                    JOptionPane.showMessageDialog(this, "Pacienti u fshi me suksesë.");
                    emptyLabels();
                }
            } else {
                throw new AppException("Selekto Userin qe deshiron me e fshi.");
            }
        } catch (AppException ae) {
            JOptionPane.showMessageDialog(this, ae.getMessage());
        }
    }

    public void emptyLabels() {
        nameTxtf.setText("");
        surnameTxtf.setText("");
        parentNameTxtf.setText("");
        phoneTxtf.setText("");
        emailTxtf.setText("");
        birthplaceTxtf.setText("");
        cityTxtf.setText("");
        dateOfBirthCalendar.setDate(null);
        genderCombo.setSelectedItem("null");
        allergiesTxtf.setText("");
    }

    private void validation() throws AppException {
        if (nameTxtf == null || nameTxtf.getText().trim().equals("") || nameTxtf.getText().trim().length() > 50) {
            throw new AppException("Emri nuk duhet të jetë i zbrazët ose të ketë më shumë se 50 karaktera");
        }
        if (surnameTxtf == null || surnameTxtf.getText().trim().equals("") || surnameTxtf.getText().trim().length() > 50) {
            throw new AppException("Mbiemri nuk duhet të jetë i zbrazët ose të ketë më shumë se 50 karaktera");
        }
        if (parentNameTxtf == null || parentNameTxtf.getText().trim().equals("") || parentNameTxtf.getText().trim().length() > 50) {
            throw new AppException("Emri i prindit nuk duhet të jetë i zbrazët ose të ketë më shumë se 50 karaktera");
        }
        if (dateOfBirthCalendar.getDate() == null) {
            throw new AppException("Data nuk duhet te jete e zbrazet");
        }
        if (genderCombo.getSelectedItem().equals("null")) {
            throw new AppException("Gjinia duhet të zgjedhet");
        }
        if (phoneTxtf.getText().trim().length() > 50) {
            throw new AppException("Numri i telefonit nuk duhet të ketë më shumë se 50 karaktera");
        }
        if (emailTxtf.getText().trim().length() > 50) {
            throw new AppException("Email nuk duhet të ketë më shumë se 50 karaktera");
        }
        if (birthplaceTxtf.getText().trim().length() > 50) {
            throw new AppException("Vendi i lindjes nuk duhet të ketë më shumë se 50 karaktera");
        }
        if (cityTxtf.getText().trim().length() > 50) {
            throw new AppException("Qyteti nuk duhet të ketë më shumë se 50 karaktera");
        }
        if (allergiesTxtf.getText().trim().length() > 50) {
            throw new AppException("Alergjitë nuk duhet të ketë më shumë se 500 karaktera");
        }
    }

    public void addCreateLog(Patient patient) throws AppException {

        Logs logs = new Logs();
        Date date = logsIr.findDate();
        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat sdf2 = new SimpleDateFormat("HH:mm");
        String today = sdf.format(date);
        String koha = sdf2.format(date);

        String message = currentUser.getName() + " " + currentUser.getSurname() + " me username-in: " + currentUser.getUsername() + " Ka shtuar pacientin " + patient.getName() + " " + patient.getSurname() + " në datën : " + today + " në ora " + koha;

        logs.setUsername(currentUser);
        logs.setTimeStamp(date);
        logs.setMessage(message);
        logs.setType("Create");

        logsIr.create(logs);
    }

    public void addDeleteLog(Patient patient) throws AppException {

        Logs logs = new Logs();
        Date date = logsIr.findDate();
        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat sdf2 = new SimpleDateFormat("HH:mm");
        String today = sdf.format(date);
        String koha = sdf2.format(date);

        String message = currentUser.getName() + " " + currentUser.getSurname() + " me username-in: " + currentUser.getUsername() + " Ka fshirë pacientin" + patient.getName() + " " + patient.getSurname() + " në datën : " + today + " në ora " + koha;

        logs.setUsername(currentUser);
        logs.setTimeStamp(date);
        logs.setMessage(message);
        logs.setType("Delete");

        logsIr.create(logs);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea allergiesTxtf;
    private javax.swing.JLabel background;
    private javax.swing.JTextField birthplaceTxtf;
    private javax.swing.JTextField cityTxtf;
    private javax.swing.JButton clearBtn;
    private com.toedter.calendar.JDateChooser dateOfBirthCalendar;
    private javax.swing.JButton deleteButton;
    private javax.swing.JTextField emailTxtf;
    private javax.swing.JComboBox<String> genderCombo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField nameTxtf;
    private javax.swing.JLabel nameTxtf0;
    private javax.swing.JLabel nameTxtf1;
    private javax.swing.JLabel nameTxtf2;
    private javax.swing.JLabel nameTxtf3;
    private javax.swing.JLabel nameTxtf4;
    private javax.swing.JLabel nameTxtf5;
    private javax.swing.JLabel nameTxtf6;
    private javax.swing.JLabel nameTxtf7;
    private javax.swing.JLabel nameTxtf8;
    private javax.swing.JLabel nameTxtf9;
    private javax.swing.JTextField parentNameTxtf;
    private javax.swing.JTable patientTbl;
    private javax.swing.JTextField phoneTxtf;
    private javax.swing.JButton saveButton;
    private javax.swing.JTextField searchTxtf;
    private javax.swing.JTextField surnameTxtf;
    // End of variables declaration//GEN-END:variables
}
