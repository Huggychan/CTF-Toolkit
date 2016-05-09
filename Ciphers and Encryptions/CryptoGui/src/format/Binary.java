package format;

/**
 * Created by bird on 5/8/2016.
 */
public class Binary extends Format {
    @Override
    public String decode(String s) {
        String returnVal = "";
        for (int i = 0; i < s.length() / 4; i++) {
            int decimal = Integer.parseInt(s.substring(i * 4, (i + 1) * 4 - 1));
            returnVal += Integer.toHexString(decimal);
        }
        return returnVal;
    }

    @Override
    public String encode(String s) {
        String bin = "";
        for (int i = 0; i < s.length(); i++) {
            bin += String.format("%04s", s.charAt(i));
        }
        return bin;
    }
}
