package com.s2.easycode.validator;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.s2.easycode.sourcegenerator.AttributeType;
import com.s2.easycode.sourcegenerator.EntityDescription;

public class EntityValidatorServiceImplTest {

    @Test
    public void validateEntityOk() {
        final EntityDescription entityDescription = new EntityDescription();
        entityDescription.setName("Entity");
        entityDescription.setPackage("br.com.test");
        entityDescription.addAttrubute("Attribute", AttributeType.INTEGER);

        final EntityValidatorService service = new EntityValidatorServiceImpl();
        final boolean validate = service.validate(entityDescription);
        Assert.assertTrue(validate);

        final List<ErrorType> errors = service.getErrors();
        Assert.assertTrue(errors.isEmpty());
    }

    @Test
    public void validateEntityEmptyPackage() {
        final EntityDescription entityDescription = new EntityDescription();
        entityDescription.setName("Entity");
        entityDescription.setPackage("");
        entityDescription.addAttrubute("Attribute", AttributeType.INTEGER);

        final EntityValidatorService service = new EntityValidatorServiceImpl();
        final boolean validate = service.validate(entityDescription);
        Assert.assertTrue(validate);

        final List<ErrorType> errors = service.getErrors();
        Assert.assertTrue(errors.isEmpty());
    }

    @Test
    public void validateEntityNullPackage() {
        final EntityDescription entityDescription = new EntityDescription();
        entityDescription.setName("Entity");
        entityDescription.addAttrubute("Attribute", AttributeType.INTEGER);

        final EntityValidatorService service = new EntityValidatorServiceImpl();
        final boolean validate = service.validate(entityDescription);
        Assert.assertFalse(validate);

        final List<ErrorType> errors = service.getErrors();
        Assert.assertTrue(errors.contains(ErrorType.ENTITY_PACKAGE_NAME_ERROR));
    }

    @Test
    public void validateEntityNullName() {
        final EntityDescription entityDescription = new EntityDescription();
        entityDescription.setName(null);
        entityDescription.addAttrubute("Attribute", AttributeType.INTEGER);

        final EntityValidatorService service = new EntityValidatorServiceImpl();
        final boolean validate = service.validate(entityDescription);
        Assert.assertFalse(validate);

        final List<ErrorType> errors = service.getErrors();
        Assert.assertFalse(errors.isEmpty());
        Assert.assertTrue(errors.contains(ErrorType.ENTITY_CLASS_NAME_ERROR));
    }

    @Test
    public void validateEntityEnptyName() {
        final EntityDescription entityDescription = new EntityDescription();
        entityDescription.setName("");
        entityDescription.addAttrubute("Attribute", AttributeType.INTEGER);

        final EntityValidatorService service = new EntityValidatorServiceImpl();
        final boolean validate = service.validate(entityDescription);
        Assert.assertFalse(validate);

        final List<ErrorType> errors = service.getErrors();
        Assert.assertFalse(errors.isEmpty());
        Assert.assertTrue(errors.contains(ErrorType.ENTITY_CLASS_NAME_ERROR));
    }

    @Test
    public void validateEntityAttributeEnptyName() {
        final EntityDescription entityDescription = new EntityDescription();
        entityDescription.setName("Name");
        entityDescription.addAttrubute("", AttributeType.INTEGER);

        final EntityValidatorService service = new EntityValidatorServiceImpl();
        final boolean validate = service.validate(entityDescription);
        Assert.assertFalse(validate);

        final List<ErrorType> errors = service.getErrors();
        Assert.assertFalse(errors.isEmpty());
        Assert.assertTrue(errors.contains(ErrorType.ENTITY_ATTRIBUTE_NAME_ERROR));
    }

    @Test
    public void validateEntityAttributeNullName() {
        final EntityDescription entityDescription = new EntityDescription();
        entityDescription.setName("Name");
        entityDescription.addAttrubute(null, AttributeType.INTEGER);

        final EntityValidatorService service = new EntityValidatorServiceImpl();
        final boolean validate = service.validate(entityDescription);
        Assert.assertFalse(validate);

        final List<ErrorType> errors = service.getErrors();
        Assert.assertFalse(errors.isEmpty());
        Assert.assertTrue(errors.contains(ErrorType.ENTITY_ATTRIBUTE_NAME_ERROR));
    }

    @Test
    public void validateEntityAttributeNullType() {
        final EntityDescription entityDescription = new EntityDescription();
        entityDescription.setName("Name");
        entityDescription.addAttrubute("Attribute", null);

        final EntityValidatorService service = new EntityValidatorServiceImpl();
        final boolean validate = service.validate(entityDescription);
        Assert.assertFalse(validate);

        final List<ErrorType> errors = service.getErrors();
        Assert.assertFalse(errors.isEmpty());
        Assert.assertTrue(errors.contains(ErrorType.ENTITY_ATTRIBUTE_TYPE_ERROR));
    }

}
