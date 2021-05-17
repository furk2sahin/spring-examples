package springsecurity.demo.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springsecurity.demo.business.concretes.RoleManager;
import springsecurity.demo.entitites.concretes.Role;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RolesRestController {

    private RoleManager roleManager;

    @Autowired
    public RolesRestController(RoleManager roleManager) {
        this.roleManager = roleManager;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<Role> getAll(){
        return roleManager.getAll();
    }

    @GetMapping("/get-by-user-id")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<Role> getRolesByUserId(@RequestParam("id") Long id){
        return roleManager.getRolesByUserId(id);
    }
}
