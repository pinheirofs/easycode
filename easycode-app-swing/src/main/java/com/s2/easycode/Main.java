package com.s2.easycode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.s2.easycode.mainframe.MainFrameCtrl;
import com.s2.easycode.sourcegenerator.EntityGeneratorService;
import com.s2.easycode.sourcegenerator.EntityGeneratorServiceImpl;
import com.s2.easycode.sourcegenerator.ProjectGeneratorService;
import com.s2.easycode.sourcegenerator.ProjectGeneratorServiceImpl;
import com.s2.easycode.validator.EntityValidatorService;
import com.s2.easycode.validator.EntityValidatorServiceImpl;
import com.s2.easycode.validator.ProjectValidatorService;
import com.s2.easycode.validator.ProjectValidatorServiceImpl;

public class Main {

    public static void main(final String[] args) {
        final Logger logger = LoggerFactory.getLogger(Main.class);
        logger.info("Application start");

        UiFactoryAbstract.setInstance(new UiFactorySwing());

        final EntityValidatorService entityValidatorService = new EntityValidatorServiceImpl();
        final EntityGeneratorService entityGeneratorService = new EntityGeneratorServiceImpl();
        final ProjectValidatorService projectValidatorService = new ProjectValidatorServiceImpl();
        final ProjectGeneratorService projectGeneratorService = new ProjectGeneratorServiceImpl();

        final MainFrameCtrl ctrl = new MainFrameCtrl(entityValidatorService, entityGeneratorService,
                projectValidatorService, projectGeneratorService);
        ctrl.showMainFrame();
    }

}
