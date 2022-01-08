package model.slot;

public class TextTags {
    public final static char BOLD = '\u24B7';
    public final static char ITALIC = '\u24BE';
    public final static char UNDERLINE = '\u24CA';

    public static String stripOfTags(String original) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < original.length(); i++) {
            char c = original.charAt(i);
            if (c != TextTags.BOLD && c != TextTags.ITALIC && c != TextTags.UNDERLINE) sb.append(c);
        }
        return sb.toString();
    }
}
