package com.s2.easycode.mainframe;

import com.s2.easycode.UiFactoryAbstract;
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
        projectDescription.setProjectName(projectName);
        projectDescription.setProjectPath(projectPath);
        if (!projectValidatorService.validate(projectDescription)) {
            for (final ErrorType error : projectValidatorService.getErrors()) {
                switch (error) {
                case PROJEC_NAME_ERROR:
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
            entityDescription.addAttrubute(attribue[0], attribue[1]);
        }

        if (!entityValidatorService.validate(entityDescription)) {
            for (final ErrorType error : entityValidatorService.getErrors()) {
                switch (error) {
                case CLASS_NAME_ERROR:
                    mainFrame.showEntityClassNameErrorMsg();
                    break;
                case ATTRIBUTE_ERROR:
                    mainFrame.showEntityArttributeErrorMsg();
                    break;
                default:
                    mainFrame.showUndefineErrorMsg();
                }
            }

            return;
        }

        projectGeneratorService.generate(projectDescription);
        entityGeneratorService.generate(entityDescription);
    }

}
