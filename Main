import java.util.*;
import java.io.*;
import javax.sound.sampled.*;

public class Main{
		public static void main(String[] args)throws  ArrayIndexOutOfBoundsException, IOException, LineUnavailableException, InterruptedException{
		Morse M = new Morse();

		if("morse-code".equals(args[0])){
			if("encode".equals(args[1]))
				System.out.println(M.encode(args[2]));
		else if("decode".equals(args[1]))
			M.decode(args[2]);
		else if("beep".equals(args[1]))
			M.BeepAudio(args[2]);
			}
		}
}
