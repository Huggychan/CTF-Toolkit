package format;

/**
 * Created by bird on 5/8/2016.
 */
public class Binary extends Format {
    @Override
    public String decode(String s) {
        if (s.length() % 4 != 0) { //if number of things is not divisible by 4. i.e. 1 -> 0001
            s = String.format("%" + (s.length() +(4 - s.length() % 4)) + "s", s).replace(' ', '0');
        }
        StringBuilder returnVal = new StringBuilder();
        for (int i = 0; i < s.length() / 4; i++) {
            int decimal = Integer.parseInt(s.substring(i * 4, (i + 1) * 4), 2);
            returnVal.append(Integer.toHexString(decimal));
        }
        return returnVal.toString();
    }

    @Override
    public String encode(String s) {
        StringBuilder bin = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            String tmp = s.charAt(i) + "";
            int j = Integer.parseInt(tmp, 16);
            bin.append(String.format("%4s",Integer.toBinaryString(j)).replace(' ', '0'));
        }
        return bin.toString();
    }

    public String toString() {
        return "Binary";
    }
}
