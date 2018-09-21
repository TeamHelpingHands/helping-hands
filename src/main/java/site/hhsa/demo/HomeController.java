package site.hhsa.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    public HomeController(UserRepo userDao, VolunteerRepo volDao, OrgRepo orgDao) {
        this.userDao = userDao;
        this.volDao = volDao;
        this.orgDao = orgDao;
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
    public String userRegister(@ModelAttribute User user, @RequestParam String isOrg, Model model) {
        user.setOrg(Boolean.parseBoolean(isOrg));
        user.setVolunteer(new Volunteer());
        userDao.save(user);
        model.addAttribute("user", user);
        if (user.isOrg()) {
            return "redirect:/organizations/register";
        }
        return "redirect:/vols/"+user.getUsername()+"/register";
    }

}
