package com.s2.easycode.mainframe;

import com.s2.easycode.UiFactoryAbstract;

public class MainFrameCtrl {

    private MainFrame mainframe;

    public MainFrameCtrl() {
    }

    public void showMainFrame() {
        final UiFactoryAbstract factory = UiFactoryAbstract.getInstance();
        mainframe = factory.createMainFrame(this);
        mainframe.showFrame();
    }

    public void createEntity() {
        // TODO Auto-generated method stub

    }

}
