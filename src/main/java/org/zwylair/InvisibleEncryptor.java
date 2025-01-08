package org.zwylair;

import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InvisibleEncryptor {
    private static final Logger LOGGER = Logger.getLogger(InvisibleEncryptor.class.getName());
    private static final String CHARACTERS = "\u200a\u200b\u200c\u200f \u206a\u206b\u206c\u206d\u206e\u206f";
    private static final String ENCRYPTED_PATTERN = "^\u2001\u2002([\u200a\u200b\u200c\u200f \u206a\u206b\u206c\u206d\u206e\u206f\\s]*)";

    public static String decode(String str) {
        try {
            String[] parts = str.replaceFirst("^\u2001\u2002", "").split("\u2000");
            byte[] byteArray = new byte[parts.length];
            for (int i = 0; i < parts.length; i++) {
                byteArray[i] = (byte) toNum(parts[i]);
            }
            return new String(byteArray, StandardCharsets.UTF_8);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error decoding string", e);
            return str;
        }
    }

    public static String encode(String str) {
        try {
            byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
            String[] encodedParts = new String[bytes.length];
            for (int i = 0; i < bytes.length; i++) {
                encodedParts[i] = toStr(bytes[i] & 255);
            }
            return "\u2001\u2002" + String.join("\u2000", encodedParts);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error encoding string", e);
            return str;
        }
    }

    public static boolean isEncrypted(String str) {
        return !str.isEmpty() && str.matches(ENCRYPTED_PATTERN);
    }

    private static int toNum(String str) {
        int result = 0;
        int multiplier = 1;
        for (int i = str.length() - 1; i >= 0; i--) {
            result += CHARACTERS.indexOf(str.charAt(i)) * multiplier;
            multiplier *= 11;
        }
        return result;
    }

    private static String toStr(int value) {
        StringBuilder sb = new StringBuilder();
        while (value > 0) {
            sb.append(CHARACTERS.charAt(value % 11));
            value /= 11;
        }
        return sb.reverse().toString();
    }
}
