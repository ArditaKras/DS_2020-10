
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;


public class Main{
		public static void main(String[] args)throws  ArrayIndexOutOfBoundsException, IOException, LineUnavailableException, InterruptedException{
		

			CreateUser generate;
			
		
			Morse M = new Morse();
		
			
			
	                //how to use arguments in console
			if(args.length > 4 || args.length < 2 || args.length == 0){
				System.out.println("\n\t\tProgrami pranon deri ne 4 argumente jo me shume!\t\t\n");
				System.out.println("~Perdor morse-code encode | decode <text>\n" + "~Perdor tap-code encode | decode <text>\n" + "~Perdor vigenere-code encrypt | decrypt <text>\n");
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
			
			if("create-user".equals(args[0]))
			{
				try {
					
					String emri = args[1];
					String emri1 = "keys/" + emri + ".xml";
					String emri2 = "keys/" + emri + ".pub.xml";
					
					Path path = Paths.get(emri1);
					
					if (Files.exists(path)) {
					  System.out.println("Gabim: Celesi '" + emri + "' egziston paraprakisht. ");
					}
					if (Files.notExists(path)) {
						generate = new CreateUser(1024);
						generate.createKeys();
						generate.writeToFile(emri1 , generate.getPublicKey().getEncoded());
						generate.writeToFile(emri2 , generate.getPrivateKey().getEncoded());
						System.out.println("Eshte krijuar celesi privat '" + emri1 + "'");
						System.out.println("Eshte krijuar celesi publik '" + emri2 + "'");
					}
					
					}
				catch (NoSuchAlgorithmException | NoSuchProviderException e) 
				{
					System.err.println(e.getMessage());
				} 
				catch (IOException e)
				{
					System.err.println(e.getMessage());
				}
			}
			if("delete-user".equals(args[0]))
			{
				String emri = args[1];
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


}
