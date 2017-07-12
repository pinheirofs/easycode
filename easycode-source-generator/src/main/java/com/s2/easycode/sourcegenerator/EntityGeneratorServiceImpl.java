package com.s2.easycode.sourcegenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier.ModifierKeyword;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class EntityGeneratorServiceImpl implements EntityGeneratorService {

    private static final String GET_PREFIX = "get";
    private static final String SET_PREFIX = "set";

    private static final String JAVA_SOURCE_FILE_EXTESION = ".java";

    private static final String SOURCE_DIRECTORY = "src/main/java/";

    private ProjectDescription projectDescription;
    private EntityDescription entityDescription;

    private AST ast;

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
        final FileWriter writer = new FileWriter(sourceFile);

        ast = AST.newAST(AST.JLS8);

        final String entityText = createEntity();
        writer.write(entityText);

        writer.close();
    }

    String createEntity() {
        final TypeDeclaration typeDeclaration = createEntityClass();
        createEntityAttributes(typeDeclaration);
        createEntityGetters(typeDeclaration);
        createEntitySetters(typeDeclaration);

        return typeDeclaration.toString();
    }

    private void createEntitySetters(final TypeDeclaration typeDeclaration) {
        @SuppressWarnings("unchecked")
        final List<BodyDeclaration> bodyDeclarations = typeDeclaration.bodyDeclarations();

        final List<AttributeDescription> attributes = entityDescription.getAttributes();
        for (final AttributeDescription attribute : attributes) {
            createEntitySetter(bodyDeclarations, attribute);
        }
    }

    private void createEntitySetter(final List<BodyDeclaration> bodyDeclarations,
            final AttributeDescription attribute) {
        final String attributeName = attribute.getName();
        final String name = createMethodName(attributeName, SET_PREFIX);

        final MethodDeclaration methodDeclaration = ast.newMethodDeclaration();
        methodDeclaration.setName(ast.newSimpleName(name));
        methodDeclaration.setReturnType2(ast.newPrimitiveType(PrimitiveType.VOID));
        addModifier(ModifierKeyword.PUBLIC_KEYWORD, methodDeclaration);
        final SingleVariableDeclaration parameter = ast.newSingleVariableDeclaration();
        parameter.setName(ast.newSimpleName(attributeName));
        addModifier(ModifierKeyword.FINAL_KEYWORD, parameter);
        adddParameter(methodDeclaration, parameter);
        bodyDeclarations.add(methodDeclaration);

        // Criar o conteudo do set
        final Block setBlock = ast.newBlock();
        methodDeclaration.setBody(setBlock);
        final FieldAccess fieldAccess = ast.newFieldAccess();
        fieldAccess.setExpression(ast.newThisExpression());
        fieldAccess.setName(ast.newSimpleName(attributeName));
        final Assignment assignment = ast.newAssignment();
        assignment.setLeftHandSide(fieldAccess);
        assignment.setRightHandSide(ast.newName(attributeName));
        addStatement(setBlock, ast.newExpressionStatement(assignment));
    }

    @SuppressWarnings("unchecked")
    private static void adddParameter(final MethodDeclaration methodDeclaration,
            final SingleVariableDeclaration parameter) {
        methodDeclaration.parameters().add(parameter);
    }

    private void createEntityGetters(final TypeDeclaration typeDeclaration) {
        @SuppressWarnings("unchecked")
        final List<BodyDeclaration> bodyDeclarations = typeDeclaration.bodyDeclarations();

        final List<AttributeDescription> attributes = entityDescription.getAttributes();
        for (final AttributeDescription attribute : attributes) {
            createEntityGetter(bodyDeclarations, attribute);
        }
    }

    private void createEntityGetter(final List<BodyDeclaration> bodyDeclarations,
            final AttributeDescription attribute) {
        final String attributeName = attribute.getName();
        final String name = createMethodName(attributeName, GET_PREFIX);

        final AttributeType type = attribute.getType();
        final MethodDeclaration methodDeclaration = ast.newMethodDeclaration();
        methodDeclaration.setName(ast.newSimpleName(name));
        if (type.isPrimitiveType()) {
            methodDeclaration.setReturnType2(ast.newPrimitiveType(type.getJdtPrimitiveType()));
        } else {
            // TODO Add code to return with Objrct type
        }
        addModifier(ModifierKeyword.PUBLIC_KEYWORD, methodDeclaration);
        bodyDeclarations.add(methodDeclaration);

        // Criar o conteudo do get
        final Block getBlock = ast.newBlock();
        methodDeclaration.setBody(getBlock);
        final ReturnStatement returnStatement = ast.newReturnStatement();
        returnStatement.setExpression(ast.newName(attributeName));
        addStatement(getBlock, returnStatement);

    }

    @SuppressWarnings("unchecked")
    private static void addStatement(final Block getBlock, final Statement statement) {
        getBlock.statements().add(statement);
    }

    private static String createMethodName(final String name, final String prefix) {
        final char firstLowerCase = name.charAt(0);
        final char fisrtUpperCase = Character.toUpperCase(firstLowerCase);

        final StringBuilder builder = new StringBuilder();
        builder.append(prefix);
        builder.append(fisrtUpperCase);
        builder.append(name.substring(1));
        return builder.toString();
    }

    private void createEntityAttributes(final TypeDeclaration typeDeclaration) {
        @SuppressWarnings("unchecked")
        final List<BodyDeclaration> bodyDeclarations = typeDeclaration.bodyDeclarations();

        final List<AttributeDescription> attributes = entityDescription.getAttributes();
        for (final AttributeDescription attribute : attributes) {
            createEntityAttribute(bodyDeclarations, attribute);
        }
    }

    private void createEntityAttribute(final List<BodyDeclaration> bodyDeclarations,
            final AttributeDescription attribute) {
        final String name = attribute.getName();
        final VariableDeclarationFragment variableDeclaration = ast.newVariableDeclarationFragment();
        variableDeclaration.setName(ast.newSimpleName(name));

        final AttributeType type = attribute.getType();
        final FieldDeclaration fieldDeclaration = ast.newFieldDeclaration(variableDeclaration);
        addModifier(ModifierKeyword.PRIVATE_KEYWORD, fieldDeclaration);
        Type astType;
        if (type.isPrimitiveType()) {
            astType = ast.newPrimitiveType(type.getJdtPrimitiveType());
        } else {
            astType = null;
            // TODO Add code to attribute with Objrct type
        }
        fieldDeclaration.setType(astType);
        bodyDeclarations.add(fieldDeclaration);
    }

    @SuppressWarnings("unchecked")
    private void addModifier(final ModifierKeyword modifierKeyword, final BodyDeclaration bodyDeclaration) {
        bodyDeclaration.modifiers().add(ast.newModifier(modifierKeyword));
    }

    @SuppressWarnings("unchecked")
    private void addModifier(final ModifierKeyword modifierKeyword,
            final SingleVariableDeclaration variableDeclaration) {
        variableDeclaration.modifiers().add(ast.newModifier(modifierKeyword));
    }

    private TypeDeclaration createEntityClass() {
        final String name = entityDescription.getName();

        final TypeDeclaration typeDeclaration = ast.newTypeDeclaration();
        typeDeclaration.setName(ast.newSimpleName(name));
        typeDeclaration.setInterface(false);
        addModifier(ModifierKeyword.PUBLIC_KEYWORD, typeDeclaration);

        return typeDeclaration;
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
