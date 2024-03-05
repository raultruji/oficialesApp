package oficiales_app.services;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import oficiales_app.dtos.UserLoginDto;
import oficiales_app.entities.User;
import oficiales_app.repositories.RoleRepository;
import oficiales_app.repositories.UserRepository;
import oficiales_app.validations.exceptions.UserAlreadyExistsException;

@Service
@Transactional
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public User registerNewUser(UserLoginDto userDto)throws UserAlreadyExistsException {
		if(userNameExists(userDto.getUserName()))
			throw new UserAlreadyExistsException("Ya existe la cuenta para "+userDto.getUserName());
		final User user = new User();
		user.setUserName(userDto.getUserName());
		user.setPassword(encoder.encode(userDto.getPassword()));
		//user.setRoles(Arrays.asList("ROLE_USER"));
		user.setRoles(Collections.singletonList(roleRepository.findByName("ROLE_USER")));
		userRepo.save(user);
		return user;
	}

	private boolean userNameExists(final String email) {
		return userRepo.findByUserName(email) != null;
	}
}
