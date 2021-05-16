package springsecurity.demo.authenums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;
import java.util.stream.Collectors;

import static springsecurity.demo.authenums.UserPermission.*;

public enum UserRole {

    STUDENT(new HashSet<>(Arrays.asList(COURSE_BUY, COURSE_DELETE))),
    ADMIN(new HashSet<>(Arrays.asList(COURSE_READ, COURSE_WRITE, STUDENT_READ, STUDENT_WRITE, COURSE_BUY, COURSE_DELETE))),
    ADMINTRAINEE(new HashSet<>(Arrays.asList(COURSE_READ, STUDENT_READ)));

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities(){
        Set<SimpleGrantedAuthority> grantedAuthorities = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return grantedAuthorities;
    }
}
