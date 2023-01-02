package com.github.pgleska.MediConv.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String>  {
	private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@$!%*?&])[A-Za-z0-9@$!%*?&]{8,}$";
	private  static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
	private Matcher matcher;	
	  
	@Override
	public void initialize(ValidPassword constraintAnnotation) {
	}
	  
	@Override
	public boolean isValid(String password, ConstraintValidatorContext context){
	    return (validatePassword(password));
	}
	 
	private boolean validatePassword(String email) {
	    matcher = pattern.matcher(email);
	    return matcher.matches();
	}
}
