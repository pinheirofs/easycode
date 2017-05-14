package com.s2.easycode;

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
