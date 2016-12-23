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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author selvarani
 */
public class Download extends javax.swing.JFrame implements Runnable{
 String us, ip,op="";
    int port;
    String p;
    String url,downfile="";
    int  i=0;  String exten ="";
    ResultSet rs;
 DB Db=new DB();
    /**
     * Creates new form Download
     */
    public Download() {
        initComponents();
            this.setTitle(us+"-DOWNLOAD");
        
    }
 Download(String user1,String p1, String ip1,int port1,String op1){
        //To change body of generated methods, choose Tools | Templates.
         p = p;
        us = user1;
        System.out.println(us);
        port = port1;
        ip = ip1;
        op=op1;
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
          //setOpacity(0.5f);
 initComponents();
    this.setTitle(us+"-DOWNLOAD");
 try {

            Thread th = new Thread(this);
            th.start();

        } catch (Exception eee) {
            System.out.println("ONe");
        }

                // Display the window.
                setVisible(true);
            }

 public void run() {
        try {
            
          ServerSocket ss1=new ServerSocket(port);
          System.out.println("Server at: "+port);
         
          while(true)
          {
               
              Socket s1=ss1.accept();
               System.out.println("Accepted at: "+port);
              DataInputStream dis=new DataInputStream(s1.getInputStream());
    String fs="";
    fs=dis.readUTF();
    System.out.println("$$$$"+fs);
    if(fs.compareToIgnoreCase("Yes")==0)
    { 
        File F=new  File(System.getProperty("user.dir") + "/" +us+ "/" + downfile);
        if(F.exists())
        {
             int answer = JOptionPane.showConfirmDialog(null, "File Already Exist you can OverWrite");
                            if (answer == JOptionPane.YES_OPTION) {
        
               int filesize=6022386; // filesize temporary hardcoded

    long start = System.currentTimeMillis();
    int bytesRead;
    int current = 0;
                byte [] mybytearray  = new byte [filesize];
    InputStream is = s1.getInputStream();
    FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + "/" +us+ "/" + downfile);
    BufferedOutputStream bos = new BufferedOutputStream(fos);
    bytesRead = is.read(mybytearray,0,mybytearray.length);
    current = bytesRead;

    // thanks to A. Cádiz for the bug fix
    do {
       bytesRead =
          is.read(mybytearray, current, (mybytearray.length-current));
       if(bytesRead >= 0) current += bytesRead;
    } while(bytesRead > -1);

    bos.write(mybytearray, 0 , current);
    bos.flush();
    long end = System.currentTimeMillis();
    System.out.println(end-start);
    bos.close();
     System.out.println("^^^");
      File f=new File(System.getProperty("user.dir") + "/" +us+ "/" + downfile);
      Db.Insert("insert into upfile values('"+us+"','"+downfile+"','"+f.length()+" Bytes"+"','"+exten+"')");
      Db.Insert("update req set st='Sent' where fn='"+downfile+"' and port="+port+"");
  JOptionPane.showMessageDialog(null,"'"+downfile+"' is Successfully Downloaded" ,"WARNING", JOptionPane.WARNING_MESSAGE);
    s1.close();
    }
        }
        else
        {
            int filesize=6022386; // filesize temporary hardcoded

    long start = System.currentTimeMillis();
    int bytesRead;
    int current = 0;
                byte [] mybytearray  = new byte [filesize];
    InputStream is = s1.getInputStream();
    FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + "/" +us+ "/" + downfile);
    BufferedOutputStream bos = new BufferedOutputStream(fos);
    bytesRead = is.read(mybytearray,0,mybytearray.length);
    current = bytesRead;

    // thanks to A. Cádiz for the bug fix
    do {
       bytesRead =
          is.read(mybytearray, current, (mybytearray.length-current));
       if(bytesRead >= 0) current += bytesRead;
    } while(bytesRead > -1);

    bos.write(mybytearray, 0 , current);
    bos.flush();
    long end = System.currentTimeMillis();
    System.out.println(end-start);
    bos.close();
     System.out.println("^^^");
      File f=new File(System.getProperty("user.dir") + "/" +us+ "/" + downfile);
      Db.Insert("insert into upfile values('"+us+"','"+downfile+"','"+f.length()+" Bytes"+"','"+exten+"')");
        Db.Insert("update req set st='Sent' where fn='"+downfile+"' and port="+port+"");
  JOptionPane.showMessageDialog(null,"'"+downfile+"' is Successfully Downloaded" ,"WARNING", JOptionPane.WARNING_MESSAGE);
    s1.close();
        }
    }
    else 
    {
        if(op.compareToIgnoreCase("Server")==0)
         JOptionPane.showMessageDialog(null,dis.readUTF() ,"ERROR", JOptionPane.ERROR_MESSAGE);
        else
        {
             JOptionPane.showMessageDialog(null,dis.readUTF() ,"WARNING", JOptionPane.WARNING_MESSAGE);
             
        }
      s1.close();  
    }
          }
        } catch (Exception rtt) {
        }
    }

