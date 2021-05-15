package springsecurity.demo.util;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import springsecurity.demo.entitites.Role;

import java.util.HashSet;
import java.util.Set;

import static springsecurity.demo.authEnums.ApplicationUserRole.*;

public class RoleParser {

    public static Set<Role> parse(String[] roles){
        Set<Role> authorities = new HashSet<>();
        for(String role : roles){
            if(role.toUpperCase().equals(ADMIN.name())){
                for(SimpleGrantedAuthority permission : ADMIN.getAuthorities())
                    addAuthority(authorities, permission);
            } else if(role.toUpperCase().equals(ADMINTRAINEE.name())){
                for(SimpleGrantedAuthority permission : ADMINTRAINEE.getAuthorities())
                    addAuthority(authorities, permission);
            }
        }
        return authorities;
    }

    public static void addAuthority(Set<Role> authorities, SimpleGrantedAuthority permission){
        Role roleToAdd = new Role();
        roleToAdd.setAuthority(permission.getAuthority());
        authorities.add(roleToAdd);
    }
}
