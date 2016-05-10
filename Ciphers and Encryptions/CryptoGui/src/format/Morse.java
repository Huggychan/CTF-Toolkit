package format;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bird on 5/9/2016.
 */
public class Morse extends Format {

    private static List<String> alpha = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
            "w", "x", "y", "z", "1", "2", "3", "4", "5", "6", "7", "8",
            "9", "0", " ");
    private static List<String> dottie = Arrays.asList(".-", "-...", "-.-.", "-..", ".", "..-.", "--.",
            "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.",
            "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-",
            "-.--", "--..", ".----", "..---", "...--", "....-", ".....",
            "-....", "--...", "---..", "----.", "-----", "/");

    private static final Ascii ascii;
    private static final Map<String, String> fromDots;
    private static final Map<String, String> fromChars;
    static {
        Map<String, String> fromDotsTmp = new HashMap<>();
        Map<String, String> fromCharsTmp = new HashMap<>();
        for (int i = 0; i < alpha.size(); i++) {
            fromDotsTmp.put(dottie.get(i), alpha.get(i));
            fromCharsTmp.put(alpha.get(i), dottie.get(i));
        }
        fromDots = fromDotsTmp;
        fromChars = fromCharsTmp;
        ascii = new Ascii();
    }

    @Override
    public String decode(String s) {
        String[] split = s.split(" ");
        for (int i = 0; i < split.length; i++) {
            split[i] = fromDots.get(split[i]);
        }
        StringBuilder builder = new StringBuilder();
        for (String string : split) {
            builder.append(string);
        }
        String joined = builder.toString();
        return ascii.decode(joined);
    }

    @Override
    public String encode(String s) {
        s = ascii.encode(s);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append(fromChars.get(s.charAt(i) + ""));
            sb.append(" ");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Morse";
    }
}

//TODO: finish this