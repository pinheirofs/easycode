package com.s2.easycode.mainframe;

import java.util.List;

public interface MainFrame {

    void showFrame();

    String getProjectName();

    String getProjectGroup();

    String getProjectPath();

    String getEntityName();

    List<String[]> getAttributes();

    void showProjectNameErrorMsg();

    void showProjectPathErrorMsg();

    void showProjectGroupErrorMsg();

    void showEntityClassNameErrorMsg();

    void showEntityArttributeNameErrorMsg();

    void showEntityArttributeTypeErrorMsg();

    void showUndefineErrorMsg();

}
