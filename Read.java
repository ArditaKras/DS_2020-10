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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	public static void ReturnMessage1(String marrsi, String mesazhi, String derguesi, String nenshkrimi) throws UnsupportedEncodingException, IOException, SQLException{
	    byte[] dekodimiCelsit = Base64.getDecoder().decode(marrsi);
        String Marrsi = new String(dekodimiCelsit, StandardCharsets.UTF_8.name());	
        byte[] dekodimiMesazhit = Base64.getDecoder().decode(mesazhi);
        String Mesazhi = new String(dekodimiMesazhit, StandardCharsets.UTF_8.name());
        byte[] dekodimiCelsit1 = Base64.getDecoder().decode(derguesi);
        String Derguesi = new String(dekodimiCelsit1, StandardCharsets.UTF_8.name());	
        byte[] dekodimiMesazhit1 = Base64.getDecoder().decode(nenshkrimi);
        String Nenshkrimi = new String(dekodimiMesazhit1, StandardCharsets.UTF_8.name());
        
        Boolean exist = FileExists(Marrsi, Path, ".xml");
    if(exist) {
        System.out.println("Marresi: " + Marrsi);
        System.out.println("Mesazhi: " + Mesazhi);
        System.out.println("Derguesi: " + Derguesi);
        System.out.println("Nenshkrimi: ");
        validate(Nenshkrimi);
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
			 
	 public void Read_Message1(String encryptedMessage) throws UnsupportedEncodingException, IOException, SQLException {
			if(!encryptedMessage.endsWith(".txt")){
					String marrsi = encryptedMessage.split("\\.", 0)[0];
					String mesazhi = encryptedMessage.split("\\.", 0)[3];
					String derguesi = encryptedMessage.split("\\.", 0)[4];
		            String nenshkrimi = encryptedMessage.split("\\.", 0)[5];
		            ReturnMessage1(marrsi, mesazhi, derguesi, nenshkrimi);
			}
			else{ 
					String contents = Files.lines(Paths.get("keys\\\\" +
								encryptedMessage)).collect(Collectors.joining("\n"));
					contents = contents.split("<string>")[1].split("</string>")[0];
					String marrsi = contents.split("\\.", 0)[0];
		            String mesazhi = contents.split("\\.", 0)[3];
					String derguesi = contents.split("\\.", 0)[4];
		            String nenshkrimi = contents.split("\\.", 0)[5];
		            ReturnMessage1(marrsi, mesazhi, derguesi, nenshkrimi);
			}}			
			 
	 public static void validate(String token) throws SQLException {
		 
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3307/eclipse","root","balajendrit");
	        Statement st = connection.createStatement();
	        String sql = ("SELECT * FROM student;");
	        ResultSet rs = st.executeQuery(sql);
	        for(int i=0;i<50;i++)
	        {
		        if(rs.next()) { 
		        	if(rs.getString("THE_TOKEN")==null)
		        		continue;
		        	else {
		        		String str1 = rs.getString("THE_TOKEN");
		        		long str3 = rs.getLong("ONTIME");
				         if (str1.equals(token)) {
				        	 
				        	String isval = TokenGenerator.YesNo(str3);
				        	System.out.println(isval);
						} else {
							continue;
						}
					}      
		        }
	        }  
		}	 
}
