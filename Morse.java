import java.util.*;
import java.io.*;
import javax.sound.sampled.*;

public class Morse {

	        private static final int DOT = 200, DASH = DOT * 3, FREQ = 800;

	String[]  AlphabetArray = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
            "s", "t", "u", "v", "w", "x", "y", "z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"," "};

	 String[] MorseCodeArray = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..",
            "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", ".----",
            "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.", "-----","/"}; 

    public String encode(String enCode){
    	String MorseCodeInput = enCode.toLowerCase();
    	String Encode = "";

  for (int i=0; i<MorseCodeInput.length(); i++) {
    for (int j=0; j<AlphabetArray.length; j++) {
      if (String.valueOf(MorseCodeInput.charAt(i)).equals(AlphabetArray[j])) {
       	Encode += MorseCodeArray[j] + " ";
    						}
    				}
			}
  			return Encode;
		}
	public String decode(String deCode){
		String calc = "";
		String[] Decode = deCode.split(" ");
			for(String decode : Decode){
				for(int i = 0; i < MorseCodeArray.length;i++){
					if(MorseCodeArray[i].equals(decode))
					{
						calc += AlphabetArray[i];
					}
				}
			}
			System.out.println(calc);
			return calc;
		}
		public void BeepAudio(String text) throws IOException, LineUnavailableException, InterruptedException {
        String audio = encode(text);
        boolean sound = true;
        for (char note : audio.toCharArray()) {
            System.out.print(note == ' ' ? " " : note);
            if (sound) {
                try (SourceDataLine sdl = AudioSystem.getSourceDataLine(new AudioFormat(9000F, 8, 1, true, false))) { 
                    sdl.open(sdl.getFormat());
                    sdl.start();
                    for (int i = 0; i < (note == '.' ? DOT : DASH) * 8; i++) {
                        sdl.write(new byte[]{(byte) (Math.sin(i / (8000F / FREQ) * 2.0 * Math.PI) * 127.0)}, 0, 1);
                    }
                    sdl.drain();
                }
            }
            Thread.sleep(DOT / 5);
        }
    }
	}
