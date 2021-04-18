package org.subham.merjency.model.resources;

import javax.validation.Payload;

import org.subham.merjency.model.Gender;

public @interface ValidateString {

    Gender[] acceptedValues();

    String message() default "{org.subham.merjency.model.resources.ValidateString.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { }; 
}