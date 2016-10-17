
package gui.view;

import java.awt.Dimension;
import ejb.Logs;
import bl.LogsInterface;
import bl.LogsRepository;
import gui.model.LogsTableModel;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SeeLogs extends javax.swing.JInternalFrame {

    EntityManager entityManager;
    LogsInterface logsIr;
    LogsTableModel logsTM;
    public SeeLogs(EntityManager entityManager) {
        this.entityManager=entityManager;
        initComponents();
        this.setLocation(220, 10);
        logsIr=new LogsRepository(entityManager);
        String [] columnNamesTableModel={"Username", "Type","Message", "Date"};
        logsTM=new LogsTableModel(columnNamesTableModel);
        
        logsTableLoad();
        logsTableMoveKey();
        
        
        searchTxtf.getDocument().addDocumentListener(new DocumentListener(){
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
        
                public void kerkoparticipant(){
                    logsTableFindByAll(searchTxtf.getText());
                }
          });
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        logsTbl = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        messageTxtf = new javax.swing.JTextField();
        searchTxtf = new javax.swing.JTextField();
        background = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Logs");
        setMaximumSize(new java.awt.Dimension(1100, 654));
        setMinimumSize(new java.awt.Dimension(1100, 654));
        setPreferredSize(new java.awt.Dimension(1100, 654));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(null);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("Message :");
        jLabel2.setToolTipText("");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(10, 90, 150, 25);

        logsTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        logsTbl.setShowHorizontalLines(false);
        logsTbl.setShowVerticalLines(false);
        jScrollPane1.setViewportView(logsTbl);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 133, 1060, 476);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("Search :");
        jLabel3.setToolTipText("");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(10, 30, 150, 25);
        jPanel1.add(messageTxtf);
        messageTxtf.setBounds(170, 90, 750, 30);
        jPanel1.add(searchTxtf);
        searchTxtf.setBounds(170, 28, 220, 30);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 620));
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1084, 624));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public void logsTableLoad(){
        List <Logs> list=logsIr.findAll();
        logsTM.add(list);
        logsTbl.setModel(logsTM);
        logsTM.fireTableDataChanged();
    }
    
    public void logsTableFindByAll(String text){
        List <Logs> list=logsIr.findByAll(text);
        logsTM.add(list);
        logsTbl.setModel(logsTM);
        logsTM.fireTableDataChanged();
    }
    
    public void logsTableMoveKey(){
    ListSelectionModel rowSM=logsTbl.getSelectionModel();
        rowSM.addListSelectionListener (new ListSelectionListener(){
        @Override
        public void valueChanged (ListSelectionEvent e){
            if(e.getValueIsAdjusting())
                return;
            ListSelectionModel rowSM = (ListSelectionModel)e.getSource();
            int selectedIndex = rowSM.getMinSelectionIndex();
                if(selectedIndex>-1){
                        Logs logs=logsTM.getLogs(selectedIndex);
                        messageTxtf.setText(logs.getMessage());
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable logsTbl;
    private javax.swing.JTextField messageTxtf;
    private javax.swing.JTextField searchTxtf;
    // End of variables declaration//GEN-END:variables
}
