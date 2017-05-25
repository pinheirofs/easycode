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

    public String getJavaType() {
        String javaType;
        switch (this) {
        case INTEGER:
            javaType = Integer.class.getName();
            break;
        case LONG:
            javaType = Long.class.getName();
            break;
        case FLOAT:
            javaType = Float.class.getName();
            break;
        case DOUBLE:
            javaType = Double.class.getName();
            break;
        case CHARACTER:
            javaType = Character.class.getName();
            break;
        case STRING:
            javaType = String.class.getName();
            break;
        case BOOLEAN:
            javaType = Boolean.class.getName();
        default:
            javaType = null;
            break;
        }

        return javaType;
    }
}
