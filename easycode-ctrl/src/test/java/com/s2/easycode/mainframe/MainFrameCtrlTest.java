package com.s2.easycode.mainframe;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.s2.easycode.UiFactoryAbstract;
import com.s2.easycode.sourcegenerator.EntityDescription;
import com.s2.easycode.sourcegenerator.EntityGeneratorService;
import com.s2.easycode.sourcegenerator.ProjectDescription;
import com.s2.easycode.sourcegenerator.ProjectGeneratorService;
import com.s2.easycode.validator.EntityValidatorService;
import com.s2.easycode.validator.ProjectValidatorService;

public class MainFrameCtrlTest {

    private boolean validateProject;
    private boolean validateEntity;
    private boolean generateProject;
    private boolean generateEntity;
    private boolean showEntityArttribute;
    private boolean showEntityClassName;
    private boolean showProjectName;
    private boolean showProjectPath;

    @Before
    public void prepareToTest() {
        validateProject = false;
        validateEntity = false;
        generateProject = false;
        generateEntity = false;
        showEntityArttribute = false;
        showEntityClassName = false;
        showProjectName = false;
        showProjectPath = false;

        final UiFactoryAdapter uiFactory = new UiFactoryAdapter();
        uiFactory.setMainFrameAdapter(new MainFrameAdapter() {
            @Override
            public void showEntityArttributeNameErrorMsg() {
                showEntityArttribute = true;
            }

            @Override
            public void showEntityClassNameErrorMsg() {
                showEntityClassName = true;
            }

            @Override
            public void showProjectNameErrorMsg() {
                showProjectName = true;
            }

            @Override
            public void showProjectPathErrorMsg() {
                showProjectPath = true;
            }
        });
        UiFactoryAbstract.setInstance(uiFactory);
    }

    @Test
    public void createEntityAndProjectOkTest() {
        final EntityValidatorService entityValidatorService = new EntityValidatorServiceAdapter() {
            @Override
            public boolean validate(final EntityDescription entityDescription) {
                validateEntity = true;
                return true;
            }
        };

        final ProjectValidatorService projectValidatorService = new ProjectValidatorServiceAdapter() {
            @Override
            public boolean validate(final ProjectDescription projectDescription) {
                validateProject = true;
                return true;
            }
        };

        final EntityGeneratorService entityGeneratorService = new EntityGeneratorServiceAdapter() {
            @Override
            public void generate(final EntityDescription entityDescription) {
                generateEntity = true;
            }
        };
        final ProjectGeneratorService projectGeneratorService = new ProjectGeneratorServiceAdapter() {
            @Override
            public void generate() {
                generateProject = true;
            }
        };

        final MainFrameCtrl mainFrameCtrl = new MainFrameCtrl(entityValidatorService, entityGeneratorService,
                projectValidatorService, projectGeneratorService);

        mainFrameCtrl.showMainFrame();

        mainFrameCtrl.createEntity();

        Assert.assertTrue(validateProject);
        Assert.assertTrue(validateEntity);
        Assert.assertTrue(generateProject);
        Assert.assertTrue(generateEntity);
    }

    @Test
    public void createEntityInvalidTest() {
        final EntityValidatorService entityValidatorService = new EntityValidatorServiceAdapter() {
            @Override
            public boolean validate(final EntityDescription entityDescription) {
                validateEntity = false;
                return false;
            }
        };

        final ProjectValidatorService projectValidatorService = new ProjectValidatorServiceAdapter() {
            @Override
            public boolean validate(final ProjectDescription projectDescription) {
                validateProject = true;
                return true;
            }
        };

        final EntityGeneratorService entityGeneratorService = new EntityGeneratorServiceAdapter() {
            @Override
            public void generate(final EntityDescription entityDescription) {
                Assert.fail();
            }
        };
        final ProjectGeneratorService projectGeneratorService = new ProjectGeneratorServiceAdapter() {
            @Override
            public void generate() {
                Assert.fail();
            }
        };

        final MainFrameCtrl mainFrameCtrl = new MainFrameCtrl(entityValidatorService, entityGeneratorService,
                projectValidatorService, projectGeneratorService);

        mainFrameCtrl.showMainFrame();

        mainFrameCtrl.createEntity();

        Assert.assertFalse(validateEntity);
        Assert.assertFalse(generateProject);
        Assert.assertFalse(generateEntity);
        Assert.assertTrue(showEntityClassName);
        Assert.assertTrue(showEntityArttribute);
    }

    @Test
    public void createProjectInvalidTest() {
        final EntityValidatorService entityValidatorService = new EntityValidatorServiceAdapter() {
            @Override
            public boolean validate(final EntityDescription entityDescription) {
                validateEntity = true;
                return true;
            }
        };

        final ProjectValidatorService projectValidatorService = new ProjectValidatorServiceAdapter() {
            @Override
            public boolean validate(final ProjectDescription projectDescription) {
                validateProject = false;
                return false;
            }
        };

        final EntityGeneratorService entityGeneratorService = new EntityGeneratorServiceAdapter() {
            @Override
            public void generate(final EntityDescription entityDescription) {
                Assert.fail();
            }
        };
        final ProjectGeneratorService projectGeneratorService = new ProjectGeneratorServiceAdapter() {
            @Override
            public void generate() {
                Assert.fail();
            }
        };

        final MainFrameCtrl mainFrameCtrl = new MainFrameCtrl(entityValidatorService, entityGeneratorService,
                projectValidatorService, projectGeneratorService);

        mainFrameCtrl.showMainFrame();

        mainFrameCtrl.createEntity();

        Assert.assertFalse(validateProject);
        Assert.assertFalse(generateProject);
        Assert.assertFalse(generateEntity);
        Assert.assertTrue(showProjectName);
        Assert.assertTrue(showProjectPath);

    }
}
