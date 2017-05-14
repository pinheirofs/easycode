package com.s2.easycode.validator;

import java.util.List;

import com.s2.easycode.sourcegenerator.EntityDescription;

public interface EntityValidatorService {

    boolean validate(EntityDescription entityDescription);

    List<ErrorType> getErrors();

}
