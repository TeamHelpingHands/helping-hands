package site.hhsa.demo;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import site.hhsa.demo.auth.UserWithRoles;
import site.hhsa.demo.organizations.models.Organization;
import site.hhsa.demo.organizations.repositories.OrgRepo;
import site.hhsa.demo.users.models.User;
import site.hhsa.demo.users.repositories.UserRepo;
import site.hhsa.demo.volunteers.models.Volunteer;
import site.hhsa.demo.volunteers.repositories.VolunteerRepo;

import java.util.Collections;

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

    @PostMapping("/register")
    public String userRegister(@ModelAttribute User user, Model model) {
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);
        authenticate(user);
        if (user.isOrg()) {
            return "redirect:/"+ user.getUsername()+"/orgs/register";
        }
        return "redirect:/vols/"+user.getUsername()+"/register";
    }

    private void authenticate(User user) {
        // Notice how we're using an empty list for the roles
        UserDetails userDetails = new UserWithRoles(user);
        Authentication auth = new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(auth);
    }
}
