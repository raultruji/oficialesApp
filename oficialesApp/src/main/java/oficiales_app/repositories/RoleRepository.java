package oficiales_app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import oficiales_app.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

   Optional<Role> findByName(String name);
   @Override
   void delete(Role role);

}
