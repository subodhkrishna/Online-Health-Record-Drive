package Client;


import Connection.DB;
import Server.SOptions;
import Server.ServerLogin;
import java.awt.GraphicsDevice;
import static java.awt.GraphicsDevice.WindowTranslucency.TRANSLUCENT;
import java.awt.GraphicsEnvironment;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import Client.FileList;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author selvarani
 */
public class DownloadDetails extends javax.swing.JFrame {
 String us, ip,op="";
    int port;
    String p;
    String url,downfile="";
    int  i=0;  String exten ="";
    ResultSet rs;
     Vector<String> ff = new Vector<String>();
    FileList fl;
 DB Db=new DB();
    /**
     * Creates new form Download
     */
    public DownloadDetails() {
        initComponents();
        
    }
 DownloadDetails(String user1,String p1, String ip1,int port1,String op1){
        //To change body of generated methods, choose Tools | Templates.
         p = p;
        us = user1;
        System.out.println(us);
        port = port1;
        ip = ip1;
        op=op1;
           fl = new FileList();
        ff = fl.folders(us);
        GraphicsEnvironment ge = 
            GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();

        //If translucent windows aren't supported, exit.
        if (!gd.isWindowTranslucencySupported(TRANSLUCENT)) {
            System.err.println(
                "Translucency is not supported");
                System.exit(0);
        }
        
        JFrame.setDefaultLookAndFeelDecorated(true);

        // Create the GUI on the event-dispatching thread
       
               // SOptions tw = new SOptions();
                

                // Set the window to 55% opaque (45% translucent).
         // setOpacity(0.5f);
 initComponents();
  this.setTitle(us+"-DOWNLOAD DETAILS");
 jList2.setListData(ff);
                // Display the window.
                setVisible(true);
            }

 

/**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DOWNLOADED DETAILS");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jList2.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList2ValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jList2);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 220, 184));

        jButton1.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 51, 0));
        jButton1.setText("DELETE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 300, -1, -1));

        jButton3.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 51, 0));
        jButton3.setText("CLOSE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 300, -1, -1));

        jLabel1.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("An Adaptive Cloud Downloading Service");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 19, -1, -1));

        jLabel3.setFont(new java.awt.Font("Georgia", 1, 12)); // NOI18N
        jLabel3.setText("YOUR DOWNLOADED FILES");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Server/images/Red-Abstract-Background-640x400.jpg"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 530, 370));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jList2ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList2ValueChanged
        if (!evt.getValueIsAdjusting()) {
            System.out.println("Came here");
            Object o = jList2.getSelectedValue();
            if(o!=null)
            {
            System.out.println(o.toString() + " is selected.");
            downfile = o.toString();
             System.out.println(downfile);
        }

        }

    }//GEN-LAST:event_jList2ValueChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     try {
         boolean st=false;
         if(downfile.compareToIgnoreCase("")!=0)
{ 
    File file = new File(System.getProperty("user.dir") + "/" + us+"/" + downfile);

        if(file.delete()){
            
                         st=true;     
                         Db.Insert("delete from upfile where filname='"+downfile+"' and node='"+us+"'" );
        }
        else{
    			 JOptionPane.showMessageDialog(null, "'"+downfile+"' is Deleteion Failed", "Error", JOptionPane.ERROR_MESSAGE);
    		}
        if(st==true)
        { ff.clear();
                                    ff = fl.folders(us);
                                    jList2.removeAll();
                                    jList2.setListData(ff);
            
    			 JOptionPane.showMessageDialog(null, "'"+downfile+"' is Deleted Succesfully", "Information", JOptionPane.INFORMATION_MESSAGE);
                         downfile="";
    		}
}
         else
             JOptionPane.showMessageDialog(null, "You Have not Choosen any file to Download", "Error", JOptionPane.ERROR_MESSAGE);
     } catch (Exception ex) {
         Logger.getLogger(DownloadDetails.class.getName()).log(Level.SEVERE, null, ex);
     }
       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
      try {
                                      setVisible(false);
                } catch (Exception ex2) {
                    ex2.printStackTrace();
                }
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       GraphicsEnvironment ge = 
            GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();

        //If translucent windows aren't supported, exit.
        if (!gd.isWindowTranslucencySupported(TRANSLUCENT)) {
            System.err.println(
                "Translucency is not supported");
                System.exit(0);
        }
        
        JFrame.setDefaultLookAndFeelDecorated(true);

        // Create the GUI on the event-dispatching thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                DownloadDetails tw = new DownloadDetails();

                // Set the window to 55% opaque (45% translucent).
          tw.setOpacity(0.5f);

                // Display the window.
                tw.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList jList2;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
