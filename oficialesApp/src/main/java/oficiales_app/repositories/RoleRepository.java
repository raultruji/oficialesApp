package oficiales_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import oficiales_app.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
   @Override
   void delete(Role role);

}
