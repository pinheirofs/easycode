package com.s2.easycode.validator;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.s2.easycode.sourcegenerator.ProjectDescription;

public class ProjectValidatorServiceImplTest {

    @Test
    public void validateProjectOk() {
        final ProjectDescription projectDescription = new ProjectDescription();
        projectDescription.setName("Project");
        projectDescription.setPath("/tmp/");

        final ProjectValidatorService service = new ProjectValidatorServiceImpl();
        final boolean validate = service.validate(projectDescription);
        Assert.assertTrue(validate);

        final List<ErrorType> errors = service.getErrors();
        Assert.assertTrue(errors.isEmpty());
    }

    @Test
    public void validateProjectNullNAme() {
        final ProjectDescription projectDescription = new ProjectDescription();
        projectDescription.setName(null);
        projectDescription.setPath("/tmp/");

        final ProjectValidatorService service = new ProjectValidatorServiceImpl();
        final boolean validate = service.validate(projectDescription);
        Assert.assertFalse(validate);

        final List<ErrorType> errors = service.getErrors();
        Assert.assertFalse(errors.isEmpty());
        Assert.assertTrue(errors.contains(ErrorType.PROJECT_NAME_ERROR));
    }

    @Test
    public void validateProjectEmptyNAme() {
        final ProjectDescription projectDescription = new ProjectDescription();
        projectDescription.setName("");
        projectDescription.setPath("/tmp/");

        final ProjectValidatorService service = new ProjectValidatorServiceImpl();
        final boolean validate = service.validate(projectDescription);
        Assert.assertFalse(validate);

        final List<ErrorType> errors = service.getErrors();
        Assert.assertFalse(errors.isEmpty());
        Assert.assertTrue(errors.contains(ErrorType.PROJECT_NAME_ERROR));
    }

    @Test
    public void validateProjectEmptyPath() {
        final ProjectDescription projectDescription = new ProjectDescription();
        projectDescription.setName("Project");
        projectDescription.setPath("");

        final ProjectValidatorService service = new ProjectValidatorServiceImpl();
        final boolean validate = service.validate(projectDescription);
        Assert.assertFalse(validate);

        final List<ErrorType> errors = service.getErrors();
        Assert.assertFalse(errors.isEmpty());
        Assert.assertTrue(errors.contains(ErrorType.PROJECT_PATH_ERROR));
    }

    @Test
    public void validateProjectNullPath() {
        final ProjectDescription projectDescription = new ProjectDescription();
        projectDescription.setName("Project");
        projectDescription.setPath(null);

        final ProjectValidatorService service = new ProjectValidatorServiceImpl();
        final boolean validate = service.validate(projectDescription);
        Assert.assertFalse(validate);

        final List<ErrorType> errors = service.getErrors();
        Assert.assertFalse(errors.isEmpty());
        Assert.assertTrue(errors.contains(ErrorType.PROJECT_PATH_ERROR));
    }

    @Test
    public void validateProjectFilePath() {
        final ProjectDescription projectDescription = new ProjectDescription();
        projectDescription.setName("Project");
        projectDescription.setPath("~/.bash_history");

        final ProjectValidatorService service = new ProjectValidatorServiceImpl();
        final boolean validate = service.validate(projectDescription);
        Assert.assertFalse(validate);

        final List<ErrorType> errors = service.getErrors();
        Assert.assertFalse(errors.isEmpty());
        Assert.assertTrue(errors.contains(ErrorType.PROJECT_PATH_ERROR));
    }

    @Test
    public void validateProjectCantEritePath() {
        final ProjectDescription projectDescription = new ProjectDescription();
        projectDescription.setName("Project");
        projectDescription.setPath("/root/");

        final ProjectValidatorService service = new ProjectValidatorServiceImpl();
        final boolean validate = service.validate(projectDescription);
        Assert.assertFalse(validate);

        final List<ErrorType> errors = service.getErrors();
        Assert.assertFalse(errors.isEmpty());
        Assert.assertTrue(errors.contains(ErrorType.PROJECT_PATH_ERROR));
    }
}
