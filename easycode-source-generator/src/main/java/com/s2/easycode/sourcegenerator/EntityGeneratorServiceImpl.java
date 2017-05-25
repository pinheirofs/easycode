package com.s2.easycode.sourcegenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EntityGeneratorServiceImpl implements EntityGeneratorService {

    private static final String JAVA_SOURCE_FILE_EXTESION = ".java";

    private static final String SOURCE_DIRECTORY = "src/main/java/";

    private ProjectDescription projectDescription;
    private EntityDescription entityDescription;

    @Override
    public void setEntityDescription(final EntityDescription entityDescription) {
        this.entityDescription = entityDescription;
    }

    @Override
    public void setProjectDescription(final ProjectDescription projectDescription) {
        this.projectDescription = projectDescription;

    }

    @Override
    public void generate() throws IOException {
        final File sourceFile = createSourceFile();

        writeEntityClass(sourceFile);
    }

    private void writeEntityClass(final File sourceFile) throws IOException {
        final FileWriter writer = new FileWriter(sourceFile);

        final String entityName = entityDescription.getName();
        writer.write("public class " + entityName + " {\n");
        writer.write("\n");

        for (final AttributeDescription attributeDescription : entityDescription.getAttributes()) {
            final String attributeName = attributeDescription.getName();
            final AttributeType attributeType = attributeDescription.getType();

            writer.write("    private " + attributeType.getJavaType() + " " + attributeName + ";\n");
        }
        writer.write("\n");

        writer.write("    public TestClass() {\n");
        writer.write("    }\n");
        writer.write("\n");

        for (final AttributeDescription attributeDescription : entityDescription.getAttributes()) {
            final AttributeType attributeType = attributeDescription.getType();
            final String attributeName = attributeDescription.getName();
            final char firstCharacter = attributeName.charAt(0);
            final String methodGetName = "get" + Character.toUpperCase(firstCharacter) + attributeName.substring(1);

            writer.write("    public " + attributeType.getJavaType() + " " + methodGetName + "() {\n");
            writer.write("        return " + attributeName + ";\n");
            writer.write("    }\n");
            writer.write("\n");

            final String methodSetName = "set" + Character.toUpperCase(firstCharacter) + attributeName.substring(1);
            writer.write("    public void " + methodSetName + "(final " + attributeType.getJavaType() + " "
                    + attributeName + ") {\n");
            writer.write("        this." + attributeName + " = " + attributeName + ";\n");
            writer.write("    }\n");
            writer.write("\n");
        }

        writer.write("}\n");

        writer.close();
    }

    private File createSourceFile() throws IOException {
        final String sourceFilepath = assemblySourceFilepath();
        final File sourceFile = new File(sourceFilepath);
        sourceFile.createNewFile();

        return sourceFile;
    }

    private String assemblySourceFilepath() {
        String projectPath = projectDescription.getPath();
        if (!projectPath.endsWith(File.separator)) {
            projectPath += File.separator;
        }
        final String projectName = projectDescription.getName();

        final String projectDirectory = projectPath + projectName + File.separator;
        final String sourceDirectory = projectDirectory + SOURCE_DIRECTORY;
        final String sourceFilepath = sourceDirectory + entityDescription.getName() + JAVA_SOURCE_FILE_EXTESION;

        return sourceFilepath;
    }

}
