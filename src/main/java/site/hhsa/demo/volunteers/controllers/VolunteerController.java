package site.hhsa.demo.volunteers.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import site.hhsa.demo.organizations.repositories.EventRepo;
import site.hhsa.demo.users.models.User;
import site.hhsa.demo.users.repositories.UserRepo;
import site.hhsa.demo.volunteers.models.Volunteer;
import site.hhsa.demo.volunteers.repositories.VolunteerRepo;

@Controller
public class VolunteerController {

    UserRepo userDao;
    VolunteerRepo volDao;
    EventRepo eventDao;

    public VolunteerController(UserRepo userDao, VolunteerRepo volDao, EventRepo eventDao) {
        this.userDao = userDao;
        this.volDao = volDao;
        this.eventDao = eventDao;
    }

    @GetMapping("/vols/{username}/register")
    public String volRegisterGet(@PathVariable String username, Model model) {
        User user = userDao.findByUsername(username);
        model.addAttribute("user", user);
        return "volunteers/register";
    }

    @PostMapping("/vols/{username}/register")
    public String volRegisterPost(@PathVariable String username, @ModelAttribute User user) {
        user.getVolunteer().setUser(userDao.findByUsername(username));
        volDao.save(user.getVolunteer());
        return "redirect:/vols/"+user.getUsername()+"/dash";
    }

    @GetMapping("/vols/{username}/dash")
    public String volProfile(@PathVariable String username, Model model) {
        User user = userDao.findByUsername(username);
        model.addAttribute("user", user);
        model.addAttribute("events", eventDao.findAll());

        return "volunteers/dashboard";
    }
}
