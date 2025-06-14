package com.example.urlShortener.Utils;

import static java.lang.String.join;

public class Util {

    public static String logBuilder(String... args) {
        return join(", ", args);
    }
}
