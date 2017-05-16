package com.s2.easycode.sourcegenerator;

public enum AttributeType {
    INTEGER, LONG, FLOAT, DOUBLE, CHARACTER, STRING, BOOLEAN;

    public static AttributeType convert(final String description) {
        AttributeType type = null;

        if (Integer.class.getCanonicalName().equals(description)) {
            type = AttributeType.INTEGER;
        } else if (Long.class.getCanonicalName().equals(description)) {
            type = AttributeType.LONG;
        } else if (Float.class.getCanonicalName().equals(description)) {
            type = AttributeType.FLOAT;
        } else if (Double.class.getCanonicalName().equals(description)) {
            type = AttributeType.DOUBLE;
        } else if (Character.class.getCanonicalName().equals(description)) {
            type = AttributeType.CHARACTER;
        } else if (String.class.getCanonicalName().equals(description)) {
            type = AttributeType.STRING;
        } else if (Boolean.class.getCanonicalName().equals(description)) {
            type = AttributeType.BOOLEAN;
        }

        return type;
    }
}
