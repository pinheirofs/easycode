package com.s2.easycode.validator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.s2.easycode.sourcegenerator.ProjectDescription;

public class ProjectValidatorServiceImpl implements ProjectValidatorService {

    private final List<ErrorType> errors;

    public ProjectValidatorServiceImpl() {
        errors = new ArrayList<>();
    }

    @Override
    public boolean validate(final ProjectDescription projectDescription) {
        boolean valid = true;

        final String name = projectDescription.getName();
        if (name == null || name.isEmpty()) {
            errors.add(ErrorType.PROJECT_NAME_ERROR);
            valid = false;
        }

        final String group = projectDescription.getGroup();
        if (group == null || group.isEmpty()) {
            errors.add(ErrorType.PROJECT_GROUP_ERROR);
            valid = false;
        }

        final String path = projectDescription.getPath();
        if (path == null || path.isEmpty()) {
            errors.add(ErrorType.PROJECT_PATH_ERROR);
            valid = false;
        } else {
            final File filepath = new File(path);
            if (filepath.isFile() || !filepath.canWrite()) {
                errors.add(ErrorType.PROJECT_PATH_ERROR);
                valid = false;
            }
        }

        return valid;
    }

    @Override
    public List<ErrorType> getErrors() {
        return new ArrayList<>(errors);
    }

}
