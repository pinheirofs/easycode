package com.s2.easycode.sourcegenerator;

import java.util.ArrayList;
import java.util.List;

public class EntityDescription {

    private final List<AttributeDescription> attributeDescriptions;
    private String name;

    public EntityDescription() {
        attributeDescriptions = new ArrayList<>();
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void addAttrubute(final String name, final AttributeType type) {
        final AttributeDescription attributeDescription = new AttributeDescription();
        attributeDescription.setName(name);
        attributeDescription.setType(type);

        attributeDescriptions.add(attributeDescription);
    }

    public String getName() {
        return name;
    }

    public List<AttributeDescription> getAttributes() {
        return new ArrayList<>(attributeDescriptions);
    }

}
