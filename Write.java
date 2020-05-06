import java.beans.XMLEncoder;
import java.io.*;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.nio.file.*;
import static java.nio.file.StandardCopyOption.*;
import java.net.*;


public class Write {
	private PrivateKey privateKey;
    private PublicKey publicKey;

    private static String Path = "keys\\\\";

    KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
    KeyPair keyPair = keyPairGen.genKeyPair();
    RSAPrivateCrtKey privKey = (RSAPrivateCrtKey) keyPair.getPrivate();
    RSAPublicKey pubKey = (RSAPublicKey) keyPair.getPublic();
	

    public Write() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(1024);
        KeyPair pair = keyGen.generateKeyPair();
        this.privateKey = pair.getPrivate();
        this.publicKey = pair.getPublic();
    }

    public void writeToFile(String path, byte[] key) throws IOException {
        File f = new File(path);
        f.getParentFile().mkdirs();
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(key);
        fos.flush();
        fos.close();
    }

    private static void copyFile(File source, File dest) throws IOException {
    Files.copy(source.toPath(), dest.toPath());
}

    public static Boolean FileExists(String user, String path, String type){
        File tempFile = new File("" + path + user + type + "");
        boolean exists = tempFile.exists();
        return exists;
    }   
	byte[] encryptData(String data) throws IOException {
		byte[] dataToEncrypt = data.getBytes();
		byte[] encryptedData = null;
		try {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		encryptedData = cipher.doFinal(dataToEncrypt);	
		} catch ( NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
											IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		return encryptedData;
	}

	public void Write(String name,String message,String file) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException,
	    															InvalidKeyException, IllegalBlockSizeException, BadPaddingException {	
	    	String enkodimiBaze64UTF8 = Base64.getEncoder()
                    .encodeToString(name.getBytes(StandardCharsets.UTF_8.toString()));
    	
	    	SecureRandom sr = new SecureRandom(); 
	    	byte [] bajtaRandomIV = new byte[8];  
	    	sr.nextBytes(bajtaRandomIV); 		  
	    	String iv = bajtaRandomIV.toString();
			String enkodimiBase64 = Base64.getEncoder()
                    .encodeToString(iv.getBytes(StandardCharsets.UTF_8.toString()));
	    	
	    	Metodat rsaObj = new Metodat();
    		byte [] bajtaRandomKEY = new byte[8]; 
    		sr.nextBytes(bajtaRandomKEY); 	      
    		String KEY = bajtaRandomKEY.toString();
			byte[] encryptedData = rsaObj.encryptData(KEY);
			String rsa = encryptedData.toString();
			String enkodimiBase64RSA = Base64.getEncoder()
                    .encodeToString(rsa.getBytes(StandardCharsets.UTF_8.toString()));
			
			SecretKey key = KeyGenerator.getInstance("DES").generateKey();
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key);			
			String enkodimiBase64DES = Base64.getEncoder()
                    .encodeToString(message.getBytes(StandardCharsets.UTF_8.toString()));
	
			String ciphertext = enkodimiBaze64UTF8 + "." + enkodimiBase64 + "." + enkodimiBase64RSA + "." + enkodimiBase64DES;
			
			if(file.endsWith(".txt")) {
	    		FileOutputStream ruajFile = new FileOutputStream(new File("keys\\\\" + file));
				XMLEncoder encoder = new XMLEncoder(ruajFile);
				encoder.writeObject(ciphertext);
				encoder.close();
				ruajFile.close();
				System.out.println("Mesazhi i enkriptuar u ruajt ne fajllin '" + file + "'");
			}
			else
				System.out.println(ciphertext);
    	}
	    
	    public void Write(String name,String message) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException,
	    													InvalidKeyException, IllegalBlockSizeException, BadPaddingException {	
	    	Boolean existsPublik = FileExists(name, Path, ".pub.xml");
			if(existsPublik){
				String enkodimiBaze64UTF8 = Base64.getEncoder()
	                    .encodeToString(name.getBytes(StandardCharsets.UTF_8.toString()));
	    	
		    	SecureRandom sr = new SecureRandom();
		    	byte [] bajtaRandomIV = new byte[8];  
		    	sr.nextBytes(bajtaRandomIV); 		  
		    	String iv = bajtaRandomIV.toString();
				String enkodimiBase64 = Base64.getEncoder()
	                    .encodeToString(iv.getBytes(StandardCharsets.UTF_8.toString()));
		    	
		    	Metodat rsaObj = new Metodat();
	    		byte [] bajtaRandomKEY = new byte[8]; 
	    		sr.nextBytes(bajtaRandomKEY); 	      
	    		String KEY = bajtaRandomKEY.toString();
				byte[] encryptedData = rsaObj.encryptData(KEY);
				String rsa = encryptedData.toString();
				String enkodimiBase64RSA = Base64.getEncoder()
	                    .encodeToString(rsa.getBytes(StandardCharsets.UTF_8.toString()));
				
				SecretKey key = KeyGenerator.getInstance("DES").generateKey();
				Cipher cipher = Cipher.getInstance("DES");
				cipher.init(Cipher.ENCRYPT_MODE, key);			
				String enkodimiBase64DES = Base64.getEncoder()
	                    .encodeToString(message.getBytes(StandardCharsets.UTF_8.toString()));
		
				String ciphertext = enkodimiBaze64UTF8 + "." + enkodimiBase64 + "." + enkodimiBase64RSA + "." + enkodimiBase64DES;
				System.out.println(ciphertext);
			}
			else{
				System.out.println("Gabim: Celesi publik '" + name + "' nuk ekziston.");
			}
    	}
}
