package gui.view;

import ExceptionPackage.AppException;
import bl.LogsRepository;
import bl.MessageInterface;
import bl.MessageRepository;
import bl.StaffInterface;
import bl.StaffRepository;
import ejb.Message;
import ejb.Staff;
import gui.model.MessageTableModel;
import gui.model.StaffTableModel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

public class Messages extends javax.swing.JInternalFrame {

    StaffInterface staffIr;
    MessageInterface messageIr;
    EntityManager entityManager;
    MessageTableModel messageTM;
    StaffTableModel staffTM;
    Staff currentUser;
    List<Staff> staffList;
    MainFrame mainFrame;
    
    public Messages(EntityManager entityManager, Staff currentUser,MainFrame mainFrame) {
        initComponents();
        this.entityManager = entityManager;
        this.currentUser = currentUser;
        this.mainFrame=mainFrame;
        this.setLocation(220, 10);
        this.setPreferredSize(new Dimension(1100, 654));
        initInterfaces(entityManager);
        tableRowsColors();
        initTableModels();
        messageTblListeners();
        staffList = staffIr.findAllWithoutAdministrator();
        fillComboWithStaff(staffList);
    }
    
    public void tableRowsColors(){
    messageTbl.setDefaultRenderer(Object.class, new DefaultTableCellRenderer()
{
    private Color green=new Color(204,255,204);
    private Color darkGreen=new Color(215,255,215);
    
    private Color red=new Color(255,40,40);
    private Color darkRed=new Color(255,80,80);
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if(messageTbl.getModel()==messageTM){
            c.setBackground(messageTM.getMessage(row).getSeen().equals("Yes") ? (row%2==0) ? green :darkGreen 
                     : (row%2==0) ? red : darkRed);
        }else 
            c.setBackground(
                    messageIr.countUnseenMessagesForSpecificUser(currentUser,staffTM.getStaff(row)) == 0 
                            ? (row%2==0) ? green :darkGreen 
                     : (row%2==0) ? red : darkRed);
        
        return c;
    }
});
    }

    public void initTableModels() {
        String[] messageTblColumns = {"Sender", "Message", "Time", "Date"};
        messageTM = new MessageTableModel(messageTblColumns);
        String[] staffTblColumns = {"Username", "Name", "Surname","Unseen"};
        staffTM = new StaffTableModel(staffTblColumns,entityManager,currentUser);
    }

    /*{
        
    ListSelectionModel rowSM=tbl.getSelectionModel();
    rowSM.addListSelectionListener (new ListSelectionListener(){
    @Override
    public void valueChanged (ListSelectionEvent e){
        if(tbl.getModel()==ptm)
        {
    if(e.getValueIsAdjusting())
        return;
    ListSelectionModel rowSM = (ListSelectionModel)e.getSource();
    int selectedIndex = rowSM.getMinSelectionIndex();
    if(selectedIndex>-1){
        
        Participant p=ptm.getParticipant(selectedIndex);
        idnumtxtf.setText(p.getIDNumber()+"");
        emritxtf.setText(p.getName());
        mbiemritxtf.setText(p.getSurname());
    }
    }
    }
    });
    }*/
    public void initInterfaces(EntityManager entityManager) {
        staffIr = new StaffRepository(entityManager);
        messageIr = new MessageRepository(entityManager);
    }

    public synchronized void staffTableLoad() {
        staffTM.add(staffIr.findAllWithoutAdministrator());
        if (!staffTM.isEmpty()) {
            messageTbl.setModel(staffTM);
            staffTM.fireTableDataChanged();
        }
    }

    public void messageTableLoad() {
        messageTM.add(messageIr.findByReciever(currentUser));
        if (!messageTM.isEmpty()) {
            messageTbl.setModel(messageTM);
            messageTM.fireTableDataChanged();
        }
    }

    public void messageTableLoad(Staff currentStaff, Staff sender) throws AppException {
        messageTM.add(messageIr.findBySenderAndReciever(currentStaff, sender));
        if (messageTM.isEmpty()) {
            throw new AppException("You haven't recieved a message from this user yet.");
        }
        messageTbl.setModel(messageTM);
        messageTM.fireTableDataChanged();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backgroundPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        staffCombo = new javax.swing.JComboBox<>();
        sendToLbl = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        recievedFromLbl = new javax.swing.JLabel();
        recieverLbl = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        messageTxtf = new javax.swing.JTextArea();
        sendBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        messageLbl = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        messageTbl = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setTitle("Messages");
        setMaximumSize(new java.awt.Dimension(1100, 654));
        setMinimumSize(new java.awt.Dimension(1100, 654));
        setPreferredSize(new java.awt.Dimension(1100, 654));

        backgroundPanel.setBackground(new java.awt.Color(102, 102, 102));

        jPanel3.setOpaque(false);

        jPanel1.setOpaque(false);

        staffCombo.setToolTipText("");

        sendToLbl.setForeground(new java.awt.Color(255, 255, 255));
        sendToLbl.setText("Send to :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(sendToLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                .addComponent(staffCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(118, 118, 118))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(staffCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sendToLbl))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.setOpaque(false);

        recievedFromLbl.setForeground(new java.awt.Color(255, 255, 255));

        recieverLbl.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(recievedFromLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(recieverLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(recievedFromLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 14, Short.MAX_VALUE)
                    .addComponent(recieverLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        messageTxtf.setBackground(new java.awt.Color(204, 255, 204));
        messageTxtf.setColumns(20);
        messageTxtf.setLineWrap(true);
        messageTxtf.setRows(5);
        messageTxtf.setWrapStyleWord(true);
        jScrollPane2.setViewportView(messageTxtf);

        sendBtn.setBackground(new java.awt.Color(0, 153, 102));
        sendBtn.setForeground(new java.awt.Color(204, 255, 204));
        sendBtn.setText("Send");
        sendBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendBtnActionPerformed(evt);
            }
        });

        clearBtn.setBackground(new java.awt.Color(0, 153, 102));
        clearBtn.setForeground(new java.awt.Color(204, 255, 204));
        clearBtn.setText("Clear");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        messageLbl.setForeground(new java.awt.Color(255, 255, 255));
        messageLbl.setText("Message :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(sendBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
                .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(messageLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(messageLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sendBtn)
                    .addComponent(clearBtn)))
        );

        messageTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        messageTbl.setShowHorizontalLines(false);
        messageTbl.setShowVerticalLines(false);
        jScrollPane3.setViewportView(messageTbl);

        jButton1.setBackground(new java.awt.Color(0, 153, 102));
        jButton1.setForeground(new java.awt.Color(204, 255, 204));
        jButton1.setText("Seen All");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout backgroundPanelLayout = new javax.swing.GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(backgroundPanelLayout);
        backgroundPanelLayout.setHorizontalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        backgroundPanelLayout.setVerticalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(backgroundPanelLayout.createSequentialGroup()
                        .addGap(0, 19, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendBtnActionPerformed
        mainFrame.keepRunning=false;
        try {
            
            validation();
            createMessage();
        } catch (AppException ae) {
            ae.printStackTrace();
            JOptionPane.showMessageDialog(this, ae.getMessage());
        }
        synchronized(mainFrame.messagesThread){
            mainFrame.keepRunning = true;
            mainFrame.messagesThread.notifyAll();
        }
    }//GEN-LAST:event_sendBtnActionPerformed
    private void clearObject() {
        messageTbl.clearSelection();
        messageTxtf.setText("");
        messageTxtf.requestFocus();
    }

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        clearObject();
    }//GEN-LAST:event_clearBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            seenAllMessagesMethod();
        } catch (AppException ex) {
            Logger.getLogger(Messages.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    
    private void seenAllMessagesMethod() throws AppException{
        List<Message>unSeenMessages=messageIr.checkUnseenMessages(currentUser);
        for(int i=0;i<unSeenMessages.size();i++){
            unSeenMessages.get(i).setSeen("Yes");
            messageIr.edit(unSeenMessages.get(i));
        }
        
                staffTableLoad();
    }
    
    
    
    private synchronized void createMessage()throws AppException{
        Message message = new Message(currentUser, staffList.get(staffCombo.getSelectedIndex()), messageTxtf.getText().trim());
            Date date = (new LogsRepository(entityManager).findDate());
            message.setTimeStamp(date);
            messageIr.create(message);
            clearObject();
            JOptionPane.showMessageDialog(this, "The message was sent sucsessfully.");
    }
    
    private void validation() throws AppException {
        if (staffCombo == null) {
            throw new AppException("Select the reciever for this message.");
        }
        if (messageTxtf.getText().trim().isEmpty()) {
            throw new AppException("The message cannot be empty.");
        }
        if (messageTxtf.getText().trim().length() > 500) {
            throw new AppException("The message cannot contain more than 500 letters.");
        }
    }
    
    private void setRowColor(){
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backgroundPanel;
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel messageLbl;
    private javax.swing.JTable messageTbl;
    private javax.swing.JTextArea messageTxtf;
    private javax.swing.JLabel recievedFromLbl;
    private javax.swing.JLabel recieverLbl;
    private javax.swing.JButton sendBtn;
    private javax.swing.JLabel sendToLbl;
    private javax.swing.JComboBox<String> staffCombo;
    // End of variables declaration//GEN-END:variables

    private void fillComboWithStaff(List<Staff> staffList) {
        for (Staff staffMember : staffList) {
            staffCombo.addItem(staffMember.getName() + " " + staffMember.getSurname());

        }
    }

    public void tableMoveKey() {
        ListSelectionModel messageTblLSM = messageTbl.getSelectionModel();
        messageTblLSM.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (messageTbl.getModel() == messageTM) {
                    if (lse.getValueIsAdjusting()) {
                        return;
                    }
                    ListSelectionModel rowSM = (ListSelectionModel) lse.getSource();
                    int selectedIndex = rowSM.getMinSelectionIndex();
                    if (selectedIndex > -1) {
                        Message message = messageTM.getMessage(selectedIndex);
                        messageTxtf.setText(message.getMessage());
                        recievedFromLbl.setText("Recieved from : ");
                        recieverLbl.setText(message.getDoctorID().getName() + " " + message.getDoctorID().getSurname());
                        if (message.getSeen().equals("No")) {
                            try {
                                message.setSeen("Yes");
                                messageIr.edit(message);
                            } catch (AppException ae) {
                                ae.printStackTrace();
                            }
                        }
                    } else {
                        recievedFromLbl.setText("");
                        recieverLbl.setText("");
                    }
                }
            }

        });
    }

    private void messageTblListeners() {
        tableMoveKey();
        clickMoveKey();
    }

    /*
    private void ProcessClickEvent(){
        trainingprocesstbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(trainingprocesstbl.getModel()==trainingptmm){
                    if(e.getClickCount()==1){
                        if(trainingprocesstbl.getSelectedRow()!=-1){
                            JTable target = (JTable)e.getSource();
                            int row = target.getSelectedRow();
                            int column = target.getSelectedColumn();
                            tp=trainingptmm.getTrainingProcess(row);
                            trainingcodetxtf.setText(tp.getTProcessID());
                        }
                    }
                }
            }
        });
    }
     */
    private void clickMoveKey() {
        messageTbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (messageTbl.getModel() == staffTM) {
                    if (e.getButton() == 1) {
                        try {
                            int row = messageTbl.getSelectedRow();
                            messageTableLoad(currentUser,staffTM.getStaff(row));
                        } catch (AppException ae) {
                            JOptionPane.showMessageDialog(null, ae.getMessage());
                        }
                    }
                } else if (e.getButton() == 3) {
                    staffTableLoad();
                }

            }
        });

    }
}
