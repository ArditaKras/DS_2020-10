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


public class Delete {
  

    private static String Path = "keys\\\\";


    private static void copyFile(File source, File destinacioni) throws IOException {
    Files.copy(source.toPath(), destinacioni.toPath());
}

    public static Boolean FileExists(String user, String path, String type){
        File tempFile = new File("" + path + user + type + "");
        boolean exists = tempFile.exists();
        return exists;
    }    

	public void Delete(String name) {
				Boolean existsPrivat = FileExists(name, Path, ".xml");
				Boolean existsPublik = FileExists(name, Path, ".pub.xml");
		    	if(existsPrivat && existsPublik) {
		    		File celsiPublik = new File(Path + name + ".pub.xml");
		    		File celsiPrivat = new File(Path + name + ".xml");
		    		celsiPublik.delete();
		    		celsiPrivat.delete();
		    		System.out.println("Eshte larguar celesi privat 'keys/" + name + ".xml'");
		    		System.out.println("Eshte larguar celesi publik 'keys/" + name + ".pub.xml'");
		    	}
		    	else if(existsPublik) {
		    		File celsiPublik = new File("./" + name + ".pub.xml");
		    		celsiPublik.delete();
		    		System.out.println("Eshte larguar celesi publik 'keys/" + name + ".pub.xml'");
		    	}
		    	else if(existsPrivat) {
		    		File celsiPrivat = new File("./" + name + ".xml");
		    		celsiPrivat.delete();
		    		System.out.println("Eshte larguar celesi privat 'keys/" + name + ".xml'");
		    	}
		    	else {
		    		System.out.println("Gabim: Celesi '" + name + "' nuk ekziston.");
		    	}	
		    }
		





}
