/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Connection.DB;
import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author selvarani
 */
public class Streaming {
   
    Streaming(String us,String ip1,int port,String fn,String op) throws UnknownHostException, IOException, InterruptedException, SQLException
    {
        System.out.println("coming to streaming");
       Socket s2=new Socket(ip1,port);
               
System.out.println("Socket Created"+port);
        
System.out.println("Send file"+fn);


                File myFile = new File (System.getProperty("user.dir") + "/Uploads" + "/" + fn);
                if(myFile.exists())
                {
                           DataOutputStream dos=new DataOutputStream(s2.getOutputStream());   
                     dos.writeUTF("Yes");
      byte [] mybytearray  = new byte [(int)myFile.length()];
      FileInputStream fis = new FileInputStream(myFile);
      BufferedInputStream bis = new BufferedInputStream(fis);
      bis.read(mybytearray,0,mybytearray.length);
      OutputStream os = s2.getOutputStream();
      System.out.println("Sending...");
      os.write(mybytearray,0,mybytearray.length);
      os.flush();
       bis.close();
      s2.close();
 System.out.println("Socket Closed.Send Successful"+fn);
    Thread.sleep(5000);
    
                }
                else
                {
                    boolean st=false;
                     DataOutputStream dos=new DataOutputStream(s2.getOutputStream());   
                    dos.writeUTF("No");
                    if(op.compareToIgnoreCase("Server")==0)
                     dos.writeUTF("The Requested File is not in Server Cache.So Your Request is Blocked");
                    else
                    {
                        String us1="";
                   
                     DB Db=new DB();
                     ResultSet rs=Db.Select("select distinct(node) from upfile where filname='"+fn+"' and node!='"+us+"'");
                     while(rs.next())
                     {
                     us1=rs.getString("node");
                      int i= Db.Insert("insert into req values('"+us1+"','"+ip1+"','"+port+"','"+fn+"',0,'Not')");
                      if(i<0)
                       st=true;
                     }
                     if(st==false)
                     {
                   
                        dos.writeUTF("The Requested File is not in Server Cache.So Your Request is Forwarded to Other Peer.Please wait"); 
                          dos.close();
                     }
                     else
                     {
                        dos.writeUTF("The Requested File is not in Server Cache and Othe Peers also.Please Try Again Later"); 
                          dos.close(); 
                     }
                    }
                    s2.close();
                      System.out.println("Socket Closed.not Send Successful"+fn);
                    Thread.sleep(5000);
                }


       System.out.println("Socket Created in port: " +port);
    }
}
