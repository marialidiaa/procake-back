package com.procake.v1.dtos.validations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = SenhaValidator.class)
public @interface SenhaValidation {
	// error message
	public String message() default "Senha invalida: A senha deve ter no mínimo 5 caracteres, 1 letra maiúscula, 1 letra minúscula, 1 caractere especial, e não conter sequencias.";

	// represents group of constraints
	public Class<?>[] groups() default {};

	// represents additional information about annotation
	public Class<? extends Payload>[] payload() default {};
}