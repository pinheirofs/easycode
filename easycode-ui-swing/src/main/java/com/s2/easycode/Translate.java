package com.s2.easycode;

import java.util.ResourceBundle;

public class Translate {

    private static Translate instance;
    private final ResourceBundle bundle;

    private Translate() {
        bundle = ResourceBundle.getBundle("translate");
    }

    public static Translate getInstance() {
        if (instance == null) {
            instance = new Translate();
        }

        return instance;
    }

    public String tr(final String property) {
        return bundle.getString(property);
    }
}
