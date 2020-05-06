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

public class Read {

    private PrivateKey privateKey;
    private PublicKey publicKey;

    private static String Path = "keys\\\\";


    public Read() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(1024);
        KeyPair pair = keyGen.generateKeyPair();
        this.privateKey = pair.getPrivate();
        this.publicKey = pair.getPublic();
    }



    private static void copyFile(File source, File dest) throws IOException {
    Files.copy(source.toPath(), dest.toPath());
}

    public static Boolean FileExists(String user, String path, String type){
        File tempFile = new File("" + path + user + type + "");
        boolean exists = tempFile.exists();
        return exists;
    }   

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }
   

	public static void ReturnMessage(String marrsi, String mesazhi) throws UnsupportedEncodingException, IOException{
				    byte[] dekodimiCelsit = Base64.getDecoder().decode(marrsi);
			        String Marrsi = new String(dekodimiCelsit, StandardCharsets.UTF_8.name());	
			        byte[] dekodimiMesazhit = Base64.getDecoder().decode(mesazhi);
			        String Mesazhi = new String(dekodimiMesazhit, StandardCharsets.UTF_8.name());
			        Boolean exist = FileExists(Marrsi, Path, ".xml");
			    if(exist) {
			        System.out.println("Marresi: " + Marrsi);
			        System.out.println("Mesazhi: " + Mesazhi);
			     	}
			    else{
			     	System.out.println("Gabim: Celesi privat 'keys/" + Marrsi +".xml' nuk ekziston");
			    }
			}

			 public void Read_Message(String encryptedMessage) throws UnsupportedEncodingException, IOException {
			if(!encryptedMessage.endsWith(".txt")){
					String marrsi = encryptedMessage.split("\\.", 0)[0];
		            String mesazhi = encryptedMessage.split("\\.", 0)[3];
		            ReturnMessage(marrsi,mesazhi);
			}
			else{ 
					String contents = Files.lines(Paths.get("keys\\\\" +
								encryptedMessage)).collect(Collectors.joining("\n"));
					contents = contents.split("<string>")[1].split("</string>")[0];
					String marrsi = contents.split("\\.", 0)[0];
		            String mesazhi = contents.split("\\.", 0)[3];
		            ReturnMessage(marrsi,mesazhi);
			}}

	


}