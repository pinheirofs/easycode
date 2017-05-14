package com.s2.easycode.mainframe;

import java.util.List;

public interface MainFrame {

    void showFrame();

    String getProjectName();

    String getProjectPath();

    String getEntityName();

    List<String[]> getAttributes();

    void showProjectNameErrorMsg();

    void showProjectPathErrorMsg();

    void showEntityClassNameErrorMsg();

    void showEntityArttributeErrorMsg();

    void showUndefineErrorMsg();

}
