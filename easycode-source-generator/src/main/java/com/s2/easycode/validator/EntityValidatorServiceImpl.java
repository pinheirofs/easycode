package com.s2.easycode.validator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.s2.easycode.sourcegenerator.AttributeDescription;
import com.s2.easycode.sourcegenerator.AttributeType;
import com.s2.easycode.sourcegenerator.EntityDescription;

public class EntityValidatorServiceImpl implements EntityValidatorService {

    private final List<ErrorType> errors = new LinkedList<>();

    @Override
    public boolean validate(final EntityDescription entityDescription) {
        final String entityName = entityDescription.getName();
        if (entityName == null || entityName.isEmpty()) {
            errors.add(ErrorType.ENTITY_CLASS_NAME_ERROR);
            return false;
        }

        final List<AttributeDescription> attributes = entityDescription.getAttributes();
        for (final AttributeDescription attribute : attributes) {
            final String attributeName = attribute.getName();
            if (attributeName == null || attributeName.isEmpty()) {
                errors.add(ErrorType.ENTITY_ATTRIBUTE_NAME_ERROR);
                return false;
            }

            final AttributeType type = attribute.getType();
            if (type == null) {
                errors.add(ErrorType.ENTITY_ATTRIBUTE_TYPE_ERROR);
                return false;
            }
        }

        return true;
    }

    @Override
    public List<ErrorType> getErrors() {
        return new ArrayList<>(errors);
    }

}