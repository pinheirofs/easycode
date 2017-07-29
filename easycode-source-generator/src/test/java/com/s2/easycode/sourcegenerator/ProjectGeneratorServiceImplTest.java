package com.s2.easycode.sourcegenerator;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.s2.easycode.sourcegenerator.maven.v400.xjc.Model;

public class ProjectGeneratorServiceImplTest {

    private static final String SRC_TEST_RESOURCE_DIRECTORY = "src/test/resource";
    private static final String SRC_TEST_JAVA_DIRECTORY = "src/test/java";
    private static final String SRC_MAIN_RESOURCE_DIRECTORY = "src/main/resource";
    private static final String SRC_MAIN_JAVA_DIRECTORY = "src/main/java";
    private static final String POM_FILE_NAME = "pom.xml";
    private static final String DEFAULT_PROJECT_PATH = "/tmp/";
    private static final String DEFAULT_PROJECT_NAME = "project";
    private static final String DEFAULT_PROJECT_GROUP = "group";

    @Before
    public void prepareTestExecution() throws InterruptedException {
        final String projectFilepath = DEFAULT_PROJECT_PATH + DEFAULT_PROJECT_NAME;
        final File file = new File(projectFilepath);
        if (file.exists()) {
            deleteDirectoryTree(file);

            Thread.sleep(1000);
        }
    }

    private static void deleteDirectoryTree(final File directory) {
        final File[] subdirectories = directory.listFiles();
        if (subdirectories != null && subdirectories.length != 0) {
            for (final File subdirectory : subdirectories) {
                deleteDirectoryTree(subdirectory);
            }
        }

        directory.delete();
    }

    @Test
    public void generateOk() throws Exception {
        final ProjectDescription projectDescription = new ProjectDescription();
        projectDescription.setName(DEFAULT_PROJECT_NAME);
        projectDescription.setGroup(DEFAULT_PROJECT_GROUP);
        projectDescription.setPath(DEFAULT_PROJECT_PATH);

        final ProjectGeneratorService service = new ProjectGeneratorServiceImpl();
        service.setProjectDescription(projectDescription);
        service.generate();

        // Verificar se o diretorio foi criado.
        final String projectFilepath = DEFAULT_PROJECT_PATH + DEFAULT_PROJECT_NAME;
        final File projectDirecotry = new File(projectFilepath);
        Assert.assertTrue(projectDirecotry.exists());
        Assert.assertTrue(projectDirecotry.isDirectory());

        // Verificar a criacao do arquivo pom
        final File pomFile = new File(projectFilepath + File.separator + POM_FILE_NAME);
        Assert.assertTrue(pomFile.exists());
        Assert.assertTrue(pomFile.isFile());

        // Ler o arquivo
        final JAXBContext jaxbContext = JAXBContext.newInstance("com.s2.easycode.sourcegenerator.maven.v400.xjc");
        final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        final StreamSource source = new StreamSource(pomFile);
        final JAXBElement<Model> element = unmarshaller.unmarshal(source, Model.class);
        final Model model = element.getValue();

        // Testar o arquivo
        Assert.assertEquals(DEFAULT_PROJECT_NAME, model.getArtifactId());
        Assert.assertEquals(DEFAULT_PROJECT_GROUP, model.getGroupId());
        Assert.assertEquals("0.0.1", model.getVersion());

        // Verificar a criacao do subdiretorio src/main/java
        final File srcMainJavaDirectory = new File(projectFilepath + File.separator + SRC_MAIN_JAVA_DIRECTORY);
        Assert.assertTrue(srcMainJavaDirectory.exists());
        Assert.assertTrue(srcMainJavaDirectory.isDirectory());

        // Verificar a criacao do subdiretorio src/main/resource
        final File srcMainResourceDirectory = new File(projectFilepath + File.separator + SRC_MAIN_RESOURCE_DIRECTORY);
        Assert.assertTrue(srcMainResourceDirectory.exists());
        Assert.assertTrue(srcMainResourceDirectory.isDirectory());

        // adicionar o teste para o subdiretorio src/test/java
        final File srcTestJavaDirectory = new File(projectFilepath + File.separator + SRC_TEST_JAVA_DIRECTORY);
        Assert.assertTrue(srcTestJavaDirectory.exists());
        Assert.assertTrue(srcTestJavaDirectory.isDirectory());

        // adicionar o teste para o subdiretorio src/test/resource
        final File srcTestResourceDirectory = new File(projectFilepath + File.separator + SRC_TEST_RESOURCE_DIRECTORY);
        Assert.assertTrue(srcTestResourceDirectory.exists());
        Assert.assertTrue(srcTestResourceDirectory.isDirectory());
    }

}
