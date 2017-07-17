package com.s2.easycode.sourcegenerator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EntityGeneratorServiceImplTest {
    private static final String FILE_EXTECION = ".java";
    private static final String SRC_MAIN_JAVA = "src/main/java/";
    private static final String ATTRIBUTE_NAME = "attribute";
    private static final String TEST_CLASS_NAME = "TestClass";
    private static final String PROJECT_PATH = "/tmp/";
    private static final String PROJECT_NAME = "project";
    private static final String TEST_PACKAGET_NAME = "br.com.test";

    @Before
    public void prepareTestExecution() throws InterruptedException {
        final String projectFilepath = PROJECT_PATH + PROJECT_NAME;
        final File file = new File(projectFilepath);
        if (file.exists()) {
            deleteDirectoryTree(file);

            Thread.sleep(1000);
        }

        final File sourceDirectory = new File(PROJECT_PATH + PROJECT_NAME + File.separator + SRC_MAIN_JAVA);
        sourceDirectory.mkdirs();
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
    public void generateEntityOKTest() throws IOException {
        final EntityGeneratorService service = new EntityGeneratorServiceImpl();

        final ProjectDescription projectDescription = new ProjectDescription();
        projectDescription.setName(PROJECT_NAME);
        projectDescription.setPath(PROJECT_PATH);

        final EntityDescription entityDescription = new EntityDescription();
        entityDescription.setPackage(TEST_PACKAGET_NAME);
        entityDescription.setName(TEST_CLASS_NAME);
        entityDescription.addAttrubute(ATTRIBUTE_NAME, AttributeType.INTEGER);

        service.setProjectDescription(projectDescription);
        service.setEntityDescription(entityDescription);
        service.generate();

        final StringBuilder builder = new StringBuilder();
        builder.append(PROJECT_PATH);
        builder.append(PROJECT_NAME);
        builder.append(File.separator);
        builder.append(SRC_MAIN_JAVA);
        builder.append(TEST_PACKAGET_NAME.replace('.', File.separatorChar));
        builder.append(File.separator);
        builder.append(TEST_CLASS_NAME);
        builder.append(FILE_EXTECION);

        final File testClassFile = new File(builder.toString());
        Assert.assertTrue(testClassFile.exists());
        Assert.assertTrue(testClassFile.isFile());

        final FileInputStream stream = new FileInputStream(testClassFile);
        final InputStreamReader reader = new InputStreamReader(stream);
        final BufferedReader bufferedReader = new BufferedReader(reader);
        String line = bufferedReader.readLine();
        Assert.assertEquals("package br.com.test;", line);

        line = bufferedReader.readLine();
        Assert.assertEquals("public class TestClass {", line);

        line = bufferedReader.readLine();
        Assert.assertEquals("  private int attribute;", line);

        line = bufferedReader.readLine();
        Assert.assertEquals("  public int getAttribute(){", line);

        line = bufferedReader.readLine();
        Assert.assertEquals("    return attribute;", line);

        line = bufferedReader.readLine();
        Assert.assertEquals("  }", line);

        line = bufferedReader.readLine();
        Assert.assertEquals("  public void setAttribute(  final int attribute){", line);

        line = bufferedReader.readLine();
        Assert.assertEquals("    this.attribute=attribute;", line);

        line = bufferedReader.readLine();
        Assert.assertEquals("  }", line);

        line = bufferedReader.readLine();
        Assert.assertEquals("}", line);

        bufferedReader.close();
    }
}
