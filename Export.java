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


public class Export { 

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

    public Export() throws NoSuchAlgorithmException {
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


		public void Export(String publicOrPrivat, String name) throws IOException {	
		    	Boolean existsPrivat = FileExists(name, Path, ".xml");
				Boolean existsPublik = FileExists(name, Path, ".pub.xml");
				if(existsPublik && publicOrPrivat.equals("public") && !name.endsWith(".pub.xml")) {
		    		System.out.println("<RSAKeyValue>");
		    		System.out.println("    <Modulus>" + N + "</Modulus>");
		    		System.out.println("    <Exponent>" + E + "</Exponent>");
		    		System.out.println("</RSAKeyValue>");
		    	}
				else if(!existsPublik && publicOrPrivat.equals("public")) {
		    		System.out.println("Gabim: Celesi publik '" + name + "' nuk ekziston.");
				}
				else if(existsPrivat && publicOrPrivat.equals("private") && !publicOrPrivat.endsWith(".xml")) {
					System.out.println("<RSAKeyValue>");
		    		System.out.println("    <Modulus>" + N + "</Modulus>");
		    		System.out.println("    <Exponent>" + E + "</Exponent>");
		    		System.out.println("    <P>" + P + "</P>");
		    		System.out.println("    <Q>" + Q + "</Q>");
		    		System.out.println("    <DP>" + DP + "</DP>");
		    		System.out.println("    <DQ>" + DQ + "</DQ>");
		    		System.out.println("    <InverseQ>" + INVERSEQ+ "</InverseQ>");
		    		System.out.println("    <D>" + D + "</D>");
		    		System.out.println("</RSAKeyValue>");
				}
				else if(!existsPrivat && publicOrPrivat.equals("private")) {
		    		System.out.println("Gabim: Celesi privat '" + name + "' nuk ekziston.");
				}
		    	else {
		    		System.out.println("Keni shenuar gabim.\nSintaksa: Main export-key <public|private> <name>\n"
	    				+ "Argumenti <public|private> e përcakton llojin e çelësit që eksportohet. \n"
	    				+ "Argumenti <name> e përcakton çelësin e cilit shfrytëzues të eksportohet. \n");
		    	}
		    }
		
		public void Export(String publicOrPrivat, String name, String file) throws IOException {	
			Boolean existsPrivat = FileExists(name, Path, ".xml");
			Boolean existsPublik = FileExists(name, Path, ".pub.xml");
			if(existsPublik && publicOrPrivat.equals("public") && file.endsWith(".pub.xml")) {
				File source = new File("C:\\\\Users\\\\User\\\\Desktop\\\\Ds-2020-Gr10\\\\keys\\\\" + name + ".pub.xml");
				File destination = new File("C:\\\\Users\\\\User\\\\Desktop\\\\Ds-2020-Gr10\\\\keys\\\\" + file);
				copyFile(source, destination);
				System.out.println("Celesi publik u ruajt ne fajllin 'keys/" + file + "'");
			}
			else if(!existsPublik && publicOrPrivat.equals("public")) {
	    		System.out.println("Gabim: Celesi publik '" + name + "' nuk ekziston.");
			}
			else if(existsPrivat && publicOrPrivat.equals("private") && file.endsWith(".xml")) {
				File source = new File("C:\\\\Users\\\\User\\\\Desktop\\\\Ds-2020-Gr10\\\\keys\\\\" + name + ".xml");
				File destination = new File("C:\\\\Users\\\\User\\\\Desktop\\\\Ds-2020-Gr10\\\\keys\\\\" + file);
				copyFile(source, destination);
				System.out.println("Celesi privat u ruajt ne fajllin '" + file + "'.");
			}
			else if(!existsPrivat && publicOrPrivat.equals("private")) {
	    		System.out.println("Gabim: Celesi privat '" + name + "' nuk ekziston.");
			}
	    	else {
	    		System.out.println("Keni shenuar gabim.\nSintaksa: Main export-key <public|private> <name> [file]\n"
	    				+ "Argumenti <public|private> e përcakton llojin e çelësit që eksportohet. \n"
	    				+ "Argumenti <name> e përcakton çelësin e cilit shfrytëzues të eksportohet. \n"
	    				+ "Argumenti opsional [file] e përcakton shtegun e fajllit se ku do të ruhet çelësi i eksportuar.");
	    	}
	    }
	

}