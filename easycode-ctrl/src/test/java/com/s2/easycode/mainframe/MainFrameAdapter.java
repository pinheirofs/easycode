package com.s2.easycode.mainframe;

import java.util.Collections;
import java.util.List;

public class MainFrameAdapter implements MainFrame {

    @Override
    public void showFrame() {
    }

    @Override
    public String getProjectName() {
        return "";
    }

    @Override
    public String getProjectPath() {
        return "";
    }

    @Override
    public String getEntityName() {
        return "";
    }

    @Override
    public List<String[]> getAttributes() {
        return Collections.emptyList();
    }

    @Override
    public void showProjectNameErrorMsg() {
    }

    @Override
    public void showProjectPathErrorMsg() {
    }

    @Override
    public void showEntityClassNameErrorMsg() {
    }

    @Override
    public void showEntityArttributeErrorMsg() {
    }

    @Override
    public void showUndefineErrorMsg() {
    }

}