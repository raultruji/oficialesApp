package oficiales_app.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import oficiales_app.dtos.UserLoginDto;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches,Object>{

	@Override
	public void initialize(PasswordMatches constraintAnnotation) {
		
	}
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		UserLoginDto user = (UserLoginDto) value;
		return user.getPassword().equals(user.getMatchingPassword());
	}

}
