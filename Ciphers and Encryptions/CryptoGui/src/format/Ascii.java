package format;

/**
 * Created by bird on 5/9/2016.
 */
public class Ascii extends Format{
    @Override
    public String decode(String s) {
        char[] c = s.toCharArray();
        StringBuilder hex = new StringBuilder();
        for (int i = 0; i < c.length; i++) {
            hex.append(Integer.toHexString((int) c[i]));
        }
        return hex.toString();
    }

    @Override
    public String encode(String s) {
        StringBuilder ascii = new StringBuilder();
        for (int i = 0; i < s.length() / 2; i++) {
            int decimal = Integer.parseInt(s.substring(i * 2, (i + 1) * 2), 16);
            ascii.append((char) decimal);
        }
        return ascii.toString();
    }

    public String toString() {
        return "Ascii";
    }
}

//TODO: note to self, binary will fail when input is not breakable into hex i.e. 6 bits will crash
//same with ascii.
