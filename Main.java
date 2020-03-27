import java.util.*;
import java.io.*;
import javax.sound.sampled.*;

public class Main{
		public static void main(String[] args)throws  ArrayIndexOutOfBoundsException, IOException, LineUnavailableException, InterruptedException{
		Morse M = new Morse();
		Tap T = new Tap();

		if(args.length > 4 || args.length < 3 || args.length == 0){
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
		if("tap-code".equals(args[0])){
			if("encode".equals(args[1]))
					T.encode(args[2]);
			else if("decode".equals(args[1]))
					T.decode(args[2]);
			}
		}
}
