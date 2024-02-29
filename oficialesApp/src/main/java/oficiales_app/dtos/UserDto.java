package oficiales_app.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import oficiales_app.validations.PasswordMatches;
@Data
@PasswordMatches
public class UserDto {
	@NotNull
	@NotEmpty
	private String userName;
	@NotNull
	@NotEmpty
	private String password;
	private String matchingPassword;
}
