package oficiales_app.services;

import oficiales_app.dtos.UserDto;
import oficiales_app.entities.User;

public interface IUserService {

	User registerNewUser(UserDto userDto);
}
