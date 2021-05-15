package springsecurity.demo.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import springsecurity.demo.business.abstracts.ApplicationUserService;
import springsecurity.demo.entitites.ApplicationUser;
import springsecurity.demo.repository.ApplicationUserRepository;
import springsecurity.demo.util.RoleParser;

import java.util.List;

@Service
public class ApplicationUserManager implements ApplicationUserService {

    private ApplicationUserRepository applicationUserRepository;

    @Autowired
    public ApplicationUserManager(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return applicationUserRepository.findByUsername(username);
    }

    @Override
    public ApplicationUser add(ApplicationUser applicationUser) {
        return applicationUserRepository.save(applicationUser);
    }

    @Override
    public void delete(Long id) {
        applicationUserRepository.deleteById(id);
    }

    @Override
    public ApplicationUser update(Long id, ApplicationUser applicationUser) {
        ApplicationUser applicationUserToUpdate = applicationUserRepository.getOne(id);
        applicationUserToUpdate.setAccountNonExpired(applicationUser.isAccountNonExpired());
        applicationUserToUpdate.setAccountNonLocked(applicationUser.isAccountNonLocked());
        applicationUserToUpdate.setCredentialsNonExpired(applicationUser.isCredentialsNonExpired());
        applicationUserToUpdate.setEnabled(applicationUser.isEnabled());
        applicationUserToUpdate.setUsername(applicationUser.getUsername());
        applicationUserToUpdate.setPassword(applicationUser.getPassword());
        applicationUserToUpdate.setRole(applicationUser.getRole());
        String[] role = applicationUserToUpdate.getRole().split(",");
        applicationUserToUpdate.setGrantedAuthorities(RoleParser.parse(role));
        return applicationUserRepository.save(applicationUserToUpdate);
    }

    @Override
    public List<ApplicationUser> saveAll(List<ApplicationUser> applicationUsers) {
        return applicationUserRepository.saveAll(applicationUsers);
    }

    @Override
    public List<ApplicationUser> getAll() {
        return applicationUserRepository.findAll();
    }
}
