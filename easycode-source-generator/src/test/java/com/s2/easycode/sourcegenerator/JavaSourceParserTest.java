package com.s2.easycode.sourcegenerator;

import java.util.List;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier.ModifierKeyword;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class JavaSourceParserTest {

    private static final String ENTITY_PACKAGE = "br.com.test";
    private static final String STRING_TYPE = "String";
    private static final String ENTITY_ATTRIBUTE_SET = "setAttribute";
    private static final String ENTITY_ATTRIBUTE_GET = "getAttribute";
    private static final String ENTITY_CLASS_NAME = "Entity";
    private static final String ENTITY_ATTRIBUTE_NAME = "entityAttribute";

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void createJavaClassTest() {
        final AST ast = AST.newAST(AST.JLS8);

        // Criacao do Tipo da classe
        final TypeDeclaration typeDeclaration = ast.newTypeDeclaration();
        typeDeclaration.setInterface(false);
        typeDeclaration.modifiers().add(ast.newModifier(ModifierKeyword.PUBLIC_KEYWORD));
        typeDeclaration.setName(ast.newSimpleName(ENTITY_CLASS_NAME));

        // Lista de itens dentro da classe
        final List typeBodyDeclarations = typeDeclaration.bodyDeclarations();

        // Criacao de um atributo
        final VariableDeclarationFragment variableDeclarationFragment = ast.newVariableDeclarationFragment();
        variableDeclarationFragment.setName(ast.newSimpleName(ENTITY_ATTRIBUTE_NAME));
        final FieldDeclaration fieldDeclaration = ast.newFieldDeclaration(variableDeclarationFragment);
        fieldDeclaration.modifiers().add(ast.newModifier(ModifierKeyword.PRIVATE_KEYWORD));
        fieldDeclaration.setType(ast.newPrimitiveType(PrimitiveType.INT));
        typeBodyDeclarations.add(fieldDeclaration);

        // Criar um metodo get
        final MethodDeclaration getMethodDeclaration = ast.newMethodDeclaration();
        getMethodDeclaration.setName(ast.newSimpleName(ENTITY_ATTRIBUTE_GET));
        getMethodDeclaration.setReturnType2(ast.newPrimitiveType(PrimitiveType.INT));
        getMethodDeclaration.modifiers().add(ast.newModifier(ModifierKeyword.PUBLIC_KEYWORD));
        typeBodyDeclarations.add(getMethodDeclaration);

        // Criar o conteudo do get
        final Block getBlock = ast.newBlock();
        getMethodDeclaration.setBody(getBlock);
        final ReturnStatement returnStatement = ast.newReturnStatement();
        returnStatement.setExpression(ast.newName(ENTITY_ATTRIBUTE_NAME));
        getBlock.statements().add(returnStatement);

        // Criar um metodo set
        final MethodDeclaration setMethodDeclaration = ast.newMethodDeclaration();
        setMethodDeclaration.setName(ast.newSimpleName(ENTITY_ATTRIBUTE_SET));
        setMethodDeclaration.setReturnType2(ast.newPrimitiveType(PrimitiveType.VOID));
        setMethodDeclaration.modifiers().add(ast.newModifier(ModifierKeyword.PUBLIC_KEYWORD));
        final SingleVariableDeclaration parameter = ast.newSingleVariableDeclaration();
        parameter.setName(ast.newSimpleName(ENTITY_ATTRIBUTE_NAME));
        parameter.modifiers().add(ast.newModifier(ModifierKeyword.FINAL_KEYWORD));
        setMethodDeclaration.parameters().add(parameter);
        typeBodyDeclarations.add(setMethodDeclaration);

        // Criar o conteudo do set
        final Block setBlock = ast.newBlock();
        setMethodDeclaration.setBody(setBlock);
        final FieldAccess fieldAccess = ast.newFieldAccess();
        fieldAccess.setExpression(ast.newThisExpression());
        fieldAccess.setName(ast.newSimpleName(ENTITY_ATTRIBUTE_NAME));
        final Assignment assignment = ast.newAssignment();
        assignment.setLeftHandSide(fieldAccess);
        assignment.setRightHandSide(ast.newName(ENTITY_ATTRIBUTE_NAME));
        setBlock.statements().add(ast.newExpressionStatement(assignment));

        final String newFileText = typeDeclaration.toString();
        System.out.print(newFileText);
    }

    @Test
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void createPrimitiveAttributeClassTest() {
        final AST ast = AST.newAST(AST.JLS8);

        // Criacao do Tipo da classe
        final TypeDeclaration typeDeclaration = ast.newTypeDeclaration();
        typeDeclaration.setInterface(false);
        typeDeclaration.modifiers().add(ast.newModifier(ModifierKeyword.PUBLIC_KEYWORD));
        typeDeclaration.setName(ast.newSimpleName(ENTITY_CLASS_NAME));

        // Lista de itens dentro da classe
        final List typeBodyDeclarations = typeDeclaration.bodyDeclarations();

        // Criacao de um atributo
        final VariableDeclarationFragment variableDeclarationFragment = ast.newVariableDeclarationFragment();
        variableDeclarationFragment.setName(ast.newSimpleName(ENTITY_ATTRIBUTE_NAME));
        final FieldDeclaration fieldDeclaration = ast.newFieldDeclaration(variableDeclarationFragment);
        fieldDeclaration.modifiers().add(ast.newModifier(ModifierKeyword.PRIVATE_KEYWORD));
        fieldDeclaration.setType(ast.newPrimitiveType(PrimitiveType.INT));
        typeBodyDeclarations.add(fieldDeclaration);

        final String newFileText = typeDeclaration.toString();
        System.out.print(newFileText);
    }

    @Test
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void createPackageClassTest() {
        final AST ast = AST.newAST(AST.JLS8);

        // Criacao do pacote da classe
        final PackageDeclaration packageDeclaration = ast.newPackageDeclaration();
        packageDeclaration.setName(ast.newName(ENTITY_PACKAGE));

        // Criacao do Tipo da classe
        final TypeDeclaration typeDeclaration = ast.newTypeDeclaration();
        typeDeclaration.setInterface(false);
        typeDeclaration.modifiers().add(ast.newModifier(ModifierKeyword.PUBLIC_KEYWORD));
        typeDeclaration.setName(ast.newSimpleName(ENTITY_CLASS_NAME));

        // Lista de itens dentro da classe
        final List typeBodyDeclarations = typeDeclaration.bodyDeclarations();

        // Criacao de um atributo
        final VariableDeclarationFragment variableDeclarationFragment = ast.newVariableDeclarationFragment();
        variableDeclarationFragment.setName(ast.newSimpleName(ENTITY_ATTRIBUTE_NAME));
        final FieldDeclaration fieldDeclaration = ast.newFieldDeclaration(variableDeclarationFragment);
        fieldDeclaration.modifiers().add(ast.newModifier(ModifierKeyword.PRIVATE_KEYWORD));
        fieldDeclaration.setType(ast.newPrimitiveType(PrimitiveType.INT));
        typeBodyDeclarations.add(fieldDeclaration);

        final String newFileText = typeDeclaration.toString();
        System.out.print(newFileText);
    }

    @Test
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void createObjectAttributeClassTest() {
        final AST ast = AST.newAST(AST.JLS8);

        // Criacao do Tipo da classe
        final TypeDeclaration typeDeclaration = ast.newTypeDeclaration();
        typeDeclaration.setInterface(false);
        typeDeclaration.modifiers().add(ast.newModifier(ModifierKeyword.PUBLIC_KEYWORD));
        typeDeclaration.setName(ast.newSimpleName(ENTITY_CLASS_NAME));

        // Lista de itens dentro da classe
        final List typeBodyDeclarations = typeDeclaration.bodyDeclarations();

        // Criacao de um atributo
        final VariableDeclarationFragment variableDeclarationFragment = ast.newVariableDeclarationFragment();
        variableDeclarationFragment.setName(ast.newSimpleName(ENTITY_ATTRIBUTE_NAME));
        final FieldDeclaration fieldDeclaration = ast.newFieldDeclaration(variableDeclarationFragment);
        fieldDeclaration.modifiers().add(ast.newModifier(ModifierKeyword.PRIVATE_KEYWORD));
        fieldDeclaration.setType(ast.newSimpleType(ast.newName(STRING_TYPE)));
        typeBodyDeclarations.add(fieldDeclaration);

        final String newFileText = typeDeclaration.toString();
        System.out.print(newFileText);
    }

    @Test
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void createPrimitiveGetMethodTest() {
        final AST ast = AST.newAST(AST.JLS8);

        // Criacao do Tipo da classe
        final TypeDeclaration typeDeclaration = ast.newTypeDeclaration();
        typeDeclaration.setInterface(false);
        typeDeclaration.modifiers().add(ast.newModifier(ModifierKeyword.PUBLIC_KEYWORD));
        typeDeclaration.setName(ast.newSimpleName(ENTITY_CLASS_NAME));

        // Lista de itens dentro da classe
        final List typeBodyDeclarations = typeDeclaration.bodyDeclarations();

        // Criacao de um atributo
        final VariableDeclarationFragment variableDeclarationFragment = ast.newVariableDeclarationFragment();
        variableDeclarationFragment.setName(ast.newSimpleName(ENTITY_ATTRIBUTE_NAME));
        final FieldDeclaration fieldDeclaration = ast.newFieldDeclaration(variableDeclarationFragment);
        fieldDeclaration.modifiers().add(ast.newModifier(ModifierKeyword.PRIVATE_KEYWORD));
        fieldDeclaration.setType(ast.newPrimitiveType(PrimitiveType.INT));
        typeBodyDeclarations.add(fieldDeclaration);

        // Criar um metodo get
        final MethodDeclaration getMethodDeclaration = ast.newMethodDeclaration();
        getMethodDeclaration.setName(ast.newSimpleName(ENTITY_ATTRIBUTE_GET));
        getMethodDeclaration.setReturnType2(ast.newPrimitiveType(PrimitiveType.INT));
        getMethodDeclaration.modifiers().add(ast.newModifier(ModifierKeyword.PUBLIC_KEYWORD));
        typeBodyDeclarations.add(getMethodDeclaration);

        // Criar o conteudo do get
        final Block getBlock = ast.newBlock();
        getMethodDeclaration.setBody(getBlock);
        final ReturnStatement returnStatement = ast.newReturnStatement();
        returnStatement.setExpression(ast.newName(ENTITY_ATTRIBUTE_NAME));
        getBlock.statements().add(returnStatement);

        final String newFileText = typeDeclaration.toString();
        System.out.print(newFileText);
    }

    @Test
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void createObjectGetMethodTest() {
        final AST ast = AST.newAST(AST.JLS8);

        // Criacao do Tipo da classe
        final TypeDeclaration typeDeclaration = ast.newTypeDeclaration();
        typeDeclaration.setInterface(false);
        typeDeclaration.modifiers().add(ast.newModifier(ModifierKeyword.PUBLIC_KEYWORD));
        typeDeclaration.setName(ast.newSimpleName(ENTITY_CLASS_NAME));

        // Lista de itens dentro da classe
        final List typeBodyDeclarations = typeDeclaration.bodyDeclarations();

        // Criacao de um atributo
        final VariableDeclarationFragment variableDeclarationFragment = ast.newVariableDeclarationFragment();
        variableDeclarationFragment.setName(ast.newSimpleName(ENTITY_ATTRIBUTE_NAME));
        final FieldDeclaration fieldDeclaration = ast.newFieldDeclaration(variableDeclarationFragment);
        fieldDeclaration.modifiers().add(ast.newModifier(ModifierKeyword.PRIVATE_KEYWORD));
        fieldDeclaration.setType(ast.newSimpleType(ast.newName(STRING_TYPE)));
        typeBodyDeclarations.add(fieldDeclaration);

        // Criar um metodo get
        final MethodDeclaration getMethodDeclaration = ast.newMethodDeclaration();
        getMethodDeclaration.setName(ast.newSimpleName(ENTITY_ATTRIBUTE_GET));
        getMethodDeclaration.setReturnType2(ast.newSimpleType(ast.newName(STRING_TYPE)));
        getMethodDeclaration.modifiers().add(ast.newModifier(ModifierKeyword.PUBLIC_KEYWORD));
        typeBodyDeclarations.add(getMethodDeclaration);

        // Criar o conteudo do get
        final Block getBlock = ast.newBlock();
        getMethodDeclaration.setBody(getBlock);
        final ReturnStatement returnStatement = ast.newReturnStatement();
        returnStatement.setExpression(ast.newName(ENTITY_ATTRIBUTE_NAME));
        getBlock.statements().add(returnStatement);

        final String newFileText = typeDeclaration.toString();
        System.out.print(newFileText);
    }

    @Test
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void createPrimitiveSetMethodTest() {
        final AST ast = AST.newAST(AST.JLS8);

        // Criacao do Tipo da classe
        final TypeDeclaration typeDeclaration = ast.newTypeDeclaration();
        typeDeclaration.setInterface(false);
        typeDeclaration.modifiers().add(ast.newModifier(ModifierKeyword.PUBLIC_KEYWORD));
        typeDeclaration.setName(ast.newSimpleName(ENTITY_CLASS_NAME));

        // Lista de itens dentro da classe
        final List typeBodyDeclarations = typeDeclaration.bodyDeclarations();

        // Criacao de um atributo
        final VariableDeclarationFragment variableDeclarationFragment = ast.newVariableDeclarationFragment();
        variableDeclarationFragment.setName(ast.newSimpleName(ENTITY_ATTRIBUTE_NAME));
        final FieldDeclaration fieldDeclaration = ast.newFieldDeclaration(variableDeclarationFragment);
        fieldDeclaration.modifiers().add(ast.newModifier(ModifierKeyword.PRIVATE_KEYWORD));
        fieldDeclaration.setType(ast.newPrimitiveType(PrimitiveType.INT));
        typeBodyDeclarations.add(fieldDeclaration);

        // Criar um metodo set
        final MethodDeclaration setMethodDeclaration = ast.newMethodDeclaration();
        setMethodDeclaration.setName(ast.newSimpleName(ENTITY_ATTRIBUTE_SET));
        setMethodDeclaration.setReturnType2(ast.newPrimitiveType(PrimitiveType.VOID));
        setMethodDeclaration.modifiers().add(ast.newModifier(ModifierKeyword.PUBLIC_KEYWORD));
        final SingleVariableDeclaration parameter = ast.newSingleVariableDeclaration();
        parameter.setName(ast.newSimpleName(ENTITY_ATTRIBUTE_NAME));
        parameter.modifiers().add(ast.newModifier(ModifierKeyword.FINAL_KEYWORD));
        setMethodDeclaration.parameters().add(parameter);
        typeBodyDeclarations.add(setMethodDeclaration);

        // Criar o conteudo do set
        final Block setBlock = ast.newBlock();
        setMethodDeclaration.setBody(setBlock);
        final FieldAccess fieldAccess = ast.newFieldAccess();
        fieldAccess.setExpression(ast.newThisExpression());
        fieldAccess.setName(ast.newSimpleName(ENTITY_ATTRIBUTE_NAME));
        final Assignment assignment = ast.newAssignment();
        assignment.setLeftHandSide(fieldAccess);
        assignment.setRightHandSide(ast.newName(ENTITY_ATTRIBUTE_NAME));
        setBlock.statements().add(ast.newExpressionStatement(assignment));

        final String newFileText = typeDeclaration.toString();
        System.out.print(newFileText);
    }

    @Test
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void createObjectSetMethodTest() {
        final AST ast = AST.newAST(AST.JLS8);

        // Criacao do Tipo da classe
        final TypeDeclaration typeDeclaration = ast.newTypeDeclaration();
        typeDeclaration.setInterface(false);
        typeDeclaration.modifiers().add(ast.newModifier(ModifierKeyword.PUBLIC_KEYWORD));
        typeDeclaration.setName(ast.newSimpleName(ENTITY_CLASS_NAME));

        // Lista de itens dentro da classe
        final List typeBodyDeclarations = typeDeclaration.bodyDeclarations();

        // Criacao de um atributo
        final VariableDeclarationFragment variableDeclarationFragment = ast.newVariableDeclarationFragment();
        variableDeclarationFragment.setName(ast.newSimpleName(ENTITY_ATTRIBUTE_NAME));
        final FieldDeclaration fieldDeclaration = ast.newFieldDeclaration(variableDeclarationFragment);
        fieldDeclaration.modifiers().add(ast.newModifier(ModifierKeyword.PRIVATE_KEYWORD));
        fieldDeclaration.setType(ast.newSimpleType(ast.newName(STRING_TYPE)));
        typeBodyDeclarations.add(fieldDeclaration);

        // Criar um metodo set
        final MethodDeclaration setMethodDeclaration = ast.newMethodDeclaration();
        setMethodDeclaration.setName(ast.newSimpleName(ENTITY_ATTRIBUTE_SET));
        setMethodDeclaration.setReturnType2(ast.newPrimitiveType(PrimitiveType.VOID));
        setMethodDeclaration.modifiers().add(ast.newModifier(ModifierKeyword.PUBLIC_KEYWORD));
        final SingleVariableDeclaration parameter = ast.newSingleVariableDeclaration();
        parameter.setName(ast.newSimpleName(ENTITY_ATTRIBUTE_NAME));
        parameter.setType(ast.newSimpleType(ast.newName(STRING_TYPE)));
        parameter.modifiers().add(ast.newModifier(ModifierKeyword.FINAL_KEYWORD));
        setMethodDeclaration.parameters().add(parameter);
        typeBodyDeclarations.add(setMethodDeclaration);

        // Criar o conteudo do set
        final Block setBlock = ast.newBlock();
        setMethodDeclaration.setBody(setBlock);
        final FieldAccess fieldAccess = ast.newFieldAccess();
        fieldAccess.setExpression(ast.newThisExpression());
        fieldAccess.setName(ast.newSimpleName(ENTITY_ATTRIBUTE_NAME));
        final Assignment assignment = ast.newAssignment();
        assignment.setLeftHandSide(fieldAccess);
        assignment.setRightHandSide(ast.newName(ENTITY_ATTRIBUTE_NAME));
        setBlock.statements().add(ast.newExpressionStatement(assignment));

        final String newFileText = typeDeclaration.toString();
        System.out.print(newFileText);
    }
}
