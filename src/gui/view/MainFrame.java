
package gui.view;

import ExceptionPackage.AppException;
import ejb.Staff;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class MainFrame extends javax.swing.JFrame {
    
    String pozita="";
    
    public MainFrame(Staff staff) {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        initComponents();
        this.pozita=pozita;
        setPrioritys();
    }
    
    public void addAddAppointment(){
        try {if(desktopPane.getSelectedFrame()!=null){
            desktopPane.getSelectedFrame().setClosed(true);
        }
        } catch (PropertyVetoException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     AddAppointment addAppointment= new AddAppointment();
     desktopPane.add(addAppointment);
     addAppointment.show();
    }
    
    public void addAddNotification(){
        try {if(desktopPane.getSelectedFrame()!=null){
            desktopPane.getSelectedFrame().setClosed(true);
        }
        } catch (PropertyVetoException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     AddNotification addNotification= new AddNotification();
     desktopPane.add(addNotification);
     addNotification.show();
    }
    
    public void addAddPatient(){
        try {if(desktopPane.getSelectedFrame()!=null){
            desktopPane.getSelectedFrame().setClosed(true);
        }
        } catch (PropertyVetoException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     AddPatient addPatient= new AddPatient();
     desktopPane.add(addPatient);
     addPatient.show();
    }
    
    public void addAddUsers(){
        try {if(desktopPane.getSelectedFrame()!=null){
            desktopPane.getSelectedFrame().setClosed(true);
        }
        } catch (PropertyVetoException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     AddUsers addUsers= new AddUsers();
     desktopPane.add(addUsers);
     addUsers.show();
    }
    
    public void addCreateAnalysis(){
        try {if(desktopPane.getSelectedFrame()!=null){
            desktopPane.getSelectedFrame().setClosed(true);
        }
        } catch (PropertyVetoException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     CreateAnalysis createAnalysis= new CreateAnalysis();
     desktopPane.add(createAnalysis);
     createAnalysis.show();
    }
    
    public void addCreateDoctorVisit(){
        try {if(desktopPane.getSelectedFrame()!=null){
            desktopPane.getSelectedFrame().setClosed(true);
        }
        } catch (PropertyVetoException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     CreateDoctorVisit createDoctorVisit= new CreateDoctorVisit();
     desktopPane.add(createDoctorVisit);
     createDoctorVisit.show();
    }
    
    public void addExportReports(){
        try {if(desktopPane.getSelectedFrame()!=null){
            desktopPane.getSelectedFrame().setClosed(true);
        }
        } catch (PropertyVetoException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     ExportReports exportReports= new ExportReports();
     desktopPane.add(exportReports);
     exportReports.show();
    }
    
    public void addLogs(){
        try {if(desktopPane.getSelectedFrame()!=null){
            desktopPane.getSelectedFrame().setClosed(true);
        }
        } catch (PropertyVetoException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     Logs logs= new Logs();
     desktopPane.add(logs);
     logs.show();
    }
    
    public void addMessage(){
        try {if(desktopPane.getSelectedFrame()!=null){
            desktopPane.getSelectedFrame().setClosed(true);
        }
        } catch (PropertyVetoException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     Message message= new Message();
     desktopPane.add(message);
     message.show();
    }
    
    public void addPrintReports(){
        try {if(desktopPane.getSelectedFrame()!=null){
            desktopPane.getSelectedFrame().setClosed(true);
        }
        } catch (PropertyVetoException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     PrintReports printReports= new PrintReports();
     desktopPane.add(printReports);
     printReports.show();
    }
    
    public void addSearch(){
        try {if(desktopPane.getSelectedFrame()!=null){
            desktopPane.getSelectedFrame().setClosed(true);
        }
        } catch (PropertyVetoException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     Search search= new Search();
     desktopPane.add(search);
     search.show();
    }
    
    public void addSeeAppointments(){
        try {if(desktopPane.getSelectedFrame()!=null){
            desktopPane.getSelectedFrame().setClosed(true);
        }
        } catch (PropertyVetoException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     SeeAppointments seeAppointments= new SeeAppointments();
     desktopPane.add(seeAppointments);
     seeAppointments.show();
    }
    
    public void addSeeNotifications(){
        try {if(desktopPane.getSelectedFrame()!=null){
            desktopPane.getSelectedFrame().setClosed(true);
        }
        } catch (PropertyVetoException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     SeeNotifications seeNotifications= new SeeNotifications();
     desktopPane.add(seeNotifications);
     seeNotifications.show();
    }
    
    public void addSeeReports(){
        try {if(desktopPane.getSelectedFrame()!=null){
            desktopPane.getSelectedFrame().setClosed(true);
        }
        } catch (PropertyVetoException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     SeeReports seeReports= new SeeReports();
     desktopPane.add(seeReports);
     seeReports.show();
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
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
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
        setPreferredSize(new java.awt.Dimension(1365, 700));

        jButton1.setBackground(new java.awt.Color(51, 204, 0));
        jButton1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        desktopPane.add(jButton1);
        jButton1.setBounds(0, 0, 200, 50);

        jButton2.setBackground(new java.awt.Color(51, 204, 0));
        jButton2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        desktopPane.add(jButton2);
        jButton2.setBounds(0, 50, 200, 50);

        jButton3.setBackground(new java.awt.Color(51, 204, 0));
        jButton3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        desktopPane.add(jButton3);
        jButton3.setBounds(0, 100, 200, 50);

        jButton4.setBackground(new java.awt.Color(51, 204, 0));
        jButton4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        desktopPane.add(jButton4);
        jButton4.setBounds(0, 150, 200, 50);

        jButton5.setBackground(new java.awt.Color(51, 204, 0));
        jButton5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("jButton5");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        desktopPane.add(jButton5);
        jButton5.setBounds(0, 200, 200, 50);

        jButton6.setBackground(new java.awt.Color(51, 204, 0));
        jButton6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("jButton6");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        desktopPane.add(jButton6);
        jButton6.setBounds(0, 250, 200, 50);

        jButton7.setBackground(new java.awt.Color(51, 204, 0));
        jButton7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("jButton7");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        desktopPane.add(jButton7);
        jButton7.setBounds(0, 300, 200, 50);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/MiLkRLgia.png"))); // NOI18N
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
    
    public void setPrioritys(){
        if(pozita.equals("Recepsion")){
            jButton1.setText("Add Patient");
            jButton2.setText("Add Notification");
            jButton3.setText("Add Appointments");
            jButton4.setText("Add Reports");
            jButton5.setText("Notifications");
            jButton6.setText("Add Patient");
            jButton6.setVisible(false);
            jButton7.setVisible(false);
        }
        if(pozita.equals("Doctor")){
            jButton1.setText("See Notifications");
            jButton2.setText("See Appointments");
            jButton3.setText("Create Doctor Visit");
            jButton4.setText("Create Message");
            jButton5.setText("Search");
            jButton6.setText("Print Report");
            jButton7.setText("Add Patient");
        }
        if(pozita.equals("Director")){
            jButton1.setText("See Reports");
            jButton2.setText("Logs");
            jButton3.setText("Export Reports");
            jButton4.setVisible(false);
            jButton5.setVisible(false);
            jButton6.setVisible(false);
            jButton7.setVisible(false);
        }
        if(pozita.equals("Administrator")){
            jButton1.setText("Add User");
            jButton2.setVisible(false);
            jButton3.setVisible(false);
            jButton4.setVisible(false);
            jButton5.setVisible(false);
            jButton6.setVisible(false);
            jButton7.setVisible(false);
        }
    }
    
    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        Login login;
        try {
            dispose();
            login = new Login();
            login.setVisible(true);
        } catch (AppException ex) {
            JOptionPane.showMessageDialog(this,ex.getMessage());
        }
       
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(pozita.equals("Recepsion")){
            addAddPatient();
        }
        else if(pozita.equals("Doctor")){
            addSeeNotifications();
        }
        else if(pozita.equals("Director")){
            addSeeReports();
        }
        else if(pozita.equals("Administrator")){
            addAddUsers();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       if(pozita.equals("Recepsion")){
            addAddNotification();
        }
       else if(pozita.equals("Doctor")){
            addSeeAppointments();
        }
       else if(pozita.equals("Director")){
            addLogs();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(pozita.equals("Recepsion")){
            addAddAppointment();
        }
        else if(pozita.equals("Doctor")){
            addCreateDoctorVisit();
        }
        else if(pozita.equals("Director")){
            addExportReports();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if(pozita.equals("Recepsion")){
            addPrintReports();
        }
        else if(pozita.equals("Doctor")){
            addMessage();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if(pozita.equals("Recepsion")){
            addMessage();
        }
        else if(pozita.equals("Doctor")){
            addSearch();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if(pozita.equals("Doctor")){
            addPrintReports();
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if(pozita.equals("Doctor")){
            addAddPatient();
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JLabel background;
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

}
