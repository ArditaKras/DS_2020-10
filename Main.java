import javax.sound.sampled.*;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Main {
  public static void main(String[] args)throws IOException, LineUnavailableException, InterruptedException, 
                            ArrayIndexOutOfBoundsException, NoSuchAlgorithmException, InvalidKeyException, 
                            NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, SQLException {
      Scanner input = new Scanner(System.in);
	  	Morse M = new Morse();
        Create C= new Create();
        Delete D=  new Delete();
        Export E=  new Export();
        Import I=  new Import();
        Write  W=  new Write();
        Read   R=  new Read();
      
        C.writeToFile("RSA/publicKey", C.getPublicKey().getEncoded());
        C.writeToFile("RSA/privateKey", C.getPrivateKey().getEncoded());
        
        if(args.length == 0 || args.length > 5){

           System.out.println("\n\t\tProgrami pranon deri ne 5 argumente!\t\t\n");
                System.out.println("~Perdor morse-code encode | decode <text>\n" + 
                    "~Perdor tap-code encode | decode <text>\n" + 
                    "~Perdor vigenere-code encrypt | decrypt <text>\n");
                System.exit(0);
            }
            
        if("morse-code".equals(args[0])){
                if("encode".equals(args[1]))
                    System.out.println(M.encode(args[2]));
            else if("decode".equals(args[1]))
                M.decode(args[2]);
            else if("beep".equals(args[1]))
                M.BeepAudio(args[2]);
                }
                //tap-code
            if("tap-code".equals(args[0])){
                if("encode".equals(args[1]))
                        Tap.encode(args[2]);
                else if("decode".equals(args[1]))
                        Tap.decode(args[2]);
                }
            //vigenere-code
            if("vigenere-cipher".equals(args[0])){
                if("encrypt".equals(args[1])){
                    String key = args[2];
                    String message = args[3];
                    String encrypted = Vigenere.encrypt(key,message);
                    System.out.println(encrypted);
                        
                }
                else if("decrypt".equals(args[1])){
                    String key = args[2];
                    String message = args[3];
                    String decrypted = Vigenere.decrypt(key,message);
                    System.out.println(decrypted);
                }
                    
            }
        
        // Faza II
        
        if(args[0].equals("create-user")) {
            if(args[1].matches("^[a-zA-Z0-9._]+")) {
            	try {
            		System.out.println("Jep fjalekalimin ");
            		String pasi1 = input.next();
            		 if (Password_Validation(pasi1)) {
            			 System.out.println("Sheno prape ");
                 		 String pasi2 = input.next();
                 		 UserLog.createuser(args[1], pasi1, pasi2);	
            		 }
            		 else {
            			System.out.println("Paswordi duhet te permbaje nje numer ose simbol.");
					}
            		
                }
            	catch (Exception e2) {
					// TODO: handle exception
				}
            	
            }
            else {
                System.out.println("Keni shenuar gabim. Lejohen vetem shkronjat,numrat dhe . _ ] +");
            }
        }
        else if (args[0].equals("login")) {
        	System.out.println("Jepni fjalekalimin");
        	String pasi = input.next();
        	UserLog.loginPass(args[1],pasi);
			
		}
           else if(args[0].equals("delete-user")) {
           try {
               UserLog.deleteUser(args[1]);
		} catch (Exception e2) {
			// TODO: handle exception
		}
        	
            
        }
        else if(args[0].equals("export-key")) {
            if(args.length == 3) {
                E.Export(args[1], args[2]);
            }
            else {
                E.Export(args[1], args[2],args[3]);
            }
        }
         else if(args[0].equals("import-key")) {
                I.Import(args[1], args[2]);

        }
           else if(args[0].equals("write-message")) {
        	 
            if(args.length == 3) {
                W.Write(args[1], args[2]);
            }
            else {
                W.Write(args[1], args[2],args[3]);
                
            }
        }
      
        else if(args[0].equals("read-message")) {
            R.Read_Message(args[1]);
        }
        else {
            System.exit(0);
        }
  
        input.close();
    }
  public static boolean Password_Validation(String password) 
  {

      if(password.length()>=6)
      {
          Pattern digit = Pattern.compile("[0-9]");
          Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
          //Pattern eight = Pattern.compile (".{8}");

             Matcher hasDigit = digit.matcher(password);
             Matcher hasSpecial = special.matcher(password);

             return hasDigit.find() || hasSpecial.find();

      }
      else
          return false;

  }

}
