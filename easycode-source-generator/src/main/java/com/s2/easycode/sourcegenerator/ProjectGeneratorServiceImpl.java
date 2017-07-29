package com.s2.easycode.sourcegenerator;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;

import com.s2.easycode.sourcegenerator.maven.v400.xjc.Model;
import com.s2.easycode.sourcegenerator.maven.v400.xjc.ObjectFactory;

public class ProjectGeneratorServiceImpl implements ProjectGeneratorService {

    private static final String PROJECT_VERSION = "0.0.1";
    private static final String SCHEMA_LOCATION = "http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd";
    private static final String POM_FILE_MODEL_VERSION = "0.0.1-SNAPSHOT";
    private static final String POM_FILE_PACKAGING_JAR = "jar";
    private static final String POM_FILE_XML = "pom.xml";
    private static final String SRC_TEST_RESOURCE_DIRECOTRY = "src/test/resource/";
    private static final String SRC_TEST_JAVA_DIRECTORY = "src/test/java/";
    private static final String SRC_MAIN_RESOURCE_DIRECTORY = "src/main/resource/";
    private static final String SRC_MAIN_JAVA_DIRECTORY = "src/main/java/";

    ProjectDescription projectDescription;

    @Override
    public void generate() throws Exception {
        if (!createProjectDirectory()) {
            return;
        }

        createPomFileXsd();

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

    private void createPomFileXsd() throws Exception {
        final ObjectFactory factory = new ObjectFactory();

        final Model model = factory.createModel();
        model.setArtifactId(projectDescription.getName());
        model.setGroupId(projectDescription.getGroup());
        model.setModelVersion(POM_FILE_MODEL_VERSION);
        model.setPackaging(POM_FILE_PACKAGING_JAR);
        model.setVersion(PROJECT_VERSION);

        final JAXBElement<Model> project = factory.createProject(model);

        final String projectDirectory = assemblyProjectDirectory();
        final File pomFile = new File(projectDirectory + POM_FILE_XML);
        pomFile.createNewFile();

        final JAXBContext jaxbContext = JAXBContext.newInstance("com.s2.easycode.sourcegenerator.maven.v400.xjc");
        final Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, SCHEMA_LOCATION);
        marshaller.marshal(project, pomFile);
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
