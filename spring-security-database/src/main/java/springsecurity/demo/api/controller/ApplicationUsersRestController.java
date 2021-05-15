package springsecurity.demo.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springsecurity.demo.business.concretes.ApplicationUserManager;
import springsecurity.demo.business.concretes.RoleManager;
import springsecurity.demo.entitites.ApplicationUser;
import springsecurity.demo.util.RoleParser;

import java.util.*;

@RestController
@RequestMapping("/api/app-user")
public class ApplicationUsersRestController {

    private ApplicationUserManager applicationUserManager;
    private RoleManager roleManager;

    @Autowired
    public ApplicationUsersRestController(ApplicationUserManager applicationUserManager, RoleManager roleManager) {
        this.applicationUserManager = applicationUserManager;
        this.roleManager = roleManager;
    }

    @GetMapping("/get-by-username")
    public ApplicationUser getApplicationUserByUsername(@RequestParam("username") String username){
        return (ApplicationUser) applicationUserManager.loadUserByUsername(username);
    }

    @PostMapping("/add")
    public ApplicationUser add(@RequestBody ApplicationUser applicationUser){
        String[] roles = applicationUser.getRole().split(",");
        applicationUser.setGrantedAuthorities(RoleParser.parse(roles));
       return applicationUserManager.add(applicationUser);
    }

    @DeleteMapping
    public void delete(@RequestParam Long id){
        applicationUserManager.delete(id);
    }

    @PutMapping
    public ApplicationUser update(@RequestParam("id") Long id, @RequestBody ApplicationUser applicationUser){
        roleManager.deleteRolesByUserId(id);
        return applicationUserManager.update(id, applicationUser);
    }

    @PostMapping("/add-all")
    public List<ApplicationUser> addAll(@RequestBody List<ApplicationUser> applicationUsers){
        for(ApplicationUser applicationUser : applicationUsers){
            String[] roles = applicationUser.getRole().split(",");
            applicationUser.setGrantedAuthorities(RoleParser.parse(roles));
        }
        return applicationUserManager.saveAll(applicationUsers);
    }

    @GetMapping("/get-all")
    public List<ApplicationUser> getAll(){
        return applicationUserManager.getAll();
    }


}
