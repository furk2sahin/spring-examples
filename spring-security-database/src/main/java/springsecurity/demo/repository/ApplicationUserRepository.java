package springsecurity.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springsecurity.demo.entitites.ApplicationUser;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    ApplicationUser findByUsername(String username);
}
