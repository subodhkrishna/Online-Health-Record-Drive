package Client;
import Server.*;
import java.io.*;
import java.util.*;

class FileList 
{
	Vector<String> v=new Vector<String>();
	public Vector folders(String us1)
	{
		 File folder = new File(System.getProperty("user.dir")+"/"+us1);
		 File[] listOfFiles = folder.listFiles();
		 System.out.println(listOfFiles.length);
	     for (int i = 0; i < listOfFiles.length; i++)
		 {
			 if (listOfFiles[i].isFile()) 
			 {
				  v.add(listOfFiles[i].getName());
	         } 
		     else if (listOfFiles[i].isDirectory())
		     {
				//System.out.println("Directory " + listOfFiles[i].getName());
	         }
			
		}
		return v;
	}
	
}