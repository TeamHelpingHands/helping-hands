package site.hhsa.demo;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import site.hhsa.demo.organizations.models.Organization;
import site.hhsa.demo.organizations.repositories.OrgRepo;
import site.hhsa.demo.users.models.User;
import site.hhsa.demo.users.repositories.UserRepo;
import site.hhsa.demo.volunteers.models.Volunteer;
import site.hhsa.demo.volunteers.repositories.VolunteerRepo;

@Controller
public class HomeController {

    UserRepo userDao;
    VolunteerRepo volDao;
    OrgRepo orgDao;
    private PasswordEncoder passwordEncoder;

    public HomeController(UserRepo userDao, VolunteerRepo volDao, OrgRepo orgDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.volDao = volDao;
        this.orgDao = orgDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String homePage(){
        return "index";
    }

    @GetMapping("/faq")
    public String faqPage(){
        return "faq";
    }

    @GetMapping("/about-team")
    public String aboutUs(){
        return "about-team";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
}
