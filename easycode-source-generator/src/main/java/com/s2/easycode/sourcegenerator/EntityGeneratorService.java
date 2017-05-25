package com.s2.easycode.sourcegenerator;

import java.io.IOException;

public interface EntityGeneratorService {

    void setProjectDescription(final ProjectDescription projectDescription);

    void setEntityDescription(final EntityDescription entityDescription);

    void generate() throws IOException;

}
