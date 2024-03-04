package oficiales_app;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import oficiales_app.entities.Role;
import oficiales_app.entities.User;
import oficiales_app.repositories.RoleRepository;
import oficiales_app.repositories.UserRepository;

@SpringBootApplication
public class OficialesAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(OficialesAppApplication.class, args);
	}
	
	@Bean
	CommandLineRunner run(RoleRepository roleRepo, UserRepository userRepo, PasswordEncoder encoder) {
		return args -> {
			if(roleRepo.findByName("ADMIN").isPresent()) return;
			Role adminRole = roleRepo.save(new Role("ADMIN"));
			roleRepo.save(new Role("USER"));
			
			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);
			
			User user = new User(1L,"admin",encoder.encode("admin"),roles);
			userRepo.save(user);
		};
	}

}
