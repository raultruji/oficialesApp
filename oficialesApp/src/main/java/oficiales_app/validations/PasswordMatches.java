package oficiales_app.validations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
/**
 * Interface for the custom annotation for match passwords validator
 */
@Target({TYPE,ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Documented
public @interface PasswordMatches {
   // All attributes of an annotation type are declared in a method-like manner
	String message() default "Passwords don't match";
    
	Class<?>[] groups() default {};
    
	Class<? extends Payload>[] payload() default {};
}
