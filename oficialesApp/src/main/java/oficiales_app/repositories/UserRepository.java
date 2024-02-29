package oficiales_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import oficiales_app.entities.User;

public interface UserRepository extends JpaRepository<User,Long>{

	User findByUserName(String email);

}
