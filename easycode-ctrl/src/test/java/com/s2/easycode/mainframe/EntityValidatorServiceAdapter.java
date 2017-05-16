package com.s2.easycode.mainframe;

import java.util.Arrays;
import java.util.List;

import com.s2.easycode.sourcegenerator.EntityDescription;
import com.s2.easycode.validator.EntityValidatorService;
import com.s2.easycode.validator.ErrorType;

public class EntityValidatorServiceAdapter implements EntityValidatorService {

    @Override
    public boolean validate(final EntityDescription entityDescription) {
        return false;
    }

    @Override
    public List<ErrorType> getErrors() {
        return Arrays.asList(ErrorType.ENTITY_ATTRIBUTE_ERROR, ErrorType.ENTITY_CLASS_NAME_ERROR);
    }

}
