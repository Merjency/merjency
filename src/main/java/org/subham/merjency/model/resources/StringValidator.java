package org.subham.merjency.model.resources;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.subham.merjency.model.Gender;

public class StringValidator implements ConstraintValidator<ValidateString, String>{

    private List<String> valueList;

    @Override
    public void initialize(ValidateString constraintAnnotation) {
        valueList = new ArrayList<String>();
        for(Gender val : constraintAnnotation.acceptedValues()) {
            valueList.add(val.toString());
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return valueList.contains(value.toUpperCase());
    }

}