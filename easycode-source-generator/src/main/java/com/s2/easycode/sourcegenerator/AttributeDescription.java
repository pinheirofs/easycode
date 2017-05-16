package com.s2.easycode.sourcegenerator;

public class AttributeDescription {

    private String name;
    private AttributeType type;

    public AttributeDescription() {
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setType(final AttributeType type) {
        this.type = type;
    }

    public AttributeType getType() {
        return type;
    }

}
