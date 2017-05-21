package com.s2.easycode.sourcegenerator;

import java.io.IOException;

public interface ProjectGeneratorService {

    void generate() throws IOException;

    void setProjectDescription(final ProjectDescription projectDescription);

}
