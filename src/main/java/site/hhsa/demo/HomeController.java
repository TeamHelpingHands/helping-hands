package site.hhsa.demo;


import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.security.core.context.SecurityContextHolder;

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

    @GetMapping("/dash")
    public String dashRedirect(){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

//        This is giving us problems...The logged-in user apparently
//        has no Org associated with it, but if you look in your DB
//        you will be able to see the Org was successfully created
//        for that user...so what's up with that
        System.out.println("THIS COMES UP NULL, FOR SOME REASON: " + currentUser.getOrganization());
        if (currentUser.getOrganization() == null) {
            return "redirect:/vols/dash";
        } else {
            return "redirect:/orgs/"+currentUser.getOrganization().getOrgName()+"/dashboard";
        }

    }

    @PostMapping("/register")
    public String userRegister(@ModelAttribute User user, @RequestParam String isOrg, Model model) {

        System.out.println("isOrg string: " + isOrg);
        user.setOrg(Boolean.parseBoolean(isOrg));

        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);
        System.out.println("IS THIS AN ORG????: " + user.isOrg());
        if (user.isOrg()) {
            return "redirect:/"+user.getUsername()+"/orgs/register";
        } else {
            return "redirect:/vols/" + user.getUsername() + "/register";
        }
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