/**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DOWNLOAD");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jList1.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        jList1.setForeground(new java.awt.Color(204, 51, 0));
        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Text Files(*.txt)", "Image Files(*.jpg)", "MsWord Files(*.doc)", "Pdf Files(*.pdf)", "PowerPoint Files(*.ppt)", "Gif Files(*.gif)", "Aiv Files(*.avi)", "MPG Files(*.mpg)", "All Files(*.*)" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 100, 212, 184));

        jList2.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList2ValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jList2);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(279, 100, 220, 184));

        jButton1.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 51, 0));
        jButton1.setText("DOWNLOAD");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 310, -1, -1));

        jButton2.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 51, 0));
        jButton2.setText("VIEW REQUESTS");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 310, 140, -1));

        jButton3.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 51, 0));
        jButton3.setText("CLOSE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 350, -1, -1));

        jLabel1.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("An Adaptive Cloud Downloading Service");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 19, -1, -1));

        jLabel2.setFont(new java.awt.Font("Georgia", 1, 12)); // NOI18N
        jLabel2.setText("SERVER FILES TYPE");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, -1, -1));

        jLabel3.setFont(new java.awt.Font("Georgia", 1, 12)); // NOI18N
        jLabel3.setText("SERVER  FILES");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 70, -1, -1));

        jButton5.setFont(new java.awt.Font("Georgia", 1, 11)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 51, 0));
        jButton5.setText("DOWNLOAD DETAILS");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 170, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Server/images/Red-Abstract-Background-640x400.jpg"))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 530, 390));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jList1MouseClicked

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

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
     System.out.println("\njList1_valueChanged(ListSelectionEvent e) called.");
            if (!evt.getValueIsAdjusting()) {
                Object o = jList1.getSelectedValue();
                System.out.println(o.toString() + " is selected.");
i=0;exten ="";
       String ext = o.toString();

                   i = ext.length();
                    System.out.println("Selected file length is :  " + i);
                     exten = ext.substring(i - 4, i - 1);
                    System.out.println("Selected file Extension is :  " + exten);

Vector file =new  Vector();String q="";
                    System.out.println("Selected file Extension is :  " + exten);
                    if(exten.compareTo("*.*")==0)
                    {
                        q="select distinct(filname) from upfile where node='system'";
                    }
                    else
                         q="select distinct(filname) from upfile where ext='"+exten+"' and node='system'";
                    ResultSet rs=Db.Select(q);
                      System.out.println(q);
            try {
                while(rs.next())
                {
                file.add(rs.getString(1));
                }
            } catch (SQLException ex) {
                Logger.getLogger(Download.class.getName()).log(Level.SEVERE, null, ex);
            }
                System.out.println("Client received the files from the server");
                jList2.setListData(file);


            }

downfile="";
    }//GEN-LAST:event_jList1ValueChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     try {
         if(downfile.compareToIgnoreCase("")!=0)
{

         String ip1="";
         ResultSet rs=Db.Select("Select ip from registration where username='system'");
         if(rs.next())
         {
             ip1=rs.getString("ip");
         }
            rs.close();
         Socket soc=new Socket(ip1,100);
          DataOutputStream in=new DataOutputStream(soc.getOutputStream());
          in.writeUTF(ip);
         in.writeInt(port);
         in.writeUTF(downfile);
         in.writeUTF(op);
         
}
         else
             JOptionPane.showMessageDialog(null, "You Have not Choosen any file to Download", "Error", JOptionPane.ERROR_MESSAGE);
     } catch (UnknownHostException ex) {
         Logger.getLogger(Download.class.getName()).log(Level.SEVERE, null, ex);
     } catch (IOException ex) {
         Logger.getLogger(Download.class.getName()).log(Level.SEVERE, null, ex);
     } catch (SQLException ex) {
         Logger.getLogger(Download.class.getName()).log(Level.SEVERE, null, ex);
     }
       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
      DownloadDetails d=new DownloadDetails(us,p,ip,port,"Server");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      
        try {        
            ViewRequests d=new ViewRequests(us,p,ip,port,"Server");
        } catch (Exception ex) {
            Logger.getLogger(COptions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
      try {
                    String q1 = "UPDATE Registration SET ip='',status='OFF' WHERE  username='"+us+"'";
                    Db.Insert(q1);
                    Db.Insert("UPDATE nodedetail SET status='OFF' WHERE node='" + us + "'");
                                      //setVisible(false);
                //Login l = new Login("a");
                System.exit(0);
                
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
                Download tw = new Download();

                // Set the window to 55% opaque (45% translucent).
          tw.setOpacity(0.5f);

                // Display the window.
                tw.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
