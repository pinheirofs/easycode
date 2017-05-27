package com.s2.easycode.validator;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.s2.easycode.sourcegenerator.ProjectDescription;

public class ProjectValidatorServiceImplTest {

    private static final String GROUP_NAME = "group";
    private static final String PROJECT_NAME = "project";

    @Test
    public void validateProjectOk() {
        final ProjectDescription projectDescription = new ProjectDescription();
        projectDescription.setName(PROJECT_NAME);
        projectDescription.setGroup(GROUP_NAME);
        projectDescription.setPath("/tmp/");

        final ProjectValidatorService service = new ProjectValidatorServiceImpl();
        final boolean validate = service.validate(projectDescription);
        Assert.assertTrue(validate);

        final List<ErrorType> errors = service.getErrors();
        Assert.assertTrue(errors.isEmpty());
    }

    @Test
    public void validateProjectNullName() {
        final ProjectDescription projectDescription = new ProjectDescription();
        projectDescription.setName(null);
        projectDescription.setGroup(GROUP_NAME);
        projectDescription.setPath("/tmp/");

        final ProjectValidatorService service = new ProjectValidatorServiceImpl();
        final boolean validate = service.validate(projectDescription);
        Assert.assertFalse(validate);

        final List<ErrorType> errors = service.getErrors();
        Assert.assertFalse(errors.isEmpty());
        Assert.assertEquals(1, errors.size());
        Assert.assertTrue(errors.contains(ErrorType.PROJECT_NAME_ERROR));
    }

    @Test
    public void validateProjectEmptyName() {
        final ProjectDescription projectDescription = new ProjectDescription();
        projectDescription.setName("");
        projectDescription.setGroup(GROUP_NAME);
        projectDescription.setPath("/tmp/");

        final ProjectValidatorService service = new ProjectValidatorServiceImpl();
        final boolean validate = service.validate(projectDescription);
        Assert.assertFalse(validate);

        final List<ErrorType> errors = service.getErrors();
        Assert.assertFalse(errors.isEmpty());
        Assert.assertEquals(1, errors.size());
        Assert.assertTrue(errors.contains(ErrorType.PROJECT_NAME_ERROR));
    }

    @Test
    public void validateProjectNullGroup() {
        final ProjectDescription projectDescription = new ProjectDescription();
        projectDescription.setName("project");
        projectDescription.setGroup(null);
        projectDescription.setPath("/tmp/");

        final ProjectValidatorService service = new ProjectValidatorServiceImpl();
        final boolean validate = service.validate(projectDescription);
        Assert.assertFalse(validate);

        final List<ErrorType> errors = service.getErrors();
        Assert.assertFalse(errors.isEmpty());
        Assert.assertEquals(1, errors.size());
        Assert.assertTrue(errors.contains(ErrorType.PROJECT_GROUP_ERROR));
    }

    @Test
    public void validateProjectEmptyGroup() {
        final ProjectDescription projectDescription = new ProjectDescription();
        projectDescription.setName("project");
        projectDescription.setGroup("");
        projectDescription.setPath("/tmp/");

        final ProjectValidatorService service = new ProjectValidatorServiceImpl();
        final boolean validate = service.validate(projectDescription);
        Assert.assertFalse(validate);

        final List<ErrorType> errors = service.getErrors();
        Assert.assertFalse(errors.isEmpty());
        Assert.assertEquals(1, errors.size());
        Assert.assertTrue(errors.contains(ErrorType.PROJECT_GROUP_ERROR));
    }

    @Test
    public void validateProjectEmptyPath() {
        final ProjectDescription projectDescription = new ProjectDescription();
        projectDescription.setName(PROJECT_NAME);
        projectDescription.setGroup(GROUP_NAME);
        projectDescription.setPath("");

        final ProjectValidatorService service = new ProjectValidatorServiceImpl();
        final boolean validate = service.validate(projectDescription);
        Assert.assertFalse(validate);

        final List<ErrorType> errors = service.getErrors();
        Assert.assertFalse(errors.isEmpty());
        Assert.assertEquals(1, errors.size());
        Assert.assertTrue(errors.contains(ErrorType.PROJECT_PATH_ERROR));
    }

    @Test
    public void validateProjectNullPath() {
        final ProjectDescription projectDescription = new ProjectDescription();
        projectDescription.setName(PROJECT_NAME);
        projectDescription.setGroup(GROUP_NAME);
        projectDescription.setPath(null);

        final ProjectValidatorService service = new ProjectValidatorServiceImpl();
        final boolean validate = service.validate(projectDescription);
        Assert.assertFalse(validate);

        final List<ErrorType> errors = service.getErrors();
        Assert.assertFalse(errors.isEmpty());
        Assert.assertEquals(1, errors.size());
        Assert.assertTrue(errors.contains(ErrorType.PROJECT_PATH_ERROR));
    }

    @Test
    public void validateProjectFilePath() {
        final ProjectDescription projectDescription = new ProjectDescription();
        projectDescription.setName(PROJECT_NAME);
        projectDescription.setGroup(GROUP_NAME);
        projectDescription.setPath("~/.bash_history");

        final ProjectValidatorService service = new ProjectValidatorServiceImpl();
        final boolean validate = service.validate(projectDescription);
        Assert.assertFalse(validate);

        final List<ErrorType> errors = service.getErrors();
        Assert.assertFalse(errors.isEmpty());
        Assert.assertEquals(1, errors.size());
        Assert.assertTrue(errors.contains(ErrorType.PROJECT_PATH_ERROR));
    }

    @Test
    public void validateProjectCantWritePath() {
        final ProjectDescription projectDescription = new ProjectDescription();
        projectDescription.setName(PROJECT_NAME);
        projectDescription.setGroup(GROUP_NAME);
        projectDescription.setPath("/root/");

        final ProjectValidatorService service = new ProjectValidatorServiceImpl();
        final boolean validate = service.validate(projectDescription);
        Assert.assertFalse(validate);

        final List<ErrorType> errors = service.getErrors();
        Assert.assertFalse(errors.isEmpty());
        Assert.assertEquals(1, errors.size());
        Assert.assertTrue(errors.contains(ErrorType.PROJECT_PATH_ERROR));
    }
}
