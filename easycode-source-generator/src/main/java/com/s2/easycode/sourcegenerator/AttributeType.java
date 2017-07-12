package com.s2.easycode.sourcegenerator;

import org.eclipse.jdt.core.dom.PrimitiveType;

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

    public PrimitiveType.Code getJdtPrimitiveType() {
        PrimitiveType.Code type;

        switch (this) {
        case BOOLEAN:
            type = PrimitiveType.BOOLEAN;
            break;
        case CHARACTER:
            type = PrimitiveType.CHAR;
            break;
        case DOUBLE:
            type = PrimitiveType.DOUBLE;
            break;
        case FLOAT:
            type = PrimitiveType.FLOAT;
            break;
        case INTEGER:
            type = PrimitiveType.INT;
            break;
        case LONG:
            type = PrimitiveType.LONG;
            break;
        default:
            throw new SourceGeneratorException("Invalid primitive type");
        }

        return type;
    }

    public boolean isPrimitiveType() {
        boolean primitiveType;

        switch (this) {
        case BOOLEAN:
        case CHARACTER:
        case DOUBLE:
        case FLOAT:
        case INTEGER:
        case LONG:
            primitiveType = true;
            break;

        default:
            primitiveType = false;
            break;
        }

        return primitiveType;
    }
}
