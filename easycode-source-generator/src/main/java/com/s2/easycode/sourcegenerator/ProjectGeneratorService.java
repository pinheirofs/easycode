package com.s2.easycode.sourcegenerator;

public interface ProjectGeneratorService {

    void generate() throws Exception;

    void setProjectDescription(final ProjectDescription projectDescription);

}
