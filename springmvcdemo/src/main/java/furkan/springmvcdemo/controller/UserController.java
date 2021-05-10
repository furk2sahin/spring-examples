package furkan.springmvcdemo.controller;

import furkan.springmvcdemo.model.User;
import furkan.springmvcdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerPage(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String saveRegisterPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model){
        model.addAttribute("user", user);

        if(result.hasErrors()){
            return "register";
        } else{
            userService.saveUser(user);
        }
        return "index";
    }

    @RequestMapping("/")
    public String login(){
        return "login";
    }

    @RequestMapping("/secure")
    public String secure(){
        return "secure";
    }
}
