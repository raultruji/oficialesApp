package oficiales_app.services;

import oficiales_app.dtos.UserLoginDto;
import oficiales_app.entities.User;

public interface IUserService {

	User registerNewUser(UserLoginDto userDto);
}
