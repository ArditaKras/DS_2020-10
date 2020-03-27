public class Tap{
	
	static String[] characters = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
            "s", "t", "u", "v", "w", "x", "y", "z"," "};
	static String[] tap_code = { ". .", ". ..", ". ...", ". ....", ". .....", ".. .", ".. ..", ".. ...", ".. ....", ".. .....", "... .", "... ..",
            "... ...", "... ....", "... .....", ".... .", ".... ..", ".... ...", ".... ....", ".... .....", "..... .", "..... ..", "..... ...", "..... ....", "..... .....", " "};



public static String encode(String eCode) {
        String builder = "";
        eCode= eCode.toLowerCase();
        String[] words = eCode.trim().split("");

        for (String word : words) {
            for (int i = 0; i < tap_code.length; i++) {
            	if(characters[i].equals(word)){
	               builder += tap_code[i] + "  "; 
		            }	
            	}
        	}
        	System.out.println(builder);
        	return builder;
    }
   public static String decode(String dCode){
   		String builder_decode  = "";
   		String[] letter = dCode.trim().split("  ");
   		for(String letters : letter){ 
   			for(int i = 0; i < tap_code.length; i++){
   				if(tap_code[i].equals(letters)) { 
   					builder_decode += characters[i];
   				}
   			}
   		}
   		System.out.println(builder_decode);
   		return builder_decode.toString();

   }

}
