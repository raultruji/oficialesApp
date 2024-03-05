package oficiales_app.dtos;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import oficiales_app.entities.Role;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDto {

	private String userName;
	private String password;
	private Set<Role> roles;
}
