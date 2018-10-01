package site.hhsa.demo;


import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import site.hhsa.demo.auth.UserWithRoles;
import site.hhsa.demo.organizations.models.Organization;
import site.hhsa.demo.organizations.repositories.EventRepo;
import site.hhsa.demo.organizations.repositories.OrgRepo;
import site.hhsa.demo.services.SmsSender;
import site.hhsa.demo.users.models.User;
import site.hhsa.demo.users.repositories.UserRepo;
import site.hhsa.demo.volunteers.models.Volunteer;
import site.hhsa.demo.volunteers.repositories.VolunteerRepo;

import java.util.Collections;
import java.util.List;

@Controller
public class HomeController {

    EventRepo eventDao;
    UserRepo userDao;
    VolunteerRepo volDao;
    OrgRepo orgDao;
    private PasswordEncoder passwordEncoder;
    @Value("${accountSID}")
    private String ACCOUNT_SID;

    @Value("${authTOKEN}")
    private String AUTH_TOKEN;

    @Value("${phnNUM}")
    private String Phn_num;

    public HomeController(UserRepo userDao, VolunteerRepo volDao, OrgRepo orgDao,EventRepo eventDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.volDao = volDao;
        this.orgDao = orgDao;
        this.eventDao = eventDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String homePage(Model model){
        model.addAttribute("events", eventDao.findAll());
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
    public String dashRedirect(Model model){

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", currentUser);

        if (currentUser.isOrg()) {
            return "redirect:/orgs/"+ currentUser.getOrganization().getOrgName()+"/dashboard";
        }

        return "redirect:/vols/dash";
    }

    @PostMapping("/register")
    public String userRegister(@ModelAttribute User user, @RequestParam String isOrg, @RequestParam String passwordCon, RedirectAttributes redir) {

        List<User> users = (List<User>) userDao.findAll();

        for (User person : users) {
            if (user.getUsername().equals(person.getUsername())) {
                redir.addFlashAttribute("usernameExists", "Username '" + user.getUsername() + "' already exists.");
                redir.addFlashAttribute("newUser", user);
                return "redirect:/register";
            } else if (user.getEmail().equals(person.getEmail())) {
                redir.addFlashAttribute("emailExists", "There is already an account with that email.");
                redir.addFlashAttribute("newUser", user);
                return "redirect:/register";
            } else if (!user.getPassword().equals(passwordCon)) {
                redir.addFlashAttribute("passwordNoMatch", "Passwords do not match.");
                redir.addFlashAttribute("newUser", user);
                return "redirect:/register";
            }
        }

        user.setOrg(Boolean.parseBoolean(isOrg));
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);
        if (user.isOrg()) {
            return "redirect:/"+user.getUsername()+"/orgs/register";
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

    @GetMapping("/test")
    public String testSenderForm(){
        return "sender";
    }

    @PostMapping("/test")
    public String testSenderSend(@RequestParam("number")String number, @RequestParam("message") String message){


        new SmsSender().SmsSender(number,message,ACCOUNT_SID,AUTH_TOKEN,Phn_num);
        return "sender";
    }

    @GetMapping("/router")
    public String volOrOrg(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userDao.findByUsername(user.getUsername());

        if(currentUser.isOrg()){
            return "redirect:/orgs/"+currentUser.getOrganization().getOrgName();
        }else{
            return "redirect:/vols/"+currentUser.getUsername();
        }
    }

    @GetMapping("/router/messages")
    public String volOrOrgMail(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userDao.findByUsername(user.getUsername());

        if(currentUser.isOrg()){
            return "redirect:/orgs/messages";
        }else{
            return "redirect:/vols/messages";
        }
    }


}
