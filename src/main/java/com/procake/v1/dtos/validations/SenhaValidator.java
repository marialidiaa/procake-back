package com.procake.v1.dtos.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SenhaValidator implements ConstraintValidator<SenhaValidation, String> {

	public boolean isValid(String pwd, ConstraintValidatorContext cxt) {

		// Valida se a senha tem mais de 5 caracteres
		if (pwd.length() < 5) {
			return false;
		}

		// Valores espceiais aceitos
		String caracteresSpeciais = "/*!@#$%^&*()\"{}_[]|\\?/<>,.";
		boolean maiuscula = false;
		boolean miniscula = false;
		boolean caractereSpecial = false;

		//Validação de cada caracter
		for (int i = 0; i < pwd.length(); i++) {

			char c = pwd.charAt(i);

			if (Character.isLowerCase(c)) {
				miniscula = true;
			}

			if (Character.isUpperCase(c)) {
				maiuscula = true;
			}

			if (caracteresSpeciais.contains(pwd.substring(i, i + 1))) {
				caractereSpecial = true;
			}
		}

		if (maiuscula && miniscula && caractereSpecial) {
			return true;
		}

		return false;
	}
}
