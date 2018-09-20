package site.hhsa.demo.volunteers.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import site.hhsa.demo.users.models.User;
import site.hhsa.demo.users.repositories.UserRepo;
import site.hhsa.demo.volunteers.models.Volunteer;
import site.hhsa.demo.volunteers.repositories.VolunteerRepo;

@Controller
public class VolunteerController {

    UserRepo userDao;
    VolunteerRepo volDao;

    public VolunteerController(UserRepo userDao, VolunteerRepo volDao) {
        this.userDao = userDao;
        this.volDao = volDao;
    }

    @GetMapping("/vol/register")
    public String volRegisterGet(@ModelAttribute User user, Model model) {
        Volunteer vol = new Volunteer();
        vol.setUser(); ;
        model.addAttribute("vol", vol);
        return "volunteers/register";
    }

    @PostMapping("/vol/register")
    public String volRegisterPost(@ModelAttribute Volunteer vol) {
        volDao.save(vol);
        return "redirect: /vol/profile/" + vol.getUser().getUsername();
    }

    @GetMapping("/vol/profile/{username}")
    public String volProfile(@PathVariable String username, Model model) {
        Volunteer vol = userDao.findByUsername(username).getVolunteer();
        model.addAttribute("vol", vol);
        return "volunteers/profile";
    }
}
