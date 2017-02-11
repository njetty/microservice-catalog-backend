package com.p632.catalog.service;

/**
 * Created by naveenjetty on 2/11/17.
 */
public final class StringTestUtil {

    private static final String CHARACTER = "A";

    private StringTestUtil() {}

    public static String createStringWithLength(int length) {
        StringBuilder builder = new StringBuilder();
        for (int index = 0; index < length; index++) {
            builder.append(CHARACTER);
        }
        return builder.toString();
    }
}
