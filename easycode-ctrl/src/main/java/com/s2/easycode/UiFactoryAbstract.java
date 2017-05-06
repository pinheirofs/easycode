package com.s2.easycode;

import com.s2.easycode.mainframe.MainFrame;
import com.s2.easycode.mainframe.MainFrameCtrl;

public abstract class UiFactoryAbstract {

    private static UiFactoryAbstract instance;

    public static UiFactoryAbstract getInstance() {
        return instance;
    }

    public static void setInstance(final UiFactoryAbstract instance) {
        UiFactoryAbstract.instance = instance;
    }

    public abstract MainFrame createMainFrame(final MainFrameCtrl mainFrameCtrl);

}
