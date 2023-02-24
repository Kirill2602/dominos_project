package ru.dominospizza.helpers;

import java.util.Base64;

public class StringDecoderHelper {
    public static String decoder(String str) {
        byte[] decodedBytes = Base64.getDecoder().decode(str);
        return new String(decodedBytes);
    }
}
