package com.s2.easycode.mainframe;

import java.util.Arrays;
import java.util.List;

import com.s2.easycode.sourcegenerator.ProjectDescription;
import com.s2.easycode.validator.ErrorType;
import com.s2.easycode.validator.ProjectValidatorService;

public class ProjectValidatorServiceAdapter implements ProjectValidatorService {

    @Override
    public boolean validate(final ProjectDescription projectDescription) {
        return false;
    }

    @Override
    public List<ErrorType> getErrors() {
        return Arrays.asList(ErrorType.PROJECT_PATH_ERROR, ErrorType.PROJECT_NAME_ERROR);
    }

}
