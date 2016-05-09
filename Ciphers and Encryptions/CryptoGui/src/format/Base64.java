package format;

/**
 * Created by bird on 5/8/2016.
 */
public class Base64 extends Format{
    @Override
    public String decode(String s) {
        byte[] bytes = s.getBytes();
        byte[] decoded = java.util.Base64.getDecoder().decode(bytes);
        return bytesToHexString(decoded);
    }

    @Override
    public String encode(String s) {
        byte[] bytes = hexStringToByteArray(s);
        byte[] encoded = java.util.Base64.getEncoder().encode(bytes);
        return new String(encoded);
    }

    public String toString() {
        return "Base 64";
    }
}
