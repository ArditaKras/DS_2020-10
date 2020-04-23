

import java.io.File;

public class DeleteUser{
	 
    public static void find(String emri)
    {
    	String emri1 = "keys/" + emri + ".xml";
		String emri2 = "keys/" + emri + ".pub.xml";
        
    	File file1 = new File(emri1); 
        File file2 = new File(emri2);
    	if(file1.delete()) 
        { 
            System.out.println("Eshte larguar celesi privat '" + emri1 + "'"); 
        } 
        
        if(file2.delete()) 
        { 
            System.out.println("Eshte larguar celesi publik '" + emri2 + "'"); 
        } 
        else
        { 
            System.out.println("Gabim: Celesi '" + emri + "' nuk egziston."); 
        } 
     
    }
    
}
