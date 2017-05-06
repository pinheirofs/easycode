package com.s2.easycode;

import com.s2.easycode.mainframe.MainFrameCtrl;

public class Main {

    public static void main(final String[] args) {
        UiFactoryAbstract.setInstance(new UiFactorySwing());

        final MainFrameCtrl ctrl = new MainFrameCtrl();
        ctrl.showMainFrame();
    }

}
