package format;

/**
 * Created by bird on 5/8/2016.
 */
public class Binary extends Format {
    @Override
    public String encode(String s) {
        int decimal = Integer.parseInt(s, 2);
        return Integer.toString(decimal, 16);
    }

    @Override
    public String decode(String s) {
        int hex = Integer.parseInt(s, 16);
        return Integer.toString(hex, 2);
    }
}
