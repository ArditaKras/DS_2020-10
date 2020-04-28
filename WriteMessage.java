

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;


public class WriteMessage {
	private static Base64.Encoder encoder = Base64.getEncoder();
		public static void Write(String name, String message) 
		{
			String emri2 = "keys/" + name + ".pub.xml";
		    Path path = Paths.get(emri2);
		    
		    try {
		    	String tosend = create(name,message);
				Files.write(path, tosend.getBytes());
				System.out.println(tosend);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		public static void Write1(String name, String message,String file) 
		{
			String emri2 = "keys/" + file;
		    Path path = Paths.get(emri2);
		    
		    try {
		    	String tosend = create(name,message);
				Files.write(path, tosend.getBytes());
				System.out.println("Celesi u ruajt ne  fajllin '"+file + "'.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		public static String create(String name, String message) throws UnsupportedEncodingException {
			
			try {
				// Encode   base64(utf8(<name>)) 
				String asB64 = Base64.getEncoder().encodeToString(name.getBytes("utf-8"));
				String first = asB64+".";
				 // Output will be: c29tZSBzdHJpbmc=
				
				// encode base64(<iv>) 
				SecureRandom sr = new SecureRandom(); //create new secure random
				byte [] iv = new byte[8]; //create an array of 8 bytes 
				sr.nextBytes(iv); //create random bytes to be used for the IV (?) Not too sure.
				IvParameterSpec IV = new IvParameterSpec(iv); //creating the IV 
				String asB65 = Base64.getEncoder().encodeToString(iv);
				String second = asB65+".";
				
				// encode base64(rsa(<key>))
				final int keySize = 512;
				KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			    keyPairGenerator.initialize(keySize);
			    KeyPair keyPair = keyPairGenerator.genKeyPair();
			    String k = encoder.encodeToString(keyPair.getPublic().getEncoded());
			    String s = k.substring(0,20);
			    String third= s + ".";
			       
				
				// encode base64(des(<message>))
				boolean base64 = true;
				byte[] array = new byte[8]; // length is bounded by 7
			    new Random().nextBytes(array);
				String key = array.toString();
				String plainText = message;

				SecretKeyFactory MyKeyFactory = SecretKeyFactory.getInstance("DES");
				byte[] keyBytes = key.getBytes();

				DESKeySpec generatedKeySpec = new DESKeySpec(keyBytes);
				SecretKey generatedSecretKey = MyKeyFactory.generateSecret(generatedKeySpec);

				Cipher generatedCipher = Cipher.getInstance("DES");
				generatedCipher.init(Cipher.ENCRYPT_MODE, generatedSecretKey);

				byte[] messsageStringBytes = plainText.getBytes();
				byte[] encryptedMessage = generatedCipher.doFinal(messsageStringBytes);

				String encryptedMessageString = new String(encryptedMessage);

				if (base64) {
				    encryptedMessageString = Base64.getEncoder().encodeToString(encryptedMessageString.getBytes("utf-8"));
				}
				String fourth = encryptedMessageString;
					
				return first+second+third+"\n"+fourth;
			
			} catch (Exception e) {
				// TODO: handle exception
				
			}
		
			return "";
			
		}		
		
}
