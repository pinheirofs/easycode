package com.s2.easycode.sourcegenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ProjectGeneratorServiceImpl implements ProjectGeneratorService {

    private static final String SRC_TEST_RESOURCE_DIRECOTRY = "src/test/resource/";
    private static final String SRC_TEST_JAVA_DIRECTORY = "src/test/java/";
    private static final String SRC_MAIN_RESOURCE_DIRECTORY = "src/main/resource/";
    private static final String SRC_MAIN_JAVA_DIRECTORY = "src/main/java/";
    private static final String POM_FILE_XML = "pom.xml";
    private static final String PROJECT_VERSION = "0.0.1-SNAPSHOT";

    ProjectDescription projectDescription;

    @Override
    public void generate() throws IOException {
        if (!createProjectDirectory()) {
            return;
        }

        createPomFile();

        createDirectories();
    }

    private void createDirectories() {
        final String projectDirectory = assemblyProjectDirectory();

        final File mainJavaDirectory = new File(projectDirectory + SRC_MAIN_JAVA_DIRECTORY);
        if (!mainJavaDirectory.mkdirs()) {
            return;
        }

        final File mainResourceDirectory = new File(projectDirectory + SRC_MAIN_RESOURCE_DIRECTORY);
        if (!mainResourceDirectory.mkdirs()) {
            return;
        }

        final File testJavaDirectory = new File(projectDirectory + SRC_TEST_JAVA_DIRECTORY);
        if (!testJavaDirectory.mkdirs()) {
            return;
        }

        final File testResourceDirectory = new File(projectDirectory + SRC_TEST_RESOURCE_DIRECOTRY);
        if (!testResourceDirectory.mkdirs()) {
            return;
        }
    }

    private void createPomFile() throws IOException {
        final String projectDirectory = assemblyProjectDirectory();

        final File pomFile = new File(projectDirectory + POM_FILE_XML);
        pomFile.createNewFile();

        final InputStream inputStream = ProjectGeneratorServiceImpl.class
                .getResourceAsStream("pom.template.header.xml");
        final InputStreamReader reader = new InputStreamReader(inputStream);
        final char[] buffer = new char[1024];
        final StringBuffer fileContent = new StringBuffer();
        while (reader.read(buffer) > 0) {
            fileContent.append(buffer);
        }
        reader.close();

        final String name = projectDescription.getName();
        final String group = projectDescription.getGroup();

        final String formatedFileContent = String.format(fileContent.toString(), name, group, PROJECT_VERSION, "");

        final FileOutputStream outputStream = new FileOutputStream(pomFile);
        final OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        writer.write(formatedFileContent);

        writer.close();
    }

    private boolean createProjectDirectory() {
        final String projectDirectory = assemblyProjectDirectory();

        final File projectDirectoryFile = new File(projectDirectory);
        return projectDirectoryFile.mkdirs();
    }

    private String assemblyProjectDirectory() {
        String projectPath = projectDescription.getPath();
        if (!projectPath.endsWith(File.separator)) {
            projectPath += File.separator;
        }
        final String projectName = projectDescription.getName();

        final String projectDirectory = projectPath + projectName + File.separator;
        return projectDirectory;
    }

    @Override
    public void setProjectDescription(final ProjectDescription projectDescription) {
        this.projectDescription = projectDescription;
    }

}
