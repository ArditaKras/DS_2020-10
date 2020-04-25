
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class WriteMessage {
		
		public static void Write(String emri2, String message)
		{
			
			String fileContent = message;
		     
		    Path path = Paths.get(emri2);
		    
		    try {
				Files.write(path, fileContent.getBytes());
				System.out.println(fileContent.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
}
