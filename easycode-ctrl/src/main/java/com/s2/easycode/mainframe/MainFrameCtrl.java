package com.s2.easycode.mainframe;

import java.io.IOException;

import com.s2.easycode.UiFactoryAbstract;
import com.s2.easycode.sourcegenerator.AttributeType;
import com.s2.easycode.sourcegenerator.EntityDescription;
import com.s2.easycode.sourcegenerator.EntityGeneratorService;
import com.s2.easycode.sourcegenerator.ProjectDescription;
import com.s2.easycode.sourcegenerator.ProjectGeneratorService;
import com.s2.easycode.validator.EntityValidatorService;
import com.s2.easycode.validator.ErrorType;
import com.s2.easycode.validator.ProjectValidatorService;

public class MainFrameCtrl {

    private MainFrame mainFrame;
    private final EntityValidatorService entityValidatorService;
    private final ProjectValidatorService projectValidatorService;
    private final EntityGeneratorService entityGeneratorService;
    private final ProjectGeneratorService projectGeneratorService;

    public MainFrameCtrl(final EntityValidatorService entityValidatorService,
            final EntityGeneratorService entityGeneratorService, final ProjectValidatorService projectValidatorService,
            final ProjectGeneratorService projectGeneratorService) {
        this.entityValidatorService = entityValidatorService;
        this.entityGeneratorService = entityGeneratorService;
        this.projectValidatorService = projectValidatorService;
        this.projectGeneratorService = projectGeneratorService;
    }

    public void showMainFrame() {
        final UiFactoryAbstract factory = UiFactoryAbstract.getInstance();
        mainFrame = factory.createMainFrame(this);
        mainFrame.showFrame();
    }

    public void createEntity() {
        final String projectName = mainFrame.getProjectName();
        final String projectPath = mainFrame.getProjectPath();
        final ProjectDescription projectDescription = new ProjectDescription();
        projectDescription.setName(projectName);
        projectDescription.setPath(projectPath);
        if (!projectValidatorService.validate(projectDescription)) {
            for (final ErrorType error : projectValidatorService.getErrors()) {
                switch (error) {
                case PROJECT_NAME_ERROR:
                    mainFrame.showProjectNameErrorMsg();
                    break;
                case PROJECT_PATH_ERROR:
                    mainFrame.showProjectPathErrorMsg();
                    break;
                default:
                    mainFrame.showUndefineErrorMsg();
                }
            }

            return;
        }

        final EntityDescription entityDescription = new EntityDescription();
        entityDescription.setName(mainFrame.getEntityName());
        for (final String[] attribue : mainFrame.getAttributes()) {
            entityDescription.addAttrubute(attribue[0], AttributeType.convert(attribue[1]));
        }

        if (!entityValidatorService.validate(entityDescription)) {
            for (final ErrorType error : entityValidatorService.getErrors()) {
                switch (error) {
                case ENTITY_CLASS_NAME_ERROR:
                    mainFrame.showEntityClassNameErrorMsg();
                    break;
                case ENTITY_ATTRIBUTE_NAME_ERROR:
                    mainFrame.showEntityArttributeNameErrorMsg();
                    break;
                case ENTITY_ATTRIBUTE_TYPE_ERROR:
                    mainFrame.showEntityArttributeTypeErrorMsg();
                    break;
                default:
                    mainFrame.showUndefineErrorMsg();
                }
            }

            return;
        }

        projectGeneratorService.setProjectDescription(projectDescription);
        try {
            projectGeneratorService.generate();
        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        entityGeneratorService.generate(entityDescription);
    }
}
