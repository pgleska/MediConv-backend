package com.github.pgleska.MediConv.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
  
	private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
	private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);;
	private Matcher matcher;
  
	@Override
	public void initialize(ValidEmail constraintAnnotation) {
	}
  
	@Override
  	public boolean isValid(String email, ConstraintValidatorContext context){
      return (validateEmail(email));
	}
  
	private boolean validateEmail(String email) {      
		matcher = pattern.matcher(email);
		return matcher.matches();
	}
}
