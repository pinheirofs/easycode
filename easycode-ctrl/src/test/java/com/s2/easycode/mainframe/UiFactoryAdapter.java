package com.s2.easycode.mainframe;

import com.s2.easycode.UiFactoryAbstract;

public class UiFactoryAdapter extends UiFactoryAbstract {

    private MainFrameAdapter mainFrameAdapter;

    @Override
    public MainFrame createMainFrame(final MainFrameCtrl mainFrameCtrl) {
        return mainFrameAdapter;
    }

    public MainFrameAdapter getMainFrameAdapter() {
        return mainFrameAdapter;
    }

    public void setMainFrameAdapter(final MainFrameAdapter mainFrameAdapter) {
        this.mainFrameAdapter = mainFrameAdapter;
    }

}
