

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


public class Import { 

	private PrivateKey privateKey;
    private PublicKey publicKey;

    private static String Path = "C:\\\\Users\\\\User\\\\Desktop\\\\Ds-2020-Gr10\\\\keys\\\\";

    KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
    KeyPair keyPair = keyPairGen.genKeyPair();
    RSAPrivateCrtKey privKey = (RSAPrivateCrtKey) keyPair.getPrivate();
    RSAPublicKey pubKey = (RSAPublicKey) keyPair.getPublic();
	
	Base64.Encoder encoder = Base64.getEncoder();
	    
    BigInteger n = ((RSAKey) privKey).getModulus();
    BigInteger e = ((RSAPrivateCrtKey) privKey).getPublicExponent();
    BigInteger p = ((RSAPrivateCrtKey) privKey).getPrimeP();
    BigInteger q = ((RSAPrivateCrtKey) privKey).getPrimeQ();
    BigInteger dp = ((RSAPrivateCrtKey) privKey).getPrimeExponentP();
    BigInteger dq = ((RSAPrivateCrtKey) privKey).getPrimeExponentQ();
    BigInteger inverseQ = ((RSAPrivateCrtKey) privKey).getCrtCoefficient();
    BigInteger d = ((RSAPrivateKey) privKey).getPrivateExponent();
        
	String N = encoder.encodeToString(n.toByteArray());
	String E = encoder.encodeToString(e.toByteArray());
	String P = encoder.encodeToString(p.toByteArray());
	String Q = encoder.encodeToString(q.toByteArray());
	String DP = encoder.encodeToString(dp.toByteArray());
	String DQ = encoder.encodeToString(dq.toByteArray());
	String INVERSEQ = encoder.encodeToString(inverseQ.toByteArray());
	String D = encoder.encodeToString(d.toByteArray());

    public Import() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(1024);
        KeyPair pair = keyGen.generateKeyPair();
        this.privateKey = pair.getPrivate();
        this.publicKey = pair.getPublic();
    }



    private static void copyFile(File source, File destinacioni) throws IOException {
    Files.copy(source.toPath(), destinacioni.toPath());
}

    public static Boolean FileExists(String user, String path, String type){
        File tempFile = new File("" + path + user + type + "");
        boolean exists = tempFile.exists();
        return exists;
    }   

    public void Import(String name, String path) throws IOException {
		    	Boolean existsPublik = FileExists(name, Path, ".pub.xml");
				Boolean existsPrivat = FileExists(name, Path, ".xml");
				String emriFajllit = path.split("\\\\")[10];	
				if(!existsPublik && emriFajllit.endsWith(".pub.xml")) {
					Path temp = Files.move 
					        (Paths.get(path),  
					        Paths.get("C:\\Users\\User\\Desktop\\Ds-2020-Gr10\\keys\\" + emriFajllit)); 
					  
					        if(temp != null) 
					        { 
					            System.out.println("Celesi publik u ruajt ne fajllin 'keys/" + name + ".pub.xml'."); 
					        } 
					        else
					        { 
					            System.out.println("Deshtoj bartja e fajllit"); 
					        } 
				}
				else if(!existsPrivat && emriFajllit.endsWith(".xml")) {
					Path temp = Files.move 
					        (Paths.get(path),  
					        Paths.get("C:\\Users\\User\\Desktop\\Ds-2020-Gr10\\keys\\" + emriFajllit)); 
							Person celsiPublik = new Person(N,E);
							FileOutputStream ruajCelsinPublik = new FileOutputStream(new File(Path + name + ".pub.xml"));
							XMLEncoder enkoderiCelsitPublik = new XMLEncoder(ruajCelsinPublik);
							enkoderiCelsitPublik.writeObject(celsiPublik);
							enkoderiCelsitPublik.close();
							ruajCelsinPublik.close();
							
					        if(temp != null) 
					        { 
					            System.out.println("Celesi privat u ruajt ne fajllin 'keys/" + name + ".xml'."); 
					            System.out.println("Celesi publik u ruajt ne fajllin 'keys/" + name + ".pub.xml'."); 
					        } 
					        else
					        { 
					            System.out.println("Deshtoj bartja e fajllit"); 
					        } 
				}
				else if(existsPublik) {
					System.out.println("Gabim: Celesi '" + name + "' ekziston paraprakisht.");
				}
				else {
					System.out.println("Gabim: Fajlli i dhene nuk eshte celes valid");
				}
		    }
		}
