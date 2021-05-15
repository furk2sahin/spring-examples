package springsecurity.demo.business.abstracts;

import springsecurity.demo.entitites.Role;

import java.util.List;

public interface RoleService{
    List<Role> getRolesByUserId(Long userId);
    void deleteRolesByUserId(Long userId);
    List<Role> getAll();
}
