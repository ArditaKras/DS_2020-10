public class Vigenere
{
    public static String encrypt(String Key,String Message) {
        String EMessage = "";
        Message = Message.toLowerCase();
        for (int i = 0, j = 0; i < Message.length(); i++) {
            char letter = Message.charAt(i);
            EMessage += (char)(((letter - 97) + (Key.charAt(j)-97)) % 26 + 97);
            j = ++j % Key.length();
        }
        return EMessage;
    }

    public static String decrypt(String Key,String Message) {
        String DMessage = "";
        Message = Message.toLowerCase();
        for (int i = 0, j = 0; i < Message.length(); i++) {
            char letter = Message.charAt(i);
            DMessage += (char)((letter - Key.charAt(j) + 26) % 26 + 97);
            j = ++j % Key.length();
        }
        return DMessage;
    }
}
