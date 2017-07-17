package com.s2.easycode.mainframe;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        final Logger logger = LoggerFactory.getLogger(MainFrameCtrl.class);
        logger.info("Requested to show main frame");

        final UiFactoryAbstract factory = UiFactoryAbstract.getInstance();
        mainFrame = factory.createMainFrame(this);
        mainFrame.showFrame();

        logger.info("Main frame exhibited");
    }

    public void createEntity() {
        final Logger logger = LoggerFactory.getLogger(MainFrameCtrl.class);
        logger.info("Requested to create a entity");

        final String projectName = mainFrame.getProjectName();
        final String projectGroup = mainFrame.getProjectGroup();
        final String projectPath = mainFrame.getProjectPath();
        final ProjectDescription projectDescription = new ProjectDescription();
        projectDescription.setName(projectName);
        projectDescription.setGroup(projectGroup);
        projectDescription.setPath(projectPath);
        if (!projectValidatorService.validate(projectDescription)) {
            for (final ErrorType error : projectValidatorService.getErrors()) {
                logger.warn("Warning while a entity was validated: " + error.toString());

                switch (error) {
                case PROJECT_NAME_ERROR:
                    mainFrame.showProjectNameErrorMsg();
                    break;
                case PROJECT_GROUP_ERROR:
                    mainFrame.showProjectGroupErrorMsg();
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
        entityDescription.setPackage(mainFrame.getEntityPackage());
        entityDescription.setName(mainFrame.getEntityName());
        for (final String[] attribue : mainFrame.getAttributes()) {
            entityDescription.addAttrubute(attribue[0], AttributeType.convert(attribue[1]));
        }

        if (!entityValidatorService.validate(entityDescription)) {
            for (final ErrorType error : entityValidatorService.getErrors()) {
                logger.warn("Warning while a entity was validated: " + error.toString());

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
        } catch (final Exception e) {
            logger.error("Error while try generate project", e);
            return;
        }

        entityGeneratorService.setProjectDescription(projectDescription);
        entityGeneratorService.setEntityDescription(entityDescription);
        try {
            entityGeneratorService.generate();
        } catch (final IOException e) {
            logger.error("Error while try generate entity", e);
            return;
        }
    }
}
