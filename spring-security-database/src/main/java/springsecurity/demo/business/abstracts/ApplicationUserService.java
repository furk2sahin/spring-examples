package springsecurity.demo.business.abstracts;

import org.springframework.security.core.userdetails.UserDetailsService;
import springsecurity.demo.entitites.ApplicationUser;

import java.util.List;

public interface ApplicationUserService extends UserDetailsService{
    ApplicationUser add(ApplicationUser add);
    void delete(Long id);
    ApplicationUser update(Long id, ApplicationUser update);
    List<ApplicationUser> saveAll(List<ApplicationUser> saveList);
    List<ApplicationUser> getAll();
}
