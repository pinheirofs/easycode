package com.s2.easycode.validator;

import java.util.List;

import com.s2.easycode.sourcegenerator.ProjectDescription;

public interface ProjectValidatorService {

    boolean validate(ProjectDescription projectDescription);

    List<ErrorType> getErrors();

}
