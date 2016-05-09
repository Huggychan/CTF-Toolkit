package format;

/**
 * Created by bird on 5/8/2016.
 */
public abstract class Format {
    /**
     * Decode from said format
     * @param s String in format type
     * @return Always hex string
     */
    public abstract String decode(String s);

    /**
     * Encode into said format
     * @param s Always hex string
     * @return Formatted in said format
     */
    public abstract String encode(String s);
}
