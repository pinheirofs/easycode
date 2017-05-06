package com.s2.easycode;

import com.s2.easycode.mainframe.MainFrame;
import com.s2.easycode.mainframe.MainFrameCtrl;
import com.s2.easycode.mainframe.MainFrameSwing;

public class UiFactorySwing extends UiFactoryAbstract {

    @Override
    public MainFrame createMainFrame(final MainFrameCtrl mainFrameCtrl) {
        return new MainFrameSwing(mainFrameCtrl);
    }

}
