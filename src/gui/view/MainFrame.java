package gui.view;

import ExceptionPackage.AppException;
import bl.EntMngClass;
import bl.MessageInterface;
import bl.MessageRepository;
import bl.StaffInterface;
import bl.StaffRepository;
import ejb.Staff;
import java.awt.Color;
import java.beans.PropertyVetoException;
import javax.persistence.EntityManager;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;



public class MainFrame extends javax.swing.JFrame {
    String pozita = "";
    EntityManager entityManager;
    Staff currentUser;
    StaffInterface staffIr;
    MessageInterface messageIr;
    Messages addMessage;
    public Thread messagesThread;
    boolean keepRunning=true;
    
    public MainFrame(EntityManager entityManager, Staff currentUser) {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.entityManager=entityManager;
        initComponents();
        initInterfaces(this.entityManager);
        this.currentUser = currentUser;
        pozita = currentUser.getRole();
        setPrioritys();
        messageCheckingThread();
    }
    
    public void initInterfaces(EntityManager entityManager){
        staffIr=new StaffRepository(entityManager);
        messageIr=new MessageRepository(entityManager);
    }
    
    public void messageCheckingThread(){
        messagesThread = new Thread() {
            long numberOfMessagesUnseen,tempAllMessages;
            long numberOfMessages=0;
            long tempUnseenMessages=0;
            
            
            @Override
            public void run() {
                
                try{
                    synchronized(messagesThread){
                        while(!keepRunning)
                            messagesThread.wait();
                        tryThread();
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            
            private synchronized void tryThread()throws InterruptedException{
        
                    if ((numberOfMessagesUnseen = messageIr.countUnseenMessagesForUser(currentUser)) > 0) {
                        if (currentUser.getRole().equals("Doctor")||currentUser.getRole().equals("Director")||currentUser.getRole().equals("Recepsion")||currentUser.getRole().equals("LaboratorTechnician")) {
                            if(numberOfMessagesUnseen!=tempUnseenMessages){
                                if(addMessage!=null){
                                    addMessage.staffTableLoad();
                                    tempUnseenMessages=numberOfMessagesUnseen;
                                }
                            }
                            if(currentUser.getRole().equals("Doctor")||currentUser.getRole().equals("LaboratorTechnician")){

                                jButton7.setForeground(Color.RED);
                                jButton7.setText("Messages ("+numberOfMessagesUnseen+")");
                            }else if(currentUser.getRole().equals("Recepsion")){
                                jButton5.setForeground(Color.RED);
                                jButton5.setText("Messages ("+numberOfMessagesUnseen+")");
                            }else if(currentUser.getRole().equals("Director")){
                                jButton3.setForeground(Color.RED);
                                jButton3.setText("Messages ("+numberOfMessagesUnseen+")");
                            }
                        }
                    } else {
                            if(currentUser.getRole().equals("Doctor")||currentUser.getRole().equals("LaboratorTechnician")){
                                jButton7.setForeground(new Color(204,255,204));
                                jButton7.setText("Messages ("+numberOfMessagesUnseen+")");
                            }
                            if(currentUser.getRole().equals("Recepsion")){
                                jButton5.setForeground(new Color(204,255,204));
                                jButton5.setText("Messages ("+numberOfMessagesUnseen+")");
                            }
                            if(currentUser.getRole().equals("Director")){
                                jButton3.setForeground(new Color(204,255,204));
                                jButton3.setText("Messages ("+numberOfMessagesUnseen+")");
                            }
                    }
                    
                    if(addMessage!=null){
                        if(numberOfMessages!=(tempAllMessages=messageIr.countMessagesForUser(currentUser))){
                            addMessage.staffTableLoad();
                        }
                        numberOfMessages=tempAllMessages;
                    }
                        
                    sleep(500);
                    run();
    }  
        };
        
        messagesThread.start();
    }
    
   
    public void addSeeVisits() {
        try {
            if (desktopPane.getSelectedFrame() != null) {
                desktopPane.getSelectedFrame().setClosed(true);
            }
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }

        SeeVisits seeVisits = new SeeVisits(entityManager,currentUser,this);
        desktopPane.add(seeVisits);
        seeVisits.show();
    }

    public void addAddNotification() {
        try {
            if (desktopPane.getSelectedFrame() != null) {
                desktopPane.getSelectedFrame().setClosed(true);
            }
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        // sdi qa t'thom biro per qeto veqe sdashta me i fshi e hajt thash qija nanen ma mut sbohet e i lash :D
        // AddDetailsToVisit addNotification = new AddDetailsToVisit();
        // desktopPane.add(addNotification);
        // addNotification.show();
    }

    public void addAddPatient() {
        try {
            if (desktopPane.getSelectedFrame() != null) {
                desktopPane.getSelectedFrame().setClosed(true);
            }
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }

        AddPatient addPatient = new AddPatient(entityManager, currentUser,this);
        desktopPane.add(addPatient);
        addPatient.show();
    }

    public void addAddUsers() {
        try {
            if (desktopPane.getSelectedFrame() != null) {
                desktopPane.getSelectedFrame().setClosed(true);
            }
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }

        AddUsers addUsers = new AddUsers(entityManager, currentUser);
        desktopPane.add(addUsers);
        addUsers.show();
    }

    public void addCreateAnalysis() {
        try {
            if (desktopPane.getSelectedFrame() != null) {
                desktopPane.getSelectedFrame().setClosed(true);
            }
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }

        CreateAnalysis createAnalysis = new CreateAnalysis();
        desktopPane.add(createAnalysis);
        createAnalysis.show();
    }

    public void addCreateVisit() {
        try {
            if (desktopPane.getSelectedFrame() != null) {
                desktopPane.getSelectedFrame().setClosed(true);
            }
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }

        CreateVisit createVisit = new CreateVisit(entityManager,currentUser,this);
        desktopPane.add(createVisit);
        createVisit.show();
    }

    public void addExportReports() {
        try {
            if (desktopPane.getSelectedFrame() != null) {
                desktopPane.getSelectedFrame().setClosed(true);
            }
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }

        ExportReports exportReports = new ExportReports(entityManager);
        desktopPane.add(exportReports);
        exportReports.show();
    }

    public void addLogs() {
        try {
            if (desktopPane.getSelectedFrame() != null) {
                desktopPane.getSelectedFrame().setClosed(true);
            }
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }

        SeeLogs logs = new SeeLogs(entityManager);
        desktopPane.add(logs);
        logs.show();
    }

    public void addMessage() {
        try {
            if (desktopPane.getSelectedFrame() != null) {
                desktopPane.getSelectedFrame().setClosed(true);
            }
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    
        if(addMessage==null)
            addMessage = new Messages(entityManager, currentUser,this);
        if(desktopPane.getSelectedFrame()!=addMessage){
            desktopPane.add(addMessage);
            addMessage.show();
        }
    }

    public void addPrintReports() {
        try {
            if (desktopPane.getSelectedFrame() != null) {
                desktopPane.getSelectedFrame().setClosed(true);
            }
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }

        PrintReports printReports = new PrintReports(currentUser,entityManager);
        desktopPane.add(printReports);
        printReports.show();
    }

    public void addSearch() {
        try {
            if (desktopPane.getSelectedFrame() != null) {
                desktopPane.getSelectedFrame().setClosed(true);
            }
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }

        Search search = new Search(entityManager);
        desktopPane.add(search);
        search.show();
    }

    public void addSeeAppointments() {
        try {
            if (desktopPane.getSelectedFrame() != null) {
                desktopPane.getSelectedFrame().setClosed(true);
            }
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }

        SeeAppointments seeAppointments = new SeeAppointments();
        desktopPane.add(seeAppointments);
        seeAppointments.show();
    }

    public void addVisit() {
        try {
            if (desktopPane.getSelectedFrame() != null) {
                desktopPane.getSelectedFrame().setClosed(true);
            }
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }

        Visits addDetailsToVisit = new Visits(entityManager,currentUser,this);
        desktopPane.add(addDetailsToVisit);
        addDetailsToVisit.show();
    }

    public void addSeeReports() {
        try {
            if (desktopPane.getSelectedFrame() != null) {
                desktopPane.getSelectedFrame().setClosed(true);
            }
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }

        SeeReports seeReports = new SeeReports();
        desktopPane.add(seeReports);
        seeReports.show();
    }
    
    public void addPrintVisits() {
        try {
            if (desktopPane.getSelectedFrame() != null) {
                desktopPane.getSelectedFrame().setClosed(true);
            }
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }

        PrintVisits printVisits= new PrintVisits(entityManager, currentUser,this);
        desktopPane.add(printVisits);
        printVisits.show();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        background2 = new javax.swing.JLabel();
        background = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        cutMenuItem = new javax.swing.JMenuItem();
        copyMenuItem = new javax.swing.JMenuItem();
        pasteMenuItem = new javax.swing.JMenuItem();
        deleteMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setBackground(new java.awt.Color(0, 153, 102));
        jButton1.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(204, 255, 204));
        jButton1.setText("jButton1");
        jButton1.setOpaque(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        desktopPane.add(jButton1);
        jButton1.setBounds(0, 0, 200, 50);

        jButton2.setBackground(new java.awt.Color(0, 153, 102));
        jButton2.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(204, 255, 204));
        jButton2.setText("jButton2");
        jButton2.setOpaque(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        desktopPane.add(jButton2);
        jButton2.setBounds(0, 50, 200, 50);

        jButton3.setBackground(new java.awt.Color(0, 153, 102));
        jButton3.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(204, 255, 204));
        jButton3.setText("jButton3");
        jButton3.setOpaque(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        desktopPane.add(jButton3);
        jButton3.setBounds(0, 100, 200, 50);

        jButton4.setBackground(new java.awt.Color(0, 153, 102));
        jButton4.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(204, 255, 204));
        jButton4.setText("jButton4");
        jButton4.setOpaque(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        desktopPane.add(jButton4);
        jButton4.setBounds(0, 150, 200, 50);

        jButton5.setBackground(new java.awt.Color(0, 153, 102));
        jButton5.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jButton5.setForeground(new java.awt.Color(204, 255, 204));
        jButton5.setText("jButton5");
        jButton5.setOpaque(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        desktopPane.add(jButton5);
        jButton5.setBounds(0, 200, 200, 50);

        jButton7.setBackground(new java.awt.Color(0, 153, 102));
        jButton7.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jButton7.setForeground(new java.awt.Color(204, 255, 204));
        jButton7.setText("jButton7");
        jButton7.setOpaque(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        desktopPane.add(jButton7);
        jButton7.setBounds(0, 300, 200, 50);

        jButton6.setBackground(new java.awt.Color(0, 153, 102));
        jButton6.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jButton6.setForeground(new java.awt.Color(204, 255, 204));
        jButton6.setText("jButton6");
        jButton6.setOpaque(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        desktopPane.add(jButton6);
        jButton6.setBounds(0, 250, 200, 50);

        background2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/Medicine Logo.png"))); // NOI18N
        desktopPane.add(background2);
        background2.setBounds(450, 40, 600, 600);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/BackgroundOrdinanca.png"))); // NOI18N
        desktopPane.add(background);
        background.setBounds(0, 0, 1365, 680);

        fileMenu.setMnemonic('f');
        fileMenu.setText("File");

        openMenuItem.setMnemonic('o');
        openMenuItem.setText("Open");
        fileMenu.add(openMenuItem);

        saveMenuItem.setMnemonic('s');
        saveMenuItem.setText("Save");
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setMnemonic('a');
        saveAsMenuItem.setText("Save As ...");
        saveAsMenuItem.setDisplayedMnemonicIndex(5);
        fileMenu.add(saveAsMenuItem);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText("Edit");

        cutMenuItem.setMnemonic('t');
        cutMenuItem.setText("Cut");
        editMenu.add(cutMenuItem);

        copyMenuItem.setMnemonic('y');
        copyMenuItem.setText("Copy");
        editMenu.add(copyMenuItem);

        pasteMenuItem.setMnemonic('p');
        pasteMenuItem.setText("Paste");
        editMenu.add(pasteMenuItem);

        deleteMenuItem.setMnemonic('d');
        deleteMenuItem.setText("Delete");
        editMenu.add(deleteMenuItem);

        menuBar.add(editMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Help");

        contentMenuItem.setMnemonic('c');
        contentMenuItem.setText("Contents");
        helpMenu.add(contentMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1365, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void setPrioritys() {
        if (pozita.equals("Recepsion")) {
            jButton1.setText("Add Patient");
            jButton2.setText("Create Visits");
            jButton3.setText("See Visits");
            jButton4.setText("Print Visits");
            jButton5.setText("Messages");
            jButton6.setVisible(false);
            jButton7.setVisible(false);
        }
        else if (pozita.equals("Doctor")) {
            jButton1.setText("Visits");
            jButton2.setText("Create Visits");
            jButton3.setText("See Visits");
            jButton4.setText("Search");
            jButton5.setText("Add Patient");
            jButton6.setText("Print Reports");
            jButton7.setText("Messages");
        }
        else if (pozita.equals("Director")) {
            jButton1.setText("Export Reports");
            jButton2.setText("Logs");
            jButton3.setText("Messages");
            jButton4.setVisible(false);
            jButton5.setVisible(false);
            jButton6.setVisible(false);
            jButton7.setVisible(false);
        }
        else if (pozita.equals("Administrator")) {
            jButton1.setText("Add User");
            jButton2.setVisible(false);
            jButton3.setVisible(false);
            jButton4.setVisible(false);
            jButton5.setVisible(false);
            jButton6.setVisible(false);
            jButton7.setVisible(false);
        }
        else if (pozita.equals("LaboratorTechnician")){
            jButton1.setText("Visits");
            jButton2.setText("Create Visits");
            jButton3.setText("See Visits");
            jButton4.setText("Search");
            jButton5.setText("Add Patient");
            jButton6.setText("Print Reports");
            jButton7.setText("Messages");
        }
    }

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        logOutMethod();
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        switch(jButton1.getText()){
            case "Add Patient":
                addAddPatient();
                break;
            case "See Visits":
                addSeeVisits();//zevendso
                break;
            case "Create Visits":
                addCreateVisit();
                break;
            case "Visits":
                addVisit();
                break;
            case "Print Visits":
                addPrintVisits();
                break;
            case "Messages":
                addMessage();
                break;
            case "Search":
                addSearch();
                break;
            case "Add User":
                addAddUsers();
                break;
            case "Export Reports":
                addExportReports();
                break;
            case "Logs":
                addLogs();
                break;
            case "Print Reports":
                addPrintReports();
                break;    
            default:
                if(jButton1.getText().contains("Message"))
                    addMessage();
                else
                JOptionPane.showMessageDialog(null, "Kontakto Administratorin !");
        }
        
        
       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        switch(jButton2.getText()){
            case "Add Patient":
                addAddPatient();
                break;
            case "See Visits":
                addSeeVisits();//zevendso
                break;
            case "Create Visits":
                addCreateVisit();
                break;
            case "Visits":
                addVisit();
                break;
            case "Print Visits":
                addPrintVisits();
                break;
            case "Messages":
                addMessage();
                break;
            case "Search":
                addSearch();
                break;
            case "Add User":
                addAddUsers();
                break;
            case "Export Reports":
                addExportReports();
                break;
            case "Logs":
                addLogs();
                break;
            case "Print Reports":
                addPrintReports();
                break;    
            default:
                if(jButton2.getText().contains("Message"))
                    addMessage();
                else
                JOptionPane.showMessageDialog(null, "Kontakto Administratorin !");
        }
        
       
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        switch(jButton3.getText()){
            case "Add Patient":
                addAddPatient();
                break;
            case "See Visits":
                addSeeVisits();//zevendso
                break;
            case "Create Visits":
                addCreateVisit();
                break;
            case "Visits":
                addVisit();
                break;
            case "Print Visits":
                addPrintVisits();
                break;
            case "Messages":
                addMessage();
                break;
            case "Search":
                addSearch();
                break;
            case "Add User":
                addAddUsers();
                break;
            case "Export Reports":
                addExportReports();
                break;
            case "Logs":
                addLogs();
                break;
            case "Print Reports":
                addPrintReports();
                break;    
            default:
                if(jButton3.getText().contains("Message"))
                    addMessage();
                else
                JOptionPane.showMessageDialog(null, "Kontakto Administratorin !");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        switch(jButton4.getText()){
            case "Add Patient":
                addAddPatient();
                break;
            case "See Visits":
                addSeeVisits();//zevendso
                break;
            case "Create Visits":
                addCreateVisit();
                break;
            case "Visits":
                addVisit();
                break;
            case "Print Visits":
                addPrintVisits();
                break;
            case "Messages":
                addMessage();
                break;
            case "Search":
                addSearch();
                break;
            case "Add User":
                addAddUsers();
                break;
            case "Export Reports":
                addExportReports();
                break;
            case "Logs":
                addLogs();
                break;
            case "Print Reports":
                addPrintReports();
                break;    
            default:
                if(jButton4.getText().contains("Message"))
                    addMessage();
                else
                JOptionPane.showMessageDialog(null, "Kontakto Administratorin !");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        switch(jButton5.getText()){
            case "Add Patient":
                addAddPatient();
                break;
            case "See Visits":
                addSeeVisits();//zevendso
                break;
            case "Create Visits":
                addCreateVisit();
                break;
            case "Visits":
                addVisit();
                break;
            case "Print Visits":
                addPrintVisits();
                break;
            case "Messages":
                addMessage();
                break;
            case "Search":
                addSearch();
                break;
            case "Add User":
                addAddUsers();
                break;
            case "Export Reports":
                addExportReports();
                break;
            case "Logs":
                addLogs();
                break;
            case "Print Reports":
                addPrintReports();
                break;    
            default:
                if(jButton5.getText().contains("Message"))
                    addMessage();
                else
                JOptionPane.showMessageDialog(null, "Kontakto Administratorin !");
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        switch(jButton6.getText()){
            case "Add Patient":
                addAddPatient();
                break;
            case "See Visits":
                addSeeVisits();//zevendso
                break;
            case "Create Visits":
                addCreateVisit();
                break;
            case "Visits":
                addVisit();
                break;
            case "Print Visits":
                addPrintVisits();
                break;
            case "Messages":
                addMessage();
                break;
            case "Search":
                addSearch();
                break;
            case "Add User":
                addAddUsers();
                break;
            case "Export Reports":
                addExportReports();
                break;
            case "Logs":
                addLogs();
                break;
            case "Print Reports":
                addPrintReports();
                break;    
            default:
                if(jButton6.getText().contains("Message"))
                    addMessage();
                else
                JOptionPane.showMessageDialog(null, "Kontakto Administratorin !");
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        switch(jButton7.getText()){
            case "Add Patient":
                addAddPatient();
                break;
            case "See Visits":
                addSeeVisits();//zevendso
                break;
            case "Create Visits":
                addCreateVisit();
                break;
            case "Visits":
                addVisit();
                break;
            case "Print Visits":
                addPrintVisits();
                break;
            case "Messages":
                addMessage();
                break;
            case "Search":
                addSearch();
                break;
            case "Add User":
                addAddUsers();
                break;
            case "Export Reports":
                addExportReports();
                break;
            case "Logs":
                addLogs();
                break;
            case "Print Reports":
                addPrintReports();
                break;    
            default:
                if(jButton7.getText().contains("Message"))
                    addMessage();
                else
                JOptionPane.showMessageDialog(null, "Kontakto Administratorin !");
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JLabel background;
    private javax.swing.JLabel background2;
    private javax.swing.JMenuItem contentMenuItem;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JMenuItem cutMenuItem;
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem pasteMenuItem;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    // End of variables declaration//GEN-END:variables

    private int logOutMethod() {
        Login login;
        try {
            this.dispose();
            dispose();
            login = new Login();
            login.setVisible(true);
        } catch (AppException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        return 0;
    }

}
