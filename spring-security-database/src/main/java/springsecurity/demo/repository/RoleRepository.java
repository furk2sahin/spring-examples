package springsecurity.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springsecurity.demo.entitites.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
