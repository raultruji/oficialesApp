package oficiales_app.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import oficiales_app.dtos.UserDto;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches,Object>{

	@Override
	public void initialize(PasswordMatches constraintAnnotation) {
		
	}
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		UserDto user = (UserDto) value;
		return user.getPassword().equals(user.getMatchingPassword());
	}

}
